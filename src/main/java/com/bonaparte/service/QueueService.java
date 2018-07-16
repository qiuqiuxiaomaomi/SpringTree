package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangmingquan on 2018/7/2.
 * 队列
 * MpscLinkedQueue
 */
@Service
public class QueueService {
    public static BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

    public void produce() throws InterruptedException {
        System.out.println("生产一个苹果");
        basket.put("an apple");
    }

    public String consumer() throws InterruptedException {
        System.out.println("消费一个苹果");
        String apple = basket.take();
        return apple;
    }

    public int getAppleNumber(){
        return basket.size();
    }

    public void testQueue() {
        QueueService queueService = new QueueService();
        class Producer implements Runnable {
            public void run() {
                try {
                    int i =1;
                    while(i <=3) {
                        queueService.produce();
                        Thread.sleep(300);
                        i++;
                    }
                } catch (InterruptedException e) {

                }
            }
        }

        class Consumer implements Runnable {
            public void run() {
                try {
                    int i = 1;
                    while(i <= 3) {
                        queueService.consumer();
                        Thread.sleep(1000);
                        i++;
                    }
                } catch (Exception e) {

                }
            }
        }

        ExecutorService service2 = Executors.newCachedThreadPool();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        service2.submit(producer);
        service2.submit(consumer);
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service2.shutdown();
    }
}
