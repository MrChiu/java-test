package com.example.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 9:40 2018-06-27
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public Object createProxyInstanceObject(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.getName().equals("sayHello")){
            System.out.println("Invoking sayHello");
        }else{
            System.out.println("others");
        }

        Object result = method.invoke(target, args);
        return result;
    }
}
