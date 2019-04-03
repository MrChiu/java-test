package com.example.lockTest.synchronizedtest;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 14:38 2019-04-03
 */
public class SynchronizedTest {

    final Object lock = new Object();

    public synchronized int add(int i){
        return i+1;
    }

    public int subtr(int i){
        synchronized (lock){
            return i-1;
        }
    }

    public synchronized static int multi(int i){
        return i*i;
    }

    public int divide(int i){
        synchronized (SynchronizedTest.class){
            return i/2;
        }
    }
}
