package com.example.cglibTest;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 11:03 2018-06-27
 */
public class CglibProxy implements MethodInterceptor {

    public Object getProxy(Class clazz){
        Enhancer enhancer = new Enhancer();
        //生成指定类对象的子类,也就是重写类中的业务函数，在重写中加入intercept()函数而已。
        enhancer.setSuperclass(clazz);
        //这里是回调函数，enhancer中肯定有个MethodInterceptor属性。
        //回调函数是在setSuperclass中的那些重写的方法中调用---猜想
        enhancer.setCallback(this);
        //创建这个子类对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName()+"执行之前做一些准备工作");
        //一不小心写成下面被注释一行代码了。 StackOverflowError
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println(method.getName()+"执行之后做一些准备的工作");

        if(method.getName().equals("method")){
            System.out.println(method.getName()+"独有的处理");
        }
        return result;
    }
}
