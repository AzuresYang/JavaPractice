package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 28029 on 2017/11/26.
 */
public class TestVolatile {
    public  int inc = 0;
    Lock lock = new ReentrantLock();

    public  void increase() {
        lock.lock();
        try {
            inc++;
            System.out.println("inc:"+inc);
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final TestVolatile test = new TestVolatile();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    System.out.println("Thread run!");
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

        System.out.println("All Over???");
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println("All Over");
        System.out.println(test.inc);
    }
}


