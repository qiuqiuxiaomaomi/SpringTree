package com.bonaparte.constant;

import io.swagger.models.auth.In;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmingquan on 2018/7/16.
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
public class RocketmqProps {
    private String namesrvAddr;
    private String producerGroupName;
    private String transactionProducerGroupName;
    private String consumerGroupName;
    private String producerInstanceName;
    private String consumerInstanceName;
    private String transactionProducerInstanceName;
    private String consumerBatchMaxSize;
    private boolean consumerBroadcasting;
    private boolean enableHisConsumer;
    private boolean enableOrderConsumer;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public String getTransactionProducerGroupName() {
        return transactionProducerGroupName;
    }

    public void setTransactionProducerGroupName(String transactionProducerGroupName) {
        this.transactionProducerGroupName = transactionProducerGroupName;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public String getProducerInstanceName() {
        return producerInstanceName;
    }

    public void setProducerInstanceName(String producerInstanceName) {
        this.producerInstanceName = producerInstanceName;
    }

    public String getConsumerInstanceName() {
        return consumerInstanceName;
    }

    public void setConsumerInstanceName(String consumerInstanceName) {
        this.consumerInstanceName = consumerInstanceName;
    }

    public String getTransactionProducerInstanceName() {
        return transactionProducerInstanceName;
    }

    public void setTransactionProducerInstanceName(String transactionProducerInstanceName) {
        this.transactionProducerInstanceName = transactionProducerInstanceName;
    }

    public boolean isConsumerBroadcasting() {
        return consumerBroadcasting;
    }

    public void setConsumerBroadcasting(boolean consumerBroadcasting) {
        this.consumerBroadcasting = consumerBroadcasting;
    }

    public boolean isEnableHisConsumer() {
        return enableHisConsumer;
    }

    public void setEnableHisConsumer(boolean enableHisConsumer) {
        this.enableHisConsumer = enableHisConsumer;
    }

    public boolean isEnableOrderConsumer() {
        return enableOrderConsumer;
    }

    public void setEnableOrderConsumer(boolean enableOrderConsumer) {
        this.enableOrderConsumer = enableOrderConsumer;
    }

    public String getConsumerBatchMaxSize() {
        return consumerBatchMaxSize;
    }

    public void setConsumerBatchMaxSize(String consumerBatchMaxSize) {
        this.consumerBatchMaxSize = consumerBatchMaxSize;
    }
}
