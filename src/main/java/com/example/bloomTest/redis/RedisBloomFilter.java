package com.example.bloomTest.redis;

import com.example.bloomTest.redis.strategy.CustomBloomFilterStrategy;
import com.example.bloomTest.redis.strategy.Strategy;
import com.google.common.hash.Funnel;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: qiudong
 * @description: redis布隆过滤器
 * @date: Created in 16:51 2018-12-12
 */
public class RedisBloomFilter<T> {

    /**
     * redis bitmap
     */
    private final RedisBitmaps bits;

    /**
     * hash次数
     */
    private final int numHashFunctions;

    /**
     * 转字节
     */
    private final Funnel<? super T> funnel;

    /**
     * 对象映射索引位的策略
     */
    private final Strategy strategy;

    private RedisBloomFilter(
            RedisBitmaps bits, int numHashFunctions, Funnel<? super T> funnel, Strategy strategy) {
        checkArgument(numHashFunctions > 0, "numHashFunctions (%s) must be > 0", numHashFunctions);
        checkArgument(numHashFunctions <= 255, "numHashFunctions (%s) must be <= 255", numHashFunctions);
        this.bits = checkNotNull(bits);
        this.numHashFunctions = numHashFunctions;
        this.funnel = checkNotNull(funnel);
        this.strategy = checkNotNull(strategy);
    }

    public static <T> RedisBloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp){
        checkArgument(expectedInsertions >= 0, "Expected insertions (%s) must be >= 0", expectedInsertions);
        checkArgument(fpp > 0.0, "False positive probability (%s) must be > 0.0", fpp);
        checkArgument(fpp < 1.0, "False positive probability (%s) must be < 1.0", fpp);
        if (expectedInsertions == 0) {
            expectedInsertions = 1;
        }
        long numBits = optimalNumOfBits(expectedInsertions, fpp);
        int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
        try {
            return new RedisBloomFilter<T>(new RedisBitmaps(numBits), numHashFunctions, funnel, new CustomBloomFilterStrategy());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create RedisBloomFilter of " + numBits + " bits", e);
        }
    }

    /**
     *计算最适宜的bit数组长度
     */
    private static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * 计算最适宜的hash函数次数
     */
    private static int optimalNumOfHashFunctions(long n, long m) {
        // (m / n) * log(2), but avoid truncation due to division!
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    /**
     * 重置bitmap
     */
    public void resetBitmap() {
        bits.reset();
    }

    /**
     * 加入布隆过滤器
     * @param t
     * @return
     */
    public boolean put(T t) {
        return strategy.put(t, funnel, numHashFunctions, bits);
    }

    /**
     * 判断是否存在
     * @param t
     * @return
     */
    public boolean mightContain(T t) {
        return strategy.mightContain(t, funnel, numHashFunctions, bits);
    }
}
