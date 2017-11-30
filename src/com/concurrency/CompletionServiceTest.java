package com.concurrency;

import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 28029 on 2017/11/28.
 */
public class CompletionServiceTest {
    static class Task implements Callable<String> {
        private int i ;
        public Task(int i){
            this.i = i;
        }
        @Override
        public String call() throws Exception{

            Thread.sleep(1000);

            int name = this.i;
            return Thread.currentThread().getName()+"执行任务:"+name;
        }
    }
    public static void testFuture(){
        int nThread = 5;
        ExecutorService executor = Executors.newFixedThreadPool(nThread);
        List<Future<String>> futures = new ArrayList<>();
        for(int i = 0; i < nThread; i++){
            Future<String> future = executor.submit(new Task(i));
            futures.add(future);
        }
        long startTime = System.nanoTime();
        while(nThread > 0 ){
            for(Future<String> future :futures){
                String result = null;
                try {
//                    if(future.isDone()){
//                        result = future.get(0, TimeUnit.SECONDS);
//                        System.out.println(result);
//
//                    };
                    result = future.get(5 ,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    future.cancel(true);
                    System.out.println("取消一个任务");
                    futures.remove(future);
                    nThread--;
                    break;

                }
                if(result != null)
                {
                    System.out.println(result);
                    futures.remove(future);
                    nThread--;
                    break;
                }
            }

        }
        long useTime = System.nanoTime() - startTime;

        System.out.println("Use Time:"+useTime);
    }

    public static void testExecutorCompletionService()
    {
        int nThread = 100;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
        for(int i = 0; i < nThread; i++)
            completionService.submit(new Task(i));
        for(int i = 0; i < nThread; i++)
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

    }

    public static void testInvokeAll(){
        int nThead = 5;

    }
    public static void main(String[] args) {
        long startTime = System.nanoTime();
       // testFuture();
         testExecutorCompletionService();
        long useTime = System.nanoTime() - startTime;

        System.out.println("Use Time:"+useTime);
    }
}
