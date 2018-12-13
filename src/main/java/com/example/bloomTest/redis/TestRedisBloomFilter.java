package com.example.bloomTest.redis;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 16:37 2018-12-12
 */
public class TestRedisBloomFilter {

    private static final int TOTAL = 100000000;
    private static final double FPP = 0.0005;

    public static void main(String[] args) {
        RedisBloomFilter<String> redisBloomFilter = RedisBloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), TOTAL, FPP);

//        redisBloomFilter.resetBitmap();


        IntStream.range(0, TOTAL).parallel().forEach(i -> {
            String s = Hashing.md5().hashInt(i).toString();
            redisBloomFilter.put(s);
        });


        long start = System.currentTimeMillis();
//        String str1 = Hashing.md5().hashInt(99999).toString();
        String str2 = Hashing.md5().hashInt(9999).toString();
//        String str3 = "abcdefghijklmnopqrstuvwxyz123456";
//        System.out.println(redisBloomFilter.mightContain(str1));
        System.out.println(redisBloomFilter.mightContain(str2));
//        System.out.println(redisBloomFilter.mightContain(str3));
        long end = System.currentTimeMillis();
        System .out.println("执行时间：" + (end - start));
    }
}
