package com.example.other;

import java.util.concurrent.*;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 14:05 2018-08-03
 */
public class FutureTaskTest {
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(10000);
            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
        executor.shutdown();

    }

}
