package com.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 28029 on 2017/11/26.
 */
public class TestHarness {
    public long timeTasks( int nThreads, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0; i < nThreads ; i ++)
        {
            Thread t = new Thread()
            {
                public void run(){
                    try{
                        startGate.await();
                        try{
                            task.run();
                            }finally {
                            endGate.countDown();
                        }
                        }catch(InterruptedException ig){

                        }
                    }
                };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
    public static void main(String[] args) {

        TestHarness test = new TestHarness();
        Runnable r = () -> {
            System.out.println("hello");};
        try {
            long time = test.timeTasks(100, r);
            System.out.println("Time:"+time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
