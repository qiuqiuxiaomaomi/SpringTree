package com.bonaparte.service;

import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/7/16.
 * 排序算法
 */
@Service
public class SortAlgorithmService {

    //冒泡排序
    public void maopaoSort(int[] numbers){
        for(int i = 0; i < numbers.length; i++){
            for(int j = i; j < numbers.length; j++){
                int temp = 0;
                if (numbers[j] > numbers[j + 1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }
        System.out.println(numbers);
    }

    //快速排序
    public Integer quickSort(int[] numbers, int l, int r){
        int i = l;
        int j = r;
        int x = numbers[l];
        while  (i < j){
            while (i <j && numbers[j] >= x){
                j--;
            }
            if (i < j){
                numbers[i] = numbers[j];
                i++;
            }
            while (i <j && numbers[i] < x){
                i++;
            }
            if (i < j){
                numbers[j] = numbers[i];
                j++;
            }
        }
        numbers[i] = x;
        System.out.println(numbers);
        return i;
    }

    public void quickSortTest(int[] numbers, int l, int r){
        if (l < r){
            int i = quickSort(numbers, l, r);
            quickSort(numbers, l, i - 1);
            quickSort(numbers, i+1, r);
        }
    }
}
