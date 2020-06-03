package com.github.kyo7701.sort;

/**
 * Author:Mr.Cris
 * Date:2020-06-03 11:09
 *
 * @description
 */
public class ShellSortAlgorithm implements Sortable {

    private InsertSortAlgorithm insertSortAlgorithm = new InsertSortAlgorithm();



    @Override
    public void sort(int[] data) {
        //对数列按照gap进行分组,当gap>=1时,对每组元素进行插入排序
        for (int gap = data.length /2; gap >= 1; gap/=2) {
            //分组遍历处理
            int groups = data.length / gap;
            for (int i = 0; i <= groups; i++) {
                //对每一组中的元素进行插入排序
                insertSortAlgorithm.sort(data,i,gap);
            }
        }
    }




}
