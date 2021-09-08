package com.github.kyo7701.leetcode;

import java.util.Arrays;

/**
 * @author Mr.Cris
 * @since 2021-09-08 22:41
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson31 {

	public void nextPermutation(int[] nums) {
		if (nums.length == 1) {
			return;
		}
		//如果只有两个数,则下一个排列直接是交换两个数
		if (nums.length == 2) {
			int i = 0;
			int j = 1;
			nums[i] += nums[j];
			nums[j] = nums[i] - nums[j];
			nums[i] = nums[i] - nums[j];
			return;
		}
		//从后向前找第一个相邻升序对i,j
		//此时[j,end)一定是降序,(因为之前一直没有发现升序对)
		//找到升序对后,代表发现一个左侧较低位的"小数" i
		//此时从[j,end)区间寻找第一个比i大的数k 交换nums[i] nums[k]
		//此时排列变大了,但是还未满足紧邻的下一个排列，所以将[j,end)区间进行升序排序
		//如果第一步升序对没有找到,证明当前排列本身就是升序排列的，则将排列本身逆序排列
		int i = nums.length - 2;
		int j = nums.length - 1;
		boolean hasNext = false;
		while (i >= 0 && j >= 1) {
			if (nums[i] < nums[j]) {
				//找到升序对
				hasNext = true;
				for (int k = nums.length - 1; k >= j; k--) {
					if (nums[i] < nums[k]) {
						//交换 并对j后面的数进行升序排序
						nums[i] += nums[k];
						nums[k] = nums[i] - nums[k];
						nums[i] = nums[i] - nums[k];
						//设置外层循环终止，并退出内层循环
						i = 0;
						break;
					}
				}
			}
			i--;
			j--;
		}
		// 对选定空间排序
		int start;
		int end = nums.length;
		if (hasNext) {
			start = j + 1;

		} else { // 对整个空间反转
			start = 0;
//			Arrays.sort(nums, start, end);
//			sort(nums, start, end, true);
		}
		Arrays.sort(nums, start, end);

	}

	//对数组选定区间进行排序,冒泡 升序
	void sort(int[] array, int start, int end, boolean asc) {
		for (int i = start; i < end + 1; i++) {
			for (int j = i + 1; j < end + 1; j++) {
				if (array[i] > array[j]) {
					if (asc) {
						array[i] += array[j];
						array[j] = array[i] - array[j];
						array[i] = array[i] - array[j];
					}

				} else if (array[i] < array[j]) {
					if (!asc) {
						array[i] += array[j];
						array[j] = array[i] - array[j];
						array[i] = array[i] - array[j];
					}
				}


			}
		}
	}

	public static void main(String[] args) {
		Lesson31 lesson31 = new Lesson31();
		int[] nums = new int[]{1, 2, 3};
		lesson31.nextPermutation(nums);
		printArray(nums);
		nums = new int[]{3, 2, 1};
		lesson31.nextPermutation(nums);
		printArray(nums);
		nums = new int[]{1, 2};
		lesson31.nextPermutation(nums);
		printArray(nums);
		nums = new int[]{1, 3, 2};
		lesson31.nextPermutation(nums);
		printArray(nums);


	}

	private static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

}
