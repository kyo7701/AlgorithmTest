package com.github.kyo7701.leetcode;

import java.util.Arrays;

/**
 * Author:Mr.Cris
 * Date:2021-02-26 17:36
 *
 * @description 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson16 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        System.out.println(threeSumClosest(nums, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        Integer secondRecord = null;
        Integer thirdRecord = null;
        Integer result = null;
        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int internalLeft = i + 1;
            int internalRight = nums.length - 1;

            while (internalLeft < internalRight) {

                if (result == null) {
                    result = nums[i] + nums[internalLeft] + nums[internalRight];
                }

                if (secondRecord != null && nums[internalLeft] == secondRecord) {

                    internalLeft++;
                    continue;
                }
                if (thirdRecord != null && nums[internalRight] == thirdRecord) {
                    internalRight--;
                    continue;
                }

                if (nums[i] + nums[internalLeft] + nums[internalRight] > target) {
                    if (Math.abs(nums[i] + nums[internalLeft] + nums[internalRight] - target) < Math.abs(result - target)) {
                        result = nums[i] + nums[internalLeft] + nums[internalRight];
                    }
                    thirdRecord = nums[internalRight];
                    internalRight--;
                    continue;
                }
                if (nums[i] + nums[internalLeft] + nums[internalRight] < target) {
                    if (Math.abs(nums[i] + nums[internalLeft] + nums[internalRight] - target) < Math.abs(result - target)) {
                        result = nums[i] + nums[internalLeft] + nums[internalRight];
                    }
                    secondRecord = nums[internalLeft];
                    internalLeft++;
                    continue;
                }
                if (nums[i] + nums[internalLeft] + nums[internalRight] == target) {
                    return target;

                }

            }
            secondRecord = null;
            thirdRecord = null;

        }
        return result;
    }

}
