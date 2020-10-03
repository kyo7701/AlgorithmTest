package com.github.kyo7701.leetcode;

/**
 * Author:Mr.Cris
 * Date:2020-07-05 14:49
 *
 * @description 寻找两个正序数组的中位数
 *给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson4 {

    /**
     * 解法1
     * 合并两个有序数组,然后按奇偶性求中位数
     */
    //TODO bug fix -> 
    public double findMedianSortedArrays(int [] nums1 , int [] nums2) {
        double result = 0.0;
        int totalLen = nums1.length + nums2.length;
        if (nums1.length == 0) {
            return findMedianSortedArray(nums2);
        }
        if (nums2.length == 0) {
            return findMedianSortedArray(nums2);
        }
        int nums[] = new int[totalLen];
        int i = 0;
        int aStart = 0,bStart = 0;
        //通过两个指针 将两个数组进行合并
        while (i < totalLen  ) {
            if (aStart == nums1.length - 1 && bStart < nums2.length) {
                if (nums1[aStart] < nums2[bStart]) {
                    nums[i++] = nums1[aStart];
                    aStart ++;
                }else {
                    nums[i++] = nums2[bStart];
                    bStart ++;
                }
                continue;
            }
            if (bStart == nums2.length - 1 && aStart < nums1.length) {
                if (nums1[aStart] < nums2[bStart]) {
                    nums[i++] = nums1[aStart];
                    aStart ++;
                }else {
                    nums[i++] = nums2[bStart];
                    bStart ++;
                }
                continue;
            }
            if (aStart < nums1.length ) {
                if (bStart < nums2.length) {
                    if (nums1[aStart] < nums2[bStart]) {
                        nums[i] = nums1[aStart];
                        aStart++;
                    } else {
                        nums[i] = nums2[bStart];
                        bStart++;
                    }
                } else {
                    nums[i] = nums1[aStart];
                    aStart++;
                }

            } else {
                if (bStart < nums2.length) {
                    nums[i] = nums2[bStart];
                    bStart++;
                }
            }
            i++;

        }

        return findMedianSortedArray(nums);
    }

    public double findMedianSortedArray(int[] nums1) {
        int len = nums1.length;
        int mod = len % 2;
        double result;
        if(len == 0) {
            return 0d;
        }
        if(mod == 0) {
            result = (nums1[ len / 2 - 1 ] + nums1[ len / 2 ]) / 2.0;
        } else {
            result = nums1[len/2];
        }
        return result;
    }

    public static void main(String[] args) {
        Lesson4 lesson4 = new Lesson4();
        int [] nums1 = new int[]{0,7};
        int [] nums2 = new int[]{1,2,4,6,9};
        double result =  lesson4.findMedianSortedArrays(nums1,nums2);
        int a = 3 * 2;
        System.out.println(a / 2);
        System.out.println( (a + 1) / 2);
        System.out.println("lesson 4 result is " + result);
    }


}
