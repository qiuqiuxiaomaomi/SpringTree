package com.bonaparte.config;

import com.bonaparte.constant.MultipleMongoProperties;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by yangmingquan on 2018/7/26.
 */
@Configuration
public class MultipleMongoConfig {
    @Autowired
    private MultipleMongoProperties mongoProperties;

    @Primary
    @Bean
    @Qualifier(PrimaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate primaryMongoTemplate() throws Exception{
        return new MongoTemplate(primaryFactory(mongoProperties.getPrimary()));
    }

    @Bean
    @Qualifier(SecondaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate secondMongoTemplate() throws Exception{
        return new MongoTemplate(secondaryFactory(mongoProperties.getSecondary()));
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(MongoProperties mongoProperties) throws Exception{
        return new SimpleMongoDbFactory(new MongoClient(mongoProperties.getHost(), mongoProperties.getPort()), mongoProperties.getDatabase());
    }

    @Bean
    @Primary
    public MongoDbFactory secondaryFactory(MongoProperties mongoProperties) throws Exception{
        return new SimpleMongoDbFactory(new MongoClient(mongoProperties.getHost(), mongoProperties.getPort()), mongoProperties.getDatabase());
    }
}
