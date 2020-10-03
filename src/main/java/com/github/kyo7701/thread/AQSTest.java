package com.github.kyo7701.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {

    public static final ReentrantLock r = new ReentrantLock();
    public static final Condition c1 = r.newCondition();


    public void test3Thread() {
        new Thread(() -> {
            try {
                System.out.println("11" + Thread.currentThread().getName());
                r.lock();
                System.out.println("r lock 已经被thread 1获取");
                c1.await();
                System.out.println("111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("22" + Thread.currentThread().getName());
                r.lock();
                System.out.println("r lock 已经被thread 2获取");
                Thread.sleep(3000);
                System.out.println("2222");
                c1.signal();

                r.unlock();
                System.out.println("r lock 已经被thread 2释放");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        AQSTest aqsTest = new AQSTest();
        aqsTest.test3Thread();
        while (true) {
            try {
                Thread.sleep(5000);
                System.out.println("main loop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


