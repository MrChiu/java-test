package com.example.fabicTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 16:38 2018-06-24
 */
public class FabicTask implements Callable<Long> {

    private int num;

    private final CyclicBarrier barrier;

    public FabicTask(int num, CyclicBarrier barrier) {
        super();
        this.num = num;
        this.barrier = barrier;
    }

    private long fabic(int n) {
        if(n < 0) {
            throw new NumberFormatException("不能小于0");
        }
        if(n == 1 || n == 2) {
            return 1;
        }
        return fabic(n - 1) + fabic(n - 2);
    }

    @Override
    public Long call() throws Exception {
        Long value = fabic(num);
        try {
            //  发信号等待结束
            this.barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        return value;
    }
}
