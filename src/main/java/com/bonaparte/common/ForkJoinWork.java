package com.bonaparte.common;

import java.util.concurrent.RecursiveTask;

/**
 * Created by yangmingquan on 2018/7/11.
 */
public class ForkJoinWork extends RecursiveTask<Long>{
    private Long start;
    private Long end;

    public static final Long critical = 100000L;

    public ForkJoinWork(Long start, Long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //判断是否是拆分完毕
        Long length = end -start;
        if(length <= critical){
            Long sum = 0L;
            for(Long i = start; i <= end; i++){
                sum+=i;
            }
            return sum;
        }else{
            Long middle = (end - start) / 2;
            ForkJoinWork right = new ForkJoinWork(start, middle);
            right.fork();
            ForkJoinWork left = new ForkJoinWork(middle, end);
            left.fork();
            return right.join() + left.join();
        }
    }
}
