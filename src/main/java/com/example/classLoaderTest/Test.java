package com.example.classLoaderTest;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 15:38 2019-01-30
 */
public class Test {

    public static void main(String[] args)  {
        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("C:\\TEMP");
        Class<?> testClass = null;
        try {
            testClass = classLoader.loadClass("com.yh.invoice.main.entity.YhInvoice");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
