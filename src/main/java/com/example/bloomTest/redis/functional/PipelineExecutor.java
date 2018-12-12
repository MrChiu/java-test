package com.example.bloomTest.redis.functional;

import redis.clients.jedis.Pipeline;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 14:55 2018-12-12
 */
@FunctionalInterface
public interface PipelineExecutor {
    void load(Pipeline pipeline);
}
