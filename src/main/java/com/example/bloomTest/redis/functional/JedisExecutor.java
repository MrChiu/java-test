package com.example.bloomTest.redis.functional;


import redis.clients.jedis.Jedis;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 14:48 2018-12-12
 */
@FunctionalInterface
public interface JedisExecutor<T> {
    T execute(Jedis jedis);
}
