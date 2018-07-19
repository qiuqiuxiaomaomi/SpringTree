package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by yangmingquan on 2018/7/16.
 * 位图，
 * 布隆过滤器
 */
@Service
public class BitMapService {

    public void bitSetOp(){
        BitSet bitSet = new BitSet();
        List<Integer> list = new ArrayList<>();
        for(int i =0; i < 129; i++){
            bitSet.set(i, true);
        }
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(128));
    }
}
