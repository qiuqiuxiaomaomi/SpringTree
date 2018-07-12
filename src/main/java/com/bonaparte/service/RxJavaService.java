package com.bonaparte.service;

import org.springframework.stereotype.Service;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by yangmingquan on 2018/6/29.
 * 线程间的消息机制
 */
@Service
public class RxJavaService {

    public void rxJavaCheck(){
        //数据发送方
        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("开始发送数据");
                subscriber.onNext("Hi, ponaparte!");
            }
        });

        //数据接收方
        Observer<String> reciever = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("数据处理完毕");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("数据处理错误");
            }

            @Override
            public void onNext(String s) {
                System.out.println("已经收到:" + s);
            }
        };

        //设置订阅关系
        sender.subscribe(reciever);
    }
}
