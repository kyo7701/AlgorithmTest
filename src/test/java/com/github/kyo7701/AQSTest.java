package com.github.kyo7701;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {

    private static final ReentrantLock r = new ReentrantLock();
    private static final Condition c1 = r.newCondition();
    private static final Condition c2 = r.newCondition();

    @Test
    public void test3Thread() {
        new Thread(() -> {
            try {
                r.lock();
                System.out.println("11" + Thread.currentThread().getName());
                System.out.println("thread 1尝试 获取lock");
                System.out.println("thread 1获取lock成功");
                c1.await();
                System.out.println("thread1 被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("22" + Thread.currentThread().getName());
                System.out.println("thread 2 尝试获取锁");
                r.lock();
                c1.signal();
                r.unlock();
                System.out.println("thread 2 释放lock");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}


