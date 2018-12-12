package com.example.bloomTest.demo;

import org.junit.Assert;

/**
 * @author: qiudong
 * @description:
 * 提高数组长度以及 hash 计算次数可以降低误报率，
 * 但相应的 CPU、内存的消耗就会提高；这就需要根据业务需要自行权衡。
 * @date: Created in 11:33 2018-12-12
 */
public class TestBloomFilters {
    public static void main(String[] args){
        long star = System.currentTimeMillis();

        BloomFilters bloomFilters = new BloomFilters (10000000) ;
        for (int i = 0; i < 10000000; i++) {
            bloomFilters.add(i + "") ;
        }
        Assert.assertTrue(bloomFilters.check(1+""));
        Assert.assertTrue(bloomFilters.check(2+""));
        Assert.assertTrue(bloomFilters.check(3+""));
        Assert.assertTrue(bloomFilters.check(999999+""));
        Assert.assertFalse(bloomFilters.check(400230340+""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }
}
