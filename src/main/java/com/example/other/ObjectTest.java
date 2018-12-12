package com.example.other;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 15:19 2018-08-08
 */
public class ObjectTest {

    public static void main(String[] args){
        Demo demo1 = new Demo();
        demo1.setId("1");
        demo1.setAge(13);
        demo1.setName("小明");
        demo1.setJob("工程师");

        Demo oldDemo = demo1;

        demo1.setJob("测试人员");

//        System.out.println("--demo1--"+demo1.toString());


        System.out.println("--oldDemo--"+object2Map(oldDemo));

        Demo newDemo = map2Object(object2Map(oldDemo), Demo.class);
        System.out.println("--newDemo--"+object2Map(newDemo));

    }

    @Data
    static class Demo{

        private String id;

        private String name;

        private int age;

        private String job;
    }

    private static Map<String, Object> object2Map(Object obj){
        Map<String,Object> reMap = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(obj);
                reMap.put(field.getName(), o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return reMap;
    }

    /**
     * Map转成实体对象
     * @param map map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    private static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if(field.getType().getName().equals(Long.class.getName())){
                    field.set(obj, Long.parseLong(map.get(field.getName()).toString()));
                }else{
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}




