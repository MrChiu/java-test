package com.example.other;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        HashMap<String, List<String>> map1 = new HashMap<>();

        map1.computeIfAbsent("a1", (x) -> new ArrayList<>()).add("aaaa1");

        System.out.println(map1.get("a1"));

//        String value = map.get("k1");
//        log.info("value:{}"+ value);

//        String nullValue = map.get("k3");
//        log.info("nullValue:{}"+ nullValue);

//        String temp = map.get("CommonProgramFiles");
//        log.info("temp:{}"+ temp);

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

    }
}
