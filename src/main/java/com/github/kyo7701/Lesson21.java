package com.github.kyo7701;

/**
 * @author created by mr_cris
 * @date 2019-05-09 22:11
 * @description
 */
public class Lesson21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode headIt = head;
        ListNode i1 = l1;
        ListNode i2 = l2;
        while (i1 != null && i2 != null) {

            if (i1.val < i2.val) {
                headIt.next = i1;
                headIt = headIt.next;
                i1 = i1.next;

            } else if (i1.val == i2.val) {
                headIt.next = i1;
                headIt = headIt.next;
                i1 = i1.next;
                headIt.next = i2;
                headIt = headIt.next;
                i2 = i2.next;
            } else {
                headIt.next = i2;
                headIt = headIt.next;
                i2 = i2.next;
            }
        }
        if (i1 == null) {
            headIt.next = i2;
        }
        if (i2 == null) {
            headIt.next = i1;
        }

        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
