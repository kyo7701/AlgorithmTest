package com.github.kyo7701.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mr.Cris
 * @since 2021-06-05 23:44
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson3 {

	/**
	 * 思路1 暴力解法
	 * 两重循环,一重循环指针指向数组起点,二重循环指向数组起点的下一位
	 * 每次二重循环指针向后挪动一位，遇到重复元素则记录当前子串长度(与最大子串长度比较，比最大值还大则替换)
	 * 终止当前二重循环 一重循环指针向后移动一位 直至一重循环移动到最后一位
	 *
	 * @param
	 * @return
	 */


	public int answer1(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] elements = s.toCharArray();
		int maxLength = 0;
		Set<Character> characterSet = new HashSet<>();
		for (int i = 0; i < elements.length; i++) {
			char ch = elements[i];
			characterSet.add(ch);
			for (int j = i + 1; j < elements.length; j++) {
				char chAtJ = elements[j];
				//遇到重复元素
				if (characterSet.contains(chAtJ)) {
					break;
				} else {
					characterSet.add(chAtJ);
					maxLength = updateLength(characterSet, maxLength);

				}
			}
			maxLength = updateLength(characterSet, maxLength);
			characterSet.clear();
		}
		return maxLength;

	}

	/**
	 * @param
	 * @return
	 */
	int answer2(String s) {
		return 0;
	}


	int updateLength(Set set, int maxLength) {
		int result = 0;
		if (set.size() > maxLength) {
			result = set.size();
		} else {
			result = maxLength;
		}
		return result;
	}

	public static void main(String[] args) {
		String s = "affffffbssdn";
		Lesson3 lesson3 = new Lesson3();
		int length = lesson3.answer1(s);
		System.out.println(length);
		System.out.println(lesson3.answer1("bbbbb"));
		System.out.println(lesson3.answer1("pwwkew"));
		System.out.println(lesson3.answer1(" "));
	}

}
