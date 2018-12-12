package com.example.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BinaryOperator;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 11:35 2018-07-03
 */
public class ArrayTest {

    public static void main(String[] args){
        Integer[] array = {6,2,4,1};

        Arrays.stream(array).forEach(a -> {
            System.out.print(a+".");
        });

        System.out.println("");

        BinaryOperator<Integer> binaryOperator = (x, y) -> {
            return x+y;
        };

        Arrays.parallelPrefix(array, binaryOperator);

        Arrays.stream(array).forEach(a -> {
            System.out.print(a+".");
        });

        System.out.println("");

        List<Integer> list = new ArrayList<>();
        Spliterator<Integer> spliterator = Arrays.spliterator(array);
        spliterator.forEachRemaining(s -> {
            s++;
            list.add(s);
        });
        list.forEach(a -> {
            System.out.print(a+".");
        });

    }
}
