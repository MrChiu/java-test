package com.example.other;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 15:26 2018-07-03
 */
@Slf4j
public class HashMapTest {

    public static void main(String[] args){
//        HashMap<String, String> map = new HashMap<>();
//
//        map.put("k1", "v1");
//        map.put("k2", "v2");
//        map.put("k4", "v4");
//        map.put("k1", "v2");
//
//        map.forEach((x, y) -> {
//            System.out.println(x+":"+y);
//        });

//        HashMap<String, List<String>> map1 = new HashMap<>();
//
//        map1.computeIfAbsent("a1", (x) -> new ArrayList<>()).add("aaaa1");
//
//        System.out.println(map1.get("a1"));

//        String value = map.get("k1");
//        log.info("value:{}"+ value);

//        String nullValue = map.get("k3");
//        log.info("nullValue:{}", nullValue);

//        String temp = map.get("CommonProgramFiles");
//        log.info("temp:{}", temp);

//        System.out.println("-------------------------");
//
//        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
//        linkedHashMap.put("a1", "v1");
//        linkedHashMap.put("b2", "v2");
//        linkedHashMap.put("c3", "v3");
////        linkedHashMap.put("a1", "v2");
//        Object b2 = linkedHashMap.get("b2");
//
//
//        linkedHashMap.forEach((x ,y) -> {
//            System.out.println(x+":"+y);
//        });

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Map<String, Double> map = new HashMap<>(2);
        ConcurrentHashMap<String, Double> concurrentHashMap = new ConcurrentHashMap<>(2);
        concurrentHashMap.put("k1", 1.00);
        concurrentHashMap.put("k2", 2.00);

        for(int i=0; i<100; i++){
            Thread t = new Thread(() -> {
                try {
                    countDownLatch.await();
                    concurrentHashMap.put("k"+ Math.random(), Math.random());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }

        countDownLatch.countDown();

        while (concurrentHashMap.size() != 102){
            try {
                Thread.sleep(100L);
                System.out.println(concurrentHashMap.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
