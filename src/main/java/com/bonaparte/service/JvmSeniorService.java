package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Service
public class JvmSeniorService {

    public void jvmCheck(){
        //触发一次full gc
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i =0;i < 10000; i++){
            arrayList.add(i);
        }
        System.gc();
    }
}
