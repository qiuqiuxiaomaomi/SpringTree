package com.bonaparte.service;

import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.entity.Charge;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.apache.commons.lang.math.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yangmingquan on 2018/6/29.
 * lamda 函数是编程及高级功能
 */
@Service
public class LamdaProgramService {
    @Autowired
    private ChargeMapper chargeMapper;

    public void lamdaProgram(){
        List<Charge> list1 = new ArrayList<>();
        List<Charge> list2 = new ArrayList<>();
        for(int i = 0; i <= 2; i++){
            Charge charge = new Charge(i, 23.0);
            list1.add(charge);
        }

        for(int i = 0; i <= 2; i++){
            Charge charge = new Charge(i+2, 23.0);
            list2.add(charge);
        }
        //惰性求值
        list1.stream().filter(x -> x.getStatus() == 1);
        //及早求值
        list1 = list1.stream()
                .filter(x -> x.getStatus() != 1)
                .collect(Collectors.toList());
        list1.stream()
                .parallel()
                .forEach(x -> x.setMoney(1.0));
        list1.parallelStream()
                .map(x -> x.getMoney() + 1)
                .collect(Collectors.toList());

        //合并两个数组
        Stream.of(list1, list2)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        //找出缴费最多的那个缴费记录
        list1.stream()
                .max(Comparator.comparing(x->x.getMoney()))
                .get().getMoney();
        list1.stream()
        //找出缴费最少的那个缴费记录
                .min(Comparator.comparing(x->x.getMoney()))
                .get().getMoney();
        //判断是由有人缴费1块
        list1.stream()
                .anyMatch(x->x.getMoney()==1);
        //去重
        list1.stream()
                .distinct()
                .collect(Collectors.toList());
        //查找满足条件的数据
        Charge charge = new Charge(1,234.0);
        list1.stream()
                .findFirst()
                .equals(charge);

        //求缴费的平均值
        list1.stream()
                .mapToDouble(x->x.getMoney())
                .average();

        //根据缴费金额排序
        list1.stream()
                .sorted(Comparator.comparing(x->x.getMoney()))
                .collect(Collectors.toList());

        //Stream创建对象
        List<Charge> chargeList = new ArrayList<>();
        IntStream.range(0,10)
                .forEach(x ->{
                    Charge chargeTemp = new Charge(1, 12.0);
                    chargeTemp.setMoney((double) x);
                    chargeList.add(chargeTemp);
                });
    }

    public void consumerBasic() {
        Consumer c = (str) -> System.out.println(str + "home");
        c.accept("GitHub");
    }

    public void eval(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n:list){
            if (predicate.test(n)){
                System.out.println(n);
            }
        }
    }

    public void lamdaPredicate(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        eval(list, x -> x >3);
    }
}
