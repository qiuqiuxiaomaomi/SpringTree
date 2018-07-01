package com.bonaparte.config;

import com.bonaparte.util.MongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by yangmingquan on 2018/7/1.
 */
@Configuration
public class MongoDBConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @SuppressWarnings("deprecation")
    @Bean
    public MongoDao mongoDao() {
        MongoDao mongoDao = new MongoDao();
        mongoDao.setMongoTemplate(mongoTemplate);
        return mongoDao;
    }
}
