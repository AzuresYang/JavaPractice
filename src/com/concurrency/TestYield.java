package com.concurrency;

/**
 * Created by 28029 on 2017/11/26.
 */
public class TestYield implements Runnable {

    public void run() {

        System.out.println("first: " + Thread.currentThread().getName() );
        for(int i = 0; i < 30; i++)
            System.out.println("Thread: " + Thread.currentThread().getName() +"   Time:"+i);
        // 暂停当前正在执行的线程对象，并执行其他线程，就是进入就绪状态
      //  Thread.currentThread().yield();
        // 可能还会执行 本线程: 以下语句不一定紧接着上面的语句被执行，可能其他线程的先执行了
        System.out.println("second: " + Thread.currentThread().getName() );

    }

    public static void main(String[] args) {
        TestYield runn = new TestYield();
        Thread t1 = new Thread(runn);
        Thread t2 = new Thread(runn);
        Thread t3 = new Thread(runn);

        t2.setPriority(t2.getPriority()+1); //设置t2的线程优先级
        t1.start();
        t2.start();
        t3.start();

    }
}