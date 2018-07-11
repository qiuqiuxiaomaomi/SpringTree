package com.bonaparte.service;

import com.bonaparte.common.ForkJoinWork;
import org.springframework.stereotype.Service;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Created by yangmingquan on 2018/6/30.
 */
@Service
public class ForkJoinService {

    public void forkJoin(){
        long x = 0;
        long y = 9999999999999l;
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinWork(x, y);
        long sum = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();
    }

    public void normalThread(){
        long x = 0;
        long y = 9999999999999l;
        long startTime = System.currentTimeMillis();
        for(long i = 0l; i<= y; i++){
            x+=i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public void lamdaThread(){
        long startTime = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 9999999999999l).parallel().reduce(0, Long::sum);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
