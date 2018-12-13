package com.example.bloomTest.redis;

import com.example.bloomTest.redis.functional.PipelineExecutor;
import com.google.common.math.LongMath;
import com.google.common.primitives.Longs;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 16:24 2018-12-12
 */
public class RedisBitmaps {
    /**
     * key
     */
    private static final String BASE_KEY = "bloomfilter";
    /**
     * 游标
     */
    private static final String CURSOR = "cursor";
    /**
     * redis工具
     */
    private JedisUtils jedisUtils;
    /**
     * bit长度值
     */
    private long bitSize;

    RedisBitmaps(long bits) {
        this.jedisUtils = new JedisUtils();
        this.bitSize = LongMath.divide(bits, 64, RoundingMode.CEILING) * Long.SIZE;//位数组的长度，相当于n个long的长度
        if (bitCount() == 0) {
            jedisUtils.execute((jedisCluster -> jedisCluster.setbit(currentKey(), bitSize - 1, false)));
        }
    }

    public boolean get(long[] offsets) {
        for (long i = 0; i < cursor() + 1; i++) {
            final long cursor = i;
            //只要有一个cursor对应的bitmap中，offsets全部命中，则表示可能存在
            boolean match = Arrays.stream(offsets).boxed()
                    .map(offset -> jedisUtils.execute(jedisCluster -> jedisCluster.getbit(genkey(cursor), offset)))
                    .allMatch(b -> (Boolean) b);
            if (match){
                return true;
            }
        }
        return false;
    }

    public boolean get(final long offset) {
        return jedisUtils.execute(jedisCluster -> jedisCluster.getbit(currentKey(), offset));
    }

    /**
     * 设置偏移量bit位
     * @param offsets 偏移量数组
     * @return
     */
    public boolean set(long[] offsets) {
        if (cursor() > 0 && get(offsets)) {
            return false;
        }
        boolean bitsChanged = false;
        for (long offset : offsets) {
            bitsChanged |= set(offset);
        }
        return bitsChanged;
    }

    public boolean set(long offset) {
        if (!get(offset)) {
            jedisUtils.execute(jedisCluster -> jedisCluster.setbit(currentKey(), offset, true));
            return true;
        }
        return false;
    }

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量
     * @return
     */
    private long bitCount() {
        return jedisUtils.execute(jedisCluster -> jedisCluster.bitcount(currentKey()));
    }

    public long bitSize() {
        return this.bitSize;
    }

    private String currentKey() {
        return genkey(cursor());
    }

    private String genkey(long cursor) {
        return BASE_KEY + "-" + cursor;
    }

    private Long cursor() {
        String cursor = jedisUtils.execute(jedisCluster -> jedisCluster.get(CURSOR));
        return cursor == null ? 0 : Longs.tryParse(cursor);
    }

    public void ensureCapacityInternal() {
        if (bitCount() * 2 > bitSize()){
            grow();
        }
    }

    public void grow() {
        Long cursor = jedisUtils.execute(jedisCluster -> jedisCluster.incr(CURSOR));
        jedisUtils.execute((jedisCluster -> jedisCluster.setbit(genkey(cursor), bitSize - 1, false)));
    }

    public void reset() {
        String[] keys = LongStream.range(0, cursor() + 1).boxed().map(this::genkey).toArray(String[]::new);
        jedisUtils.execute(jedisCluster -> jedisCluster.del(keys));
        jedisUtils.execute(jedisCluster -> jedisCluster.set(CURSOR, "0"));
        jedisUtils.execute(jedisCluster -> jedisCluster.setbit(currentKey(), bitSize - 1, false));
    }

//    private PipelineExecutor apply(PipelineExecutor executor) {
//        return executor;
//    }
}
