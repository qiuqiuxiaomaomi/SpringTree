package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.Stack;

/**
 * Created by yangmingquan on 2018/7/24.
 * 堆栈，堆的研究
 */
@Service
public class StackService {

    public void stackCheck(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.pop();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.search("2"));
        stack.add(5);
        stack.add(6);
        stack.add(1,8);
        stack.addElement(9);
        System.out.println(stack);
    }
}
