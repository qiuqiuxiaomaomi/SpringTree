package com.bonaparte.config;

import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by yangmingquan on 2018/7/10.
 */
@Configuration
public class ScheduledConfiguration {
    @Resource
    private DataSource dataSource;
    @Resource
    private StdScheduler quartzScheduler;

    @Bean(name = "quartzScheduler")
    public SchedulerFactoryBean quartzScheduler(){
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
        quartzScheduler.setDataSource(dataSource);
        quartzScheduler.setApplicationContextSchedulerContextKey("applicationContextKey");
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        quartzScheduler.setConfigLocation(resolver.getResource("classpath:quartz.properties"));
        return quartzScheduler;
    }
}
