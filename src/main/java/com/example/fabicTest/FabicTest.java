package com.example.fabicTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 16:38 2018-06-24
 */
public class FabicTest {

    static final ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        int NUM = 10;
        long startTime = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(NUM, () -> {
            //  计算耗费时间
            System.out.println("---计算耗费时间:"+(System.currentTimeMillis() - startTime));
        });

        List<Future<Long>> resultList = new ArrayList<Future<Long>>();

        for(int i = 0; i < NUM; i ++) {
            Future<Long> future = service.submit((new FabicTask(41, barrier)));
            resultList.add(future);
        }

        resultList.forEach(longFuture -> {
            Long value = null;
            try {
                value = longFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println(value);
        });

        service.shutdown();
    }
}
