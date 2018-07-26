package com.bonaparte.interfaces;

/**
 * Created by yangmingquan on 2018/7/26.
 */
public class ParadigmMain {

    public static void main(String[] args){
        ParadigmList<Integer> link = new ParadigmList<Integer>(-1);
        for(int i = 0; i< 5; i++){
            link.headInsert(i);
        }
        link.show();
        link.headInsert(6);
        link.tailInsert(9);
        link.show();
    }
}
