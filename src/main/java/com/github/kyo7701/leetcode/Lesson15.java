package com.github.kyo7701.leetcode;

import java.util.*;

/**
 * Author:Mr.Cris
 * Date:2020-09-06 13:49
 *
 * @description 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson15 {

    public static void main(String[] args) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list1 = Arrays.asList(0, 1, 2, 3, 4);
        Integer[] array = {1, 0, 2, 3, 4};
        Set<Integer> set = new HashSet<>();
        List<Integer> list2 = Arrays.asList(array);
        int[] nums = new int[]{34, 55, 79, 28, 46, 33, 2, 48, 31, -3, 84, 71, 52, -3, 93, 15, 21, -43, 57, -6, 86, 56, 94, 74, 83, -14, 28, -66, 46, -49, 62, -11, 43, 65, 77, 12, 47, 61, 26, 1, 13, 29, 55, -82, 76, 26, 15, -29, 36, -29, 10, -70, 69, 17, 49};
//        int[] nums = new int[]{0, -1, -1, 4, 2, 0};
        System.out.println(threeSum(nums).toString());
    }


    //想法1 三重循环
    //时间复杂度 o(n^3)
    //对三重循环中的重复情况进行剔除 
    //要找出三数之和为0 并且三数为不重复的三元组
    //最朴素且最好想的解法是说对数组进行三重循环，寻找所有可能的三元组，但三重循环必定时间复杂度、空间复杂度占用较高的情况
    // 三重循环 (每固定两层循环的数字、有且仅有一个数字满足 a + b + c = 0,继续移动上两层固定的数字 )
    //三重循环中势必产生很多重复的三元组 需要考虑 全0情况 和对结果集剔除 重复解实现也复杂 空间时间消耗也高

    //--------------------------------------------------------------------------------------------

    //想法2 初始数据排序 + 三重循环 + 条件限制 尽可能筛选重复情况
    //未脱离三重循环的 大框架 时间复杂度仍为 o(n^3)
    //--------------------------------------------------------------------------------------------
    //要想不重复先分析下什么样的案例是重复的
    // 从数组nums任取 3个数 a b c，若三个数各不相同 ,若发生重复，则重复的三元组必定为 a b c、b a c 、 c b a
    // 在三重循环中 每一重循环固定从最左侧开始 每次固定一个数字向右遍历
    // 得出若数组处于无序状态,很容易产生 b a c、 c b a 这样的重复三元组，若数组处于有序状态，则 b a c、c b a 这样的重复案例很容易就被剔除掉了
    // 然后我们再讨论 三个数字中有相同数值的情况
    //在有序数组中 任意两个数字数值相同是 可以存在满足条件的三元组的
    //例如 -8 4 4 / -4 -4 8
    //三个数值全相同且满足条件的 有且只有一种即为 0 0 0
    //这时可以得出 任意两重循环的数值是允许被相同的 需要避免的是同一重循环中 相邻两次扫描数值不能相同
    //另外 在数组有序的条件下 若满足 a + b + c = 0 则 若a > 0 则不存在这样的三元组使得题目成立

    //想法3 循环 + 双指针
    // a + b + c = 0
    //在想法2的基础上 若固定好上层循环中的a 和b 若有则只能有一个c能满足 上述等式
    // 此时若第二重循环b向右移动得到b'(有序数组b' > b),则c' 一定在c的左侧才能满足 上述等式 那么其实第三重循环应该向左侧移动 或者换句话说 第三层和第二层循环其实应该是并列关系
    // 或者再换句话说 有序数组中 一个指针从左向右扫 ,一个指针从右向左扫 这种情况优先考虑双指针中的对撞指针

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Integer secondRecord = null;
        Integer thirdRecord = null;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int internalLeft = i + 1;
            int internalRight = nums.length - 1;
            while (internalLeft < internalRight) {
                if (secondRecord != null && nums[internalLeft] == secondRecord) {
                    internalLeft++;
                    continue;
                }
                if (thirdRecord != null && nums[internalRight] == thirdRecord) {
                    internalRight--;
                    continue;
                }

                if (nums[i] + nums[internalLeft] + nums[internalRight] > 0) {
                    thirdRecord = nums[internalRight];
                    internalRight--;
                    continue;
                }
                if (nums[i] + nums[internalLeft] + nums[internalRight] < 0) {
                    secondRecord = nums[internalLeft];
                    internalLeft++;
                    continue;
                }
                if (nums[i] + nums[internalLeft] + nums[internalRight] == 0) {
                    List<Integer> answer = new ArrayList<>();
                    answer.add(nums[i]);
                    answer.add(nums[internalLeft]);
                    answer.add(nums[internalRight]);
                    result.add(answer);
                    secondRecord = nums[internalLeft];
                    thirdRecord = nums[internalRight];
                    internalLeft++;
                    internalRight--;

                }

            }
            secondRecord = null;
            thirdRecord = null;

        }
        return result;

    }


}
