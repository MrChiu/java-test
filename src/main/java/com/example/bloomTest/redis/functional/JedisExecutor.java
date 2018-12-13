package com.example.bloomTest.redis.functional;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 14:48 2018-12-12
 */
@FunctionalInterface
public interface JedisExecutor<T> {
    T execute(JedisCluster jedisCluster);
}
