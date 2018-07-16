package com.bonaparte.common;

import com.alibaba.fastjson.JSON;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;

/**
 * Created by yangmingquan on 2018/7/16.
 */
public class PonaparteMqMessage implements Message, Serializable{
    private Object messageBody;

    public PonaparteMqMessage(){
        super();
    }

    @Override
    public Object getPayload() {
        return JSON.toJSONString(this.getMessageBody());
    }

    @Override
    public MessageHeaders getHeaders() {
        return null;
    }

    public Object getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(Object messageBody) {
        this.messageBody = messageBody;
    }
}
