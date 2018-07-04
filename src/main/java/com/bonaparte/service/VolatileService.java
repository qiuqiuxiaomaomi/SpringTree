package com.bonaparte.service;

import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/7/2.
 * 内存的可见性，禁止指令的重排序
 */
@Service
public class VolatileService {
    private volatile int count = 0;

    public void muiltyThread(){
        for(int i =0; i<10; i++){
            new Thread(){
                public void run(){
                    count++;
                }
            }.start();
        }
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(count);
    }

}
