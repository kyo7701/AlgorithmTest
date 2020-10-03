package com.github.kyo7701.leetcode;

import java.util.*;

/**
 * Author:Mr.Cris
 * Date:2020-09-06 13:49
 *
 * @description
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson15  {

    public static void main(String[] args) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list1 = Arrays.asList(0,1,2,3,4);
        Integer[] array = {1,0,2,3,4};
        Set<Integer> set = new HashSet<>();
        List<Integer> list2 = Arrays.asList(array);


        result.add(list1);
        result.add(list2);
        System.out.println(list1.equals(list2));
        System.out.println(result.size());
    }


//    public List<List<Integer>> threeSum(int[] nums) {
//
//    }


}
