package com.example.concurrentTest;

import com.example.JavaTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CountDownLatch;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 10:05 2019-04-03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaTestApplication.class)
@WebAppConfiguration
public class ConcurrentTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void test() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        //模拟1000个线程并发
        CountDownLatchUtil countDownLatchUtil = new CountDownLatchUtil(1000);
        countDownLatchUtil.latch(() -> {
            helloService.sayHello(currentTimeMillis);
        });
    }
}
