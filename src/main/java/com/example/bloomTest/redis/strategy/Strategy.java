package com.example.bloomTest.redis.strategy;

import com.example.bloomTest.redis.RedisBitmaps;
import com.google.common.hash.Funnel;

import java.io.Serializable;

/**
 * @author: qiudong
 * @description: 对象映射索引位策略
 * @date: Created in 15:50 2018-12-12
 */
public interface Strategy extends Serializable {

    /**
     * 加入布隆过滤器
     * @param object
     * @param funnel
     * @param numHashFunctions
     * @param bits
     * @param <T>
     * @return
     */
    <T> boolean put(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits);

    /**
     * 布隆判定
     * @param object
     * @param funnel
     * @param numHashFunctions
     * @param bits
     * @param <T>
     * @return
     */
    <T> boolean mightContain(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits);

}
