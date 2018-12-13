package com.example.bloomTest.redis;

import com.example.bloomTest.redis.functional.JedisExecutor;
import com.example.bloomTest.redis.functional.PipelineExecutor;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 14:57 2018-12-12
 */
public class JedisUtils {
    private static final GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

//    private JedisPool jedisPool;

    private JedisCluster jedisCluster;

    public JedisUtils() {

        //创建jedisCluster对象，有一个参数 nodes是Set类型，Set包含若干个HostAndPort对象
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.0.140.170",7001));
        nodes.add(new HostAndPort("10.0.140.170",7002));
        nodes.add(new HostAndPort("10.0.140.170",7003));
        nodes.add(new HostAndPort("10.0.140.173",7001));
        nodes.add(new HostAndPort("10.0.140.173",7002));
        nodes.add(new HostAndPort("10.0.140.173",7003));
        jedisCluster = new JedisCluster(nodes);

//        jedisPool = new JedisPool(poolConfig, "10.0.140.170", 7001);
    }

    public <T> T execute(JedisExecutor<T> jedisExecutor) {
//        try (Jedis jedis = jedisPool.getResource()) {
            return jedisExecutor.execute(jedisCluster);
//        }
    }

//    public List<Object> pipeline(List<PipelineExecutor> pipelineExecutors) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            Pipeline pipeline = jedis.pipelined();
//
//            for (PipelineExecutor executor : pipelineExecutors){
//                executor.load(pipeline);
//            }
//            return pipeline.syncAndReturnAll();
//        }
//    }
}
