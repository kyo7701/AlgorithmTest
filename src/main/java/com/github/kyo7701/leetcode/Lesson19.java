package com.github.kyo7701.leetcode;

/**
 * Author:Mr.Cris
 * Date:2021-03-02 19:04
 *
 * @description 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lesson19 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

    /**
     * 一次循环求长度,下次就知道该删除第几个元素了
     *
     * @param
     * @return
     */
    public static ListNode answer1(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode node = dummyHead;
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        //欲删除倒数第n个元素需找到该元素的前一个节点
        int index = length - n - 1;
        int i = 0;
        if (length == 1 && n == 1) {
            return null;
        }
        if (length == n) {
            index = n - 1;
        }
        node = head;
        while (node != null) {
            if (i == index) {
                ListNode removedNode = node.next;
                node.next = removedNode.next;
                removedNode.next = null;
                break;
            }
            node = node.next;
            i++;
        }
        return head;
    }

//    public static ListNode answer2(ListNode head, int n) {
//
//    }
//
//    public static ListNode answer3(ListNode head, int n) {
//
//    }


}
