package com.github.kyo7701.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:Mr.Cris
 * Date:2021-02-26 18:57
 *
 * @description 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson18 {

    // 与三数之和类似, 最朴素的解法就是四重循环
    //我们依照惯例 固定4个数中的1个数 则剩下的三个数则转化为三数之和的问题了
    //同理我们依然是可以用排序+双指针来解决问题

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                    //
                }
                int internalLeft = j + 1;
                int internalRight = nums.length - 1;
                Integer firstRecord = null;
                Integer secondRecord = null;
                while (internalLeft < internalRight) {

                    if (firstRecord != null && nums[internalLeft] == firstRecord) {
                        internalLeft++;
                        continue;
                    }
                    if (secondRecord != null && nums[internalRight] == secondRecord) {
                        internalRight--;
                        continue;
                    }


                    if (nums[i] + nums[j] + nums[internalLeft] + nums[internalRight] > target) {
                        secondRecord = nums[internalRight];
                        internalRight--;
                        continue;
                    }

                    if (nums[i] + nums[j] + nums[internalLeft] + nums[internalRight] < target) {
                        firstRecord = nums[internalLeft];
                        internalLeft++;
                        continue;
                    }

                    if (nums[i] + nums[j] + nums[internalLeft] + nums[internalRight] == target) {
                        List<Integer> answer = new ArrayList<>();
                        answer.add(nums[i]);
                        answer.add(nums[j]);
                        answer.add(nums[internalLeft]);
                        answer.add(nums[internalRight]);
                        firstRecord = nums[internalLeft];
                        secondRecord = nums[internalRight];
                        internalLeft++;
                        internalRight--;
                        result.add(answer);
                    }

                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, -1, -1, 1, 1, 2, 2};
        int target = 0;
        System.out.println(fourSum(nums, target));
    }

}
