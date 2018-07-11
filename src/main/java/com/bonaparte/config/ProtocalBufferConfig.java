package com.bonaparte.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

/**
 * Created by yangmingquan on 2018/7/11.
 */
@Configuration
public class ProtocalBufferConfig {
    ProtobufHttpMessageConverter protobufHttpMessageConverter(){
        return new ProtobufHttpMessageConverter();
    }


}
