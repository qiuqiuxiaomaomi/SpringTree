package com.bonaparte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})//不用springboot的默认文件配置
@EnableAsync
public class Ponaparte {

    public static void main(String[] args) {
        SpringApplication.run(Ponaparte.class, args);
    }


}

