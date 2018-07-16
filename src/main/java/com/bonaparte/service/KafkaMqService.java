package com.bonaparte.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by yangmingquan on 2018/6/29.
 * kafka的高级功能
 */

@Component
public class KafkaMqService {
    private static Log log = LogFactory.getLog(KafkaMqService.class);

    @KafkaListener(topics = {"${spring.kafka.consumer.topic}"})
    public Object ponaparteKafkaConsumer(String data){
        log.info("kafka消费topic消息");
        return kafkaProcess(data);
    }

    public Object kafkaProcess(String data){
        return null;
    }
}
