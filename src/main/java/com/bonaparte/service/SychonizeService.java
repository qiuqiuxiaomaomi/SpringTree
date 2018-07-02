package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/7/1.
 * 同步机制与lock
 */
@Service
public class SychonizeService {
    //sychonize 进入临界区
    public Integer numCount = 1;

    /**
     * 多线程环境下，搞并发环境下
     *   以下两种模式是会影响程序性能的
     * */
    public Integer getSpecChargeCount1(){
        synchronized (this){
            numCount++;
        }
        return numCount;
    }

    public synchronized Integer getSpecChargeCount2(){
        synchronized (this){
            numCount++;
        }
        return numCount;
    }

}
