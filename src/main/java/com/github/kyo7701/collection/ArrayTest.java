package com.github.kyo7701.collection;

/**
 * Author:Mr.Cris
 * Date:2020-10-03 21:06
 *
 * @description
 */
public class ArrayTest {

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,45,6};
        array[2] ++;
        for (int i : array) {
            System.out.println(i);
        }
    }

}
