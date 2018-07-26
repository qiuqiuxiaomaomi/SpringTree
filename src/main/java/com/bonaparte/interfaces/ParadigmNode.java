package com.bonaparte.interfaces;

/**
 * Created by yangmingquan on 2018/7/16.
 * Java的泛型编程
 */
public class ParadigmNode<T> {

    private T data;  // 节点值
    private ParadigmNode<T> next;

    public ParadigmNode(T data){
        this.data = data;
        this.next = null;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public ParadigmNode<T> getNext(){
        return next;
    }

    public void setNext(ParadigmNode<T> next){
        this.next = next;
    }
}
