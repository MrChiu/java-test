package com.example.other;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 17:18 2018-06-07
 */
public class VolatileTest {

    public static AtomicInteger race = new AtomicInteger(0);

    public static int intRace = 0;

    public static volatile int volatileRace = 0;

    public static void increase(){
        race.incrementAndGet();
        intRace++;
        volatileRace++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args){
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i=0; i<THREADS_COUNT; i++){
            threads[i] = new Thread(()->{
                for (int j=0; j<10000; j++)
                    increase();
            });
            threads[i].start();
        }

        while (Thread.activeCount()>1){
            Thread.yield();
        }

        System.out.println("----race:"+race);
        System.out.println("----intRace:"+intRace);
        System.out.println("----volatileRace:"+volatileRace);
    }
}
