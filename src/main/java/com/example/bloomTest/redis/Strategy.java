package com.example.bloomTest.redis;

import com.google.common.hash.Funnel;

import java.io.Serializable;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 15:50 2018-12-12
 */
public interface Strategy extends Serializable {

    <T> boolean put(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits);

    <T> boolean mightContain(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits);

}
