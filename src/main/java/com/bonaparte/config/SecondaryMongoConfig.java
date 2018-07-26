package com.bonaparte.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by yangmingquan on 2018/7/26.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.bonaparte.config",
                         mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
public class SecondaryMongoConfig {
    protected static final String MONGO_TEMPLATE="secondaryMongoTemplate";
}
