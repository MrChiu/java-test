package com.example.lockTest;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 15:28 2018-07-02
 */
public class SubThread implements Runnable  {
    private Task task;

    public SubThread(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.sub();
    }
}
