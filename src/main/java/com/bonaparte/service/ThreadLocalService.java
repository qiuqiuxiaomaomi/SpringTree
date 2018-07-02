package com.bonaparte.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by yangmingquan on 2018/7/2.
 * ThreadLocal 代替synchronized 提高并发能力
 */
@Service
public class ThreadLocalService {
    public static final Log log = LogFactory.getLog(ThreadLocalService.class);
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public void setThreadLocal(){
        threadLocal.set( new Random(10).nextInt());
        log.info(threadLocal.get());
    }

    public Integer getThreadLocal(){
        return threadLocal.get();
    }
}
