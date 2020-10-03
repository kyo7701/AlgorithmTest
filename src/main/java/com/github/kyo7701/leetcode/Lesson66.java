package com.github.kyo7701.leetcode;

/**
 * Author:Mr.Cris
 * Date:2020-10-03 21:20
 *
 * @description plus one
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson66 {

    public static void main(String[] args) {

    }


    /**
     * 错误的解法
     * 惯性思考，先把数组中的数组装成一个数，进行求和，求和后再打散成数组
     * 考虑到进位和数组扩容问题，却没有考虑到数值溢出问题，给一个超出int或者超出long的数字就GG了
     * @param
     * @return
     */
    public int[] plusOneSolution1(int[] digits) {
        //进位
        //溢出
        int length = digits.length;
        int source = 0;
        for(int i = 0; i < length ; i++) {
            source += digits[i] * Math.pow(10,length - i -1);
        }
        source += 1;
        int result = source / (int)Math.pow(10,length);
        if(result > 0) {
            //有进位
            length += 1;
        }
        int[] array = new int[length];
        for(int i = length-1; i >= 0 ; i--) {
            int mod = source % 10;
            source /= 10;
            array[i] = mod;
        }
        return array;
    }

    /**
     * 正确解法
     * 按位进行加运算，逐个考虑进位问题
     * 能够正确输出结果，思考简单，逻辑臃肿，性能较差
     * @param
     * @return
     */
    public int[] plusOneSolution2(int[] digits) {
        int length = digits.length;
        int carry = 0;
        int[] array = new int[length];
        for(int i = length - 1; i >= 0 ; i--) {
            if( i == length - 1) {
                int result = digits[i] + 1;
                int resultDigit = 0;
                if(result > 9) {
                    carry = 1;
                }else {
                    resultDigit = result;
                    carry = 0;
                }
                array[i] = resultDigit;
            }else {
                int resultDigit = 0;
                if(carry != 0) {
                    int result = digits[i] + carry;
                    if(result > 9) {
                        carry = 1;
                    }else {
                        resultDigit = result;
                        carry = 0;
                    }


                }else{
                    resultDigit = digits[i];
                }
                array[i] = resultDigit;

            }
        }
        if(carry > 0) {
            array = new int[length + 1];
            array[0] = 1;
        }
        return array;
    }

    /**
     * 正确解法2
     * 在没有进位的时候没有多余的运算，代码更为简洁，较不容易在第一时间想到
     * @param
     * @return
     */
    public int[] plusOneSolution3(int[] digits) {
        int length = digits.length;
        for (int i = length -1 ; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            //如果没有进位则直接返回原数组，不再向左进行计算
            //不过在原始数据上直接操作数据是一种非常不好的习惯
            if (digits[i] != 0) return digits;
        }
        digits = new int[length + 1];
        digits[0] = 1;
        return digits;
    }

}
