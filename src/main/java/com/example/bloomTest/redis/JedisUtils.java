package com.example.bloomTest.redis;

import com.example.bloomTest.redis.functional.JedisExecutor;
import com.example.bloomTest.redis.functional.PipelineExecutor;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 14:57 2018-12-12
 */
public class JedisUtils {
    private static final GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

    private JedisPool jedisPool;

    public JedisUtils() {
        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    public <T> T execute(JedisExecutor<T> jedisExecutor) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedisExecutor.execute(jedis);
        }
    }

    public List<Object> pipeline(List<PipelineExecutor> pipelineExecutors) {
        try (Jedis jedis = jedisPool.getResource()) {
            Pipeline pipeline = jedis.pipelined();

            for (PipelineExecutor executor : pipelineExecutors){
                executor.load(pipeline);
            }
            return pipeline.syncAndReturnAll();
        }
    }
}
