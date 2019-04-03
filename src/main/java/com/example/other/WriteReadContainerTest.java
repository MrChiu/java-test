package com.example.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qiudong
 * @description: 读写容器分离，避免锁竞争
 * @date: Created in 18:02 2019-03-19
 */
public class WriteReadContainerTest {

    public static void main(String[] args){

        TaskService task = new TaskService();

        task.putRequest("write01");
        task.putRequest("write02");
        task.putRequest("write03");
        task.putRequest("write04");

        task.swapRequests();

        task.putRequest("write05");
        task.remove("write01");


        task.toString();
    }


    /**
     * 任务服务线程
     */
    static class TaskService implements Runnable {
        //写容器
        private volatile List<String> writeList = new ArrayList<>();
        //读容器
        private volatile List<String> readList = new ArrayList<>();

        //添加任务请求
        public void putRequest(String request){
            synchronized (this.writeList){
                writeList.add(request);
                System.out.println("----写入任务------"+request);
            }
        }

        //移除任务
        public void remove(String value){
            readList.remove(value);
            System.out.println("----处理任务------"+value);
        }

        //交换读写容器
        public void swapRequests(){
            List<String> temp = writeList;
            this.writeList = readList;
            this.readList = temp;
        }

        @Override
        public String toString(){
            System.out.println("---writeList"+writeList.toString());

            return null;
        }

        @Override
        public void run() {

            doWork();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                this.swapRequests();
            }

        }

        private void doWork(){
            synchronized (this.readList){
                if(!this.readList.isEmpty()){
                    System.out.println("---readList"+readList.toString());
                    this.readList.clear();
                }
            }
        }

    }

}
