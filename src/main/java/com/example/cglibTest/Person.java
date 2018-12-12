package com.example.cglibTest;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 11:13 2018-06-27
 */
public class Person {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        UserManager um = (UserManager)proxy.getProxy(UserManager.class);
        um.function();
        um.method();
    }
}
