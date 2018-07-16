package com.bonaparte.service;

import com.bonaparte.common.RocketmqEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangmingquan on 2018/6/29.
 * rocketmq高级功能，rocketmq与阿里ons的区别
 */
@Service
public class RocketMqService {
    private static Log log = LogFactory.getLog(KafkaMqService.class);
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Autowired
    private TransactionMQProducer transactionMQProducer;
    private int i = 0;

    public void defaultSendMsg(){
        Message msg = new Message("",
                "",
                "",
                "12345".getBytes());

        try{
            defaultMQProducer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public String transactionSendMsg(){
        SendResult sendResult = null;
        Message msg = new Message("",
                "",
                "",
                "12345".getBytes());
        try{
            sendResult = transactionMQProducer.sendMessageInTransaction(msg, (Message msg1, Object arg) -> {
                int value = 1;

                // TODO 执行本地事务，改变value的值
                // ===================================================
                System.out.println("执行本地事务。。。完成");
                if (arg instanceof Integer) {
                    value = (Integer) arg;
                }
                // ===================================================

                if (value == 0) {
                    throw new RuntimeException("Could not find db");
                } else if ((value % 5) == 0) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else if ((value % 4) == 0) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }, 4);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return sendResult.toString();
    }

    public void sendMsgOrder() {
        Message msg = new Message("",
                "",
                "",
                "12345".getBytes());
        try {
            defaultMQProducer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println("MessageQueue" + arg);
                    int index = ((Integer) arg) % mqs.size();
                    return mqs.get(index);
                }
            }, i);// i==arg
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest1' && #event.msgs[0].tags=='Taga'")
    public void rockemqMsgListen(RocketmqEvent event) {
        try {

        } catch (Exception e) {

        }
    }
}
