package com.concurrency.crawler;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 28029 on 2017/11/30.
 */
//爬虫的多线程保存
public class PoisionPill {
    private static final File POISON = new File("");
    private final File root;
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final CrawlerThead producer = new CrawlerThead();
    private final IndexerThread consumer = new IndexerThread();
    public PoisionPill(File root, BlockingQueue<File> queue, FileFilter fileFilter) {
        this.root = root;
        this.queue = queue;
        this.fileFilter = fileFilter;
    }
    public void start(){
        producer.start();
        consumer.start();
    }
    public void stop(){
        producer.interrupt();
    }
    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }
    private class CrawlerThead extends Thread{
        public void run(){
            try{
                crawl(root);
            }finally {
                while(true){
                    try{
                        queue.put(POISON);
                    }catch(InterruptedException el){
                        //重新尝试
                    }
                }
            }

        }
        //这个是读取文件的
        private void crawl(File root){

        }
    }
    private class IndexerThread extends Thread{
        public void run(){
            try {
                while(true) {
                    File file = null;
                    file = queue.take();
                    if (file == POISON)
                        break;
                    else
                        indexFile(file);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void indexFile(File file){}
    }

    public static void main(String[] args) {
    }
}
