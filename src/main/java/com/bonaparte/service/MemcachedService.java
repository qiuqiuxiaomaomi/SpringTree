package com.bonaparte.service;

import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by yangmingquan on 2018/6/29.
 * memcached 高级功能
 */
@Service
public class MemcachedService {
    @Autowired
    private MemCachedClient memCachedClient;

    public void basicOp() throws InterruptedException{
        boolean flag = memCachedClient.set("a", 1);
        Object a = memCachedClient.get("a");
        System.out.println(a);

        memCachedClient.set("b", 1, new Date(3000));
        Thread.sleep(3000);
        Object b = memCachedClient.get("b");
        System.out.println(b);
    }
}
