package com.bonaparte.config;

import com.bonaparte.common.RocketmqEvent;
import com.bonaparte.constant.RocketmqProps;
import com.bonaparte.service.GuavaSeniorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.rmi.PortableRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangmingquan on 2018/7/16.
 */
@Configuration
@EnableConfigurationProperties(RocketmqProps.class)
public class RocketMqConfig{
    private final static Log log = LogFactory.getLog(GuavaSeniorService.class);
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private RocketmqProps rocketmqProps;
    private static boolean isFirstSub = true;
    private static long startTime = System.currentTimeMillis();

    @Bean
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(rocketmqProps.getProducerGroupName());
        producer.setNamesrvAddr(rocketmqProps.getNamesrvAddr());
        producer.setInstanceName(rocketmqProps.getProducerInstanceName());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);

        producer.start();
        return producer;
    }

    @Bean
    public TransactionMQProducer transactionMQProducer() throws MQClientException{
        TransactionMQProducer producer = new TransactionMQProducer(rocketmqProps.getTransactionProducerGroupName());
        producer.setNamesrvAddr(rocketmqProps.getNamesrvAddr());
        producer.setInstanceName(rocketmqProps.getTransactionProducerInstanceName());
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.setCheckThreadPoolMinSize(2);
        producer.setCheckThreadPoolMaxSize(2);
        producer.setCheckRequestHoldMax(2000);
        producer.start();
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketmqProps.getConsumerGroupName());
        consumer.setNamesrvAddr(rocketmqProps.getNamesrvAddr());
        consumer.setInstanceName(rocketmqProps.getConsumerInstanceName());
        if (rocketmqProps.isConsumerBroadcasting()) {
            consumer.setMessageModel(MessageModel.BROADCASTING);
        }
        consumer.setConsumeMessageBatchMaxSize(
                rocketmqProps.getConsumerBatchMaxSize() == 0 ? 1 : rocketmqProps.getConsumerBatchMaxSize());// 设置批量消费，以提升消费吞吐量，默认是1
        /**
         * 订阅指定topic下tags
         */
        List<String> subscribeList = rocketmqProps.getSubscribe();
        for (String sunscribe : subscribeList) {
            consumer.subscribe(sunscribe.split(":")[0], sunscribe.split(":")[1]);
        }
        if (rocketmqProps.isEnableOrderConsumer()) {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeOrderlyContext context) -> {
                try {
                    context.setAutoCommit(true);
                    msgs =filter(msgs);
                    if(msgs.size()==0) return ConsumeOrderlyStatus.SUCCESS;
                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                // 如果没有return success，consumer会重复消费此信息，直到success。
                return ConsumeOrderlyStatus.SUCCESS;
            });
        } else {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
                try {
                    msgs=filter(msgs);
                    if(msgs.size()==0) return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                // 如果没有return success，consumer会重复消费此信息，直到success。
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);// 延迟5秒再启动，主要是等待spring事件监听相关程序初始化完成，否则，回出现对RocketMQ的消息进行消费后立即发布消息到达的事件，然而此事件的监听程序还未初始化，从而造成消息的丢失
                    /**
                     * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
                     */
                    try {
                        consumer.start();
                    } catch (Exception e) {
                        log.info("RocketMq pushConsumer Start failure!!!.");
                        log.error(e.getMessage(), e);
                    }
                    log.info("RocketMq pushConsumer Started.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        return consumer;
    }

    private List<MessageExt> filter(List<MessageExt> msgs){
        if(isFirstSub&&!rocketmqProps.isEnableHisConsumer()){
            msgs =msgs.stream().filter(item ->startTime - item.getBornTimestamp() < 0).collect(Collectors.toList());
        }
        if(isFirstSub && msgs.size()>0){
            isFirstSub = false;
        }
        return msgs;
    }
}