package com.example.other;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 13:45 2018-06-26
 */
public class CountdowmTest {

    private static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    public static void main(String[] args){

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable runnable = () -> {
            try {
                countDownLatch.await();

                System.out.println("1--------"+Thread.currentThread().getName());

                Object value = concurrentHashMap.putIfAbsent("k1", "v1");//Thread.currentThread().getName()

                if(value == null){
                    System.out.println("2--------null");
                }else{
                    System.out.println("2--------"+value.toString());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for(int i = 0; i< 10; i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }

        countDownLatch.countDown();
    }
}
