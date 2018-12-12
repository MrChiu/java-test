package com.example.proxyTest;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 9:39 2018-06-27
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("Hello Wrold");
    }
}
