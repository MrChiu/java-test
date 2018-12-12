package com.example.proxyTest;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 9:37 2018-06-27
 */
public class MyDynamicProxyTest {

    public static void main(String[] args){
        Hello hello = new HelloImpl2();
        MyInvocationHandler handler = new MyInvocationHandler();
        //构造代码实例
        Hello proxyHello = (Hello) handler.createProxyInstanceObject(hello);
        //调用代理方法
        proxyHello.sayHello();
    }
}
