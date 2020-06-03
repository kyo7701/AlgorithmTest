package com.github.kyo7701.sort;

/**
 * Author:Mr.Cris
 * Date:2020-06-03 15:18
 *
 * @description
 */
public class InsertSortAlgorithm implements Sortable {


    @Override
    public void sort(int[] data) {

        //选定第一位为已排序数列，从下一个元素开始排序,并逐个对新加入元素(数组后面元素)进行插入排序
        for (int i = 1; i < data.length; i++) {
            int newElement = data[i];
            int j = i - 1;
            //从后向前扫描,如果新插入的元素比已排序队列的待比较元素要小，则后移该元素
            //若新插入的元素比带比较元素大，则证明已经找到该元素的位置
            while (j >= 0 && newElement < data[j]) {
                data[j + 1] = data[j];
                j--;
            }
            //将该元素插入到该位置
            data[j + 1] = newElement;

        }
    }

    public void sort(int[] data,int start, int gap) {
        for (int i = start + gap; i < data.length; i += gap) {
            int newElement = data[i];
            int j = i - gap;
            while (j >= 0 && newElement < data[j]) {
                data[j + gap] = data[j];
                j -= gap;
            }
            data[j+gap] = newElement;
        }
    }


}
