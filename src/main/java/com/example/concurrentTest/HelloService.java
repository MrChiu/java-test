package com.example.concurrentTest;

import org.springframework.stereotype.Service;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 10:04 2019-04-03
 */
@Service
public class HelloService {

    public void sayHello(long timeMillis) {
        long time = System.currentTimeMillis() - timeMillis;
        if (time > 5000) {
            //超过5秒的打印日志输出
            System.out.println("time : "+time);
        }
        try {
            //模拟业务执行时间为1s
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
