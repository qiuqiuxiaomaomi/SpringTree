package com.bonaparte.interfaces;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created by yangmingquan on 2018/7/26.
 */
public class ParadigmList<T> {
    private ParadigmNode<T> head;
    public ParadigmNode<T> getHead(){
        return head;
    }
    public void setHead(ParadigmNode<T> head){
        this.head = head;
    }

    public ParadigmList(T data){
        this.head = new ParadigmNode<>(data);
    }

    public void headInsert(T data){
        ParadigmNode<T> node = new ParadigmNode<>(data);
        node.setNext(head.getNext());
        head.setNext(node);
    }

    public void tailInsert(T data){
        ParadigmNode<T> node = new ParadigmNode<>(data);
        if (head.getNext() == null){
            head.setNext(node);
        }else{
            ParadigmNode<T> p = head;
            while (p.getNext() != null){
                p = p.getNext();
            }
            p.setNext(node);
        }
    }

    public boolean delete(T data){
        ParadigmNode<T> p = head.getNext();
        ParadigmNode<T> s = head;
        while (p != null){
            if (p.getData().equals(data)){
                s.setNext(p.getNext());
                return true;
            }
            s = p;
            p = p.getNext();
        }
        return false;
    }

    public void show(){
        ParadigmNode<T> link = head.getNext();
        while (link != null){
            System.out.println(link.getData() + "     ");
        }
    }
}
