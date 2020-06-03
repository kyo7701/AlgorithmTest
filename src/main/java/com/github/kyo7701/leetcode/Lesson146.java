package com.github.kyo7701.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:Mr.Cris
 * Date:2020-02-29 18:14
 */
public class Lesson146 {

    static class LRUCache {

        class Node {
            public Node pre;
            public Node next;
            public Integer key;
            public Integer val;
            Node(int key,int val) {
                this.val = val;
                this.key = key;
            }
            Node() {

            }
        }

        private Map<Integer, Node> vMap;

        private Node head = new Node();
        private Node tail = new Node();
        private int capacity = 0;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            vMap = new HashMap<>();
        }

        private void updateNodePos(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            //更新原始前驱结点节点的后继指针为被更新节点的后继节点
            pre.next = next;
            //更新原始后继节点的前驱结点为被更新节点的前驱结点
            if(node.next != null)
                node.next.pre = pre;

            node.pre = head;
            node.next = head.next;

            if(head.next != null)
                head.next.pre = node;
            head.next = node;
        }

        public int get(int key) {
            if(vMap.get(key) == null) {
                return -1;
            }
            Node node = vMap.get(key);
            updateNodePos(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node ele = vMap.get(key);
            //秘钥不存在，写入并置于链表头
            if(ele == null) {
                //当容量不足,删除链表尾部元素
                removeElementIfReachesMaxCapcity();
                Node node = new Node(key,value);
                node.pre = head;
                node.next = head.next;
                if(head.next != null)
                    head.next.pre = node;
                head.next = node;
                vMap.put(key,node);
                if(vMap.keySet().size() == 1) {
                    tail.pre = node;
                    node.next = tail;
                }
            } else {
                //秘钥存在,更新元素位置至链表头
                ele.val = value;
                updateNodePos(ele);
            }

        }

        private void removeElementIfReachesMaxCapcity() {
            if(vMap.keySet().size() == capacity) {
                //删除链表尾部元素
                Node ele = tail.pre;
                Node pre = ele.pre;
                Node next = ele.next;
                pre.next = next;
                next.pre = pre;
                vMap.remove(ele.key);
            }
        }
    }

    public static void main(String[] args) {

//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.get(1);       // 返回  1
//        cache.put(3, 3);    // 该操作会使得密钥 2 作废
//        cache.get(2);       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得密钥 1 作废
//        cache.get(1);       // 返回 -1 (未找到)
//        cache.get(3);       // 返回  3
//        cache.get(4);       // 返回  4

        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        System.out.println("null");
        cache.put(2, 2);
        System.out.println("null");
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println("null");
        System.out.println(cache.get(2));// 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println("null");
        System.out.println(cache.get(1));// 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4


    }



}
