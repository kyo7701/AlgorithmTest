package com.github.kyo7701.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Author:Mr.Cris
 * Date:2020-06-02 19:34
 *
 * @description
 */
public class QuickSortAlgorithm implements Sortable {

    public static void main(String[] args) {
        QuickSortAlgorithm quickSortDemo = new QuickSortAlgorithm();
        int[] data = new int[20];
        HashSet<Integer> preSort = new HashSet<>();
        HashSet<Integer> postSort = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            data[i] = (int) (Math.random() * 100);
            System.out.print(data[i] + " ");

        }
        List<Integer> preList = new ArrayList(Arrays.asList(data));
        preSort.addAll(preList);
        quickSortDemo.sort(data);
        List<Integer> postList = new ArrayList(Arrays.asList(data));
        postSort.addAll(postList);
        System.out.println(postSort.equals(postSort));
    }


    public void sort(int[] data) {
        if (data.length == 0 || data.length == 1) {
            return;
        }
        //选定基准值
        int head = 0;
        int tail = data.length - 1;
        sort(data, head, tail);
        System.out.println();
        for (int datum : data) {
            System.out.print(datum + " ");
        }
        System.out.println();
    }

    public void sort(int[] data, int head, int tail) {
        int startIndex = head;
        int endIndex = tail;
        //递归终止条件，如果分区中元素只有 0 或1 个则已经有序
        if (tail - head <= 1) {
            return;
        }
        int selectedElement = data[startIndex];
        if (head < tail) {
            //按基准值进行分区,左面的分区都小于等于基准值,右面的分区都大于基准值
            while (head < tail) {
                //从右向左找第一个小于等于基准值的元素
                if (data[tail] <= selectedElement) {
                    //找到后，再从左至右找第一个大于基准值的元素，并交换它们
                    if (data[head] > selectedElement) {
                        swap(data, head, tail);
                    } else {
                        head++;
                    }
                } else {
                    tail--;
                }
            }
            //head和tail指针重合,此时说明除了指针处所有元素已经交换完毕,则交换基准值和head指针处元素
            swap(data, startIndex, head);
            //分区后对左右两个子分区进行递归，重复上述操作
            sort(data, startIndex, head - 1);
            sort(data, head + 1, endIndex);
        }
    }

    public void swap(int[] data, int head, int tail) {
        if (data[head] > data[tail]) {
            //对两个元素进行交换
            data[head] = data[head] + data[tail];
            data[tail] = data[head] - data[tail];
            data[head] = data[head] - data[tail];

        }
    }

}
