package com.github.kyo7701.leetcode;

/**
 * Author:Mr.Cris
 * Date:2020-10-25 12:33
 *
 * @description
 * 实现strStr()函数。
 *
 * 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Lesson28 {

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr(haystack,needle));
    }

    public static int strStr(String haystack, String needle) {
        int left =0;
        int right = 0;
        int length = haystack.length();
        int strLen = needle.length();
        int breakLenth = 0;
        boolean firstSame = true;
        int firstSameIndex = 0;
        if((needle == "" && haystack == "")|| needle.equals(haystack)) {
            return 0;
        }
        if(needle.length() == 0 && haystack.length() != 0) {
            return 0;
        }
        if(needle.length() > haystack.length()) {
            return -1;
        }
        while(right < length) {
            if(haystack.charAt(right) == needle.charAt(0) ) {
                left = right;
                for(int i = right; i < right + strLen; i++) {
                    char leftCh = haystack.charAt(i);
                    char rightCh = needle.charAt(i - right);
                    if (i != right && leftCh == haystack.charAt(right) && firstSame) {
                        firstSameIndex = i;
                        firstSame = false;
                    }
                    if(leftCh != rightCh) {
                        if (left != right) {
                            left = firstSameIndex;
                            breakLenth = left - right;
                        } else {
                            breakLenth = i + 1;
                        }
                        break;
                    }
                    if(haystack.charAt(i) == needle.charAt(i - right) && i == right + strLen - 1 ) {

                        return left;
                    }
                }
                if(breakLenth == 0) {
                    breakLenth = strLen;
                }
                right += breakLenth;
            } else {
                right++;
            }

        }
        return -1;
    }
}


