package com.bonaparte.common;

import java.util.Stack;

/**
 * Created by yangmingquan on 2018/7/24.
 */
public class BinaryTree {
    private Node root = null;
    BinaryTree(int value){
        root = new Node(value);
        root.setLeftChild(null);
        root.setRightChild(null);
    }

    //查找
    public Node findKey(int value){
        Node current = root;
        while (true){
            if (value == current.value){
                return current;
            } else if (value < current.value){
                current = current.leftChild;
            } else if (value > current.value){
                current = current.rightChild;
            }

            if (current == null){
                return null;
            }
        }
    }

    //插入
    public boolean insert(int value){
        boolean flag = true;
        Node node = new Node(value);
        if (root == null){
            root = node;
            root.leftChild = null;
            root.rightChild = null;
        } else{
            Node current = root;
            Node parent = null;
            while(true){
                if (value < current.value){
                    parent = current;
                    current = current.leftChild;
                    if (current == null){
                        parent.leftChild = node;
                        break;
                    }
                } else if (value > current.value){
                    parent = current;
                    current = current.rightChild;
                    if (current == null){
                        parent = node;
                        break;
                    }
                } else{
                    flag = false;
                }
            }
        }
        return flag;
    }

    //中序遍历递归操作
    public void inOrderTraverse(Node node){
        if (node == null){
            return;
        }
        inOrderTraverse(node.getLeftChild());
        System.out.println(node.getValue());
        inOrderTraverse(node.getRightChild());
    }

    //中序遍历非递归操作
    public void inOrderByStack(Node node){
        Stack<Node> stack = new Stack<Node>();
        Node current = node;
        while(current != null && !stack.isEmpty()){
            while(current != null){
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()){
                current = stack.pop();
                System.out.println(current.getValue());
                current = current.rightChild;
            }
        }
    }

    //前序遍历递归操作
    public void preOrderTraverse(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.getValue());
        preOrderTraverse(node.getLeftChild());
        preOrderTraverse(node.getRightChild());
    }

    //前序遍历非递归操作
    public void preOrderByStack(){

    }

    //后续遍历递归操作
    public void postOrderTraverse(Node node){
        if (node == null){
            return;
        }
        postOrderTraverse(node.getLeftChild());
        postOrderTraverse(node.getRightChild());
        System.out.println(node.getValue());
    }

    //后续遍历非递归操作
    public void postOrderByStack(){

    }

    //获取最小值
    public int getMinValue(){
        Node current = root;
        while (true){
            if (current.leftChild == null){
                return current.value;
            }else{
                current = current.leftChild;
            }
        }
    }

    public int getMaxValue(){
        Node current = root;
        while (true){
            if (current.rightChild == null){
                return current.value;
            }else{
                current = current.rightChild;
            }
        }
    }

    //删除
    public boolean delete(int value){
        return true;
    }
}
