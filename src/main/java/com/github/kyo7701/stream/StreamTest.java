package com.github.kyo7701.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:Mr.Cris
 * Date:2020-03-23 22:08
 *
 * @description
 */
public class StreamTest {

    public interface TestInterface {
        public void helloWorld();
    }


    public static void sayHello(TestInterface testInterface) {
        testInterface.helloWorld();
    }


    public static void main(String[] args) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            resultList.add((int)(Math.random() * 10));
        }
        System.out.println(resultList.toString());
        List<Integer> filteredList = resultList.stream().filter(n -> n > 5).sorted(Comparator.comparing(Integer::intValue).reversed()).distinct().collect(Collectors.toList());
        System.out.println(filteredList.toString());
        sayHello(()-> System.out.println("hello world"));

    }


}
