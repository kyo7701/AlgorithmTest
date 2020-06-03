package com.github.kyo7701.leetcode;

import java.util.HashMap;

/**
 * @author created by mr_cris
 * @date 2019-05-09 16:24
 * @description 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Lesson1 {

    /**
     * 暴力解法
     * 逐个遍历每个元素，并查找是否存在与target- x值相等的元素
     * @param
     * @return
     */
    int[] answer1(int []nums,int target) {
        int[] result = new int[2];
        for (int i =0; i < nums.length; i ++) {
            int anotherElement = target - nums[i];
            if (anotherElement < 0) {
                continue;
            }
            for (int j = 0; j < nums.length; j ++) {
                if (j != i) {
                    if (anotherElement == nums[j]) {
                        if (i > j) {
                            result = new int[]{j,i};
                        }else {
                            result = new int[]{i,j};
                        }
                    }
                }
            }

        }
        return result;
    }

    /**
     *
     * @param
     * @return
     */
    int[] answer2(int []nums,int target) {
        int[] result = new int[2];
        HashMap<Integer,Integer> kvMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            kvMap.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int anotherELement = target - nums[i];
            if (kvMap.containsKey(anotherELement)) {
                Integer index = kvMap.get(anotherELement);
                if (index != null) {
                    if (index > i) {
                        result = new int[]{i,index};
                    }else {
                        result = new int[]{index,i};
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{2,7,11,5};
        int target = 9;
        Lesson1 lesson1 = new Lesson1();
        int [] result = lesson1.answer1(nums,target);
        for (int i : result) {
            System.out.println(i);
        }
        result = lesson1.answer2(nums,target);
        for (int i : result) {
            System.out.println(i);
        }
    }



}
