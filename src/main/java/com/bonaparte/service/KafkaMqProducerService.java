package com.bonaparte.service;

import com.alibaba.fastjson.JSON;
import com.bonaparte.common.PonaparteMqMessage;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

/**
 * Created by yangmingquan on 2018/7/16.
 */
@Component
public class KafkaMqProducerService {
    @Resource(name = "kafkaTemplate")
    private KafkaTemplate kafkaTemplate;

    private static Log log = LogFactory.getLog(KafkaMqService.class);

    public Map<String, Object> sendMesForTemplate(PonaparteMqMessage message,
                                                  Long key,
                                                  String topic){
        MessageHeaders messageHeaders = message.getHeaders();
        Map<String, Object> msg = new HashedMap();
        msg.put("application", "Ponaparte");
        msg.put("initTime", messageHeaders.getTimestamp());
        msg.put("body", message.getMessageBody());
        message.setMessageBody(msg);
        log.info("Kafka 发送消息:" + JSON.toJSONString(message));
        Random random = new Random();
        Integer partionIndex = random.nextInt(3) + 1;
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partionIndex, key, message);
        return null;
    }
}
