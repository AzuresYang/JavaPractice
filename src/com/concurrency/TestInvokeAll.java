package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 28029 on 2017/11/28.
 */
public class TestInvokeAll {
    static class Task implements Callable<String> {
        private int i ;
        public Task(int i){
            this.i = i;
        }
        @Override
        public String call() throws Exception{

            Thread.sleep(i*1000);

            int name = this.i;
            return Thread.currentThread().getName()+"执行任务:"+name;
        }
    }
    public static void tesInvokeAll(){
        int nThread = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Callable<String>> calls = new ArrayList<>();
        for(int i = 0 ; i<nThread; i++){
            calls.add(new Task(i));
        }

        try {
            List<Future<String>> futures = exec.invokeAll(calls,3000,TimeUnit.MILLISECONDS);
            for(Future<String> future:futures)
            {
                String result = null;
                try {
                    if(future.isDone() && !future.isCancelled())
                     result = future.get();
                } catch (ExecutionException e) {
                    System.out.println("取到一个空的");
                }
                if(result == null)
                    System.out.println("取到一个空结果");
                else
                    System.out.println(result);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        tesInvokeAll();
    }
}
