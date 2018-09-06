package com.bonaparte.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/9/6.
 */
@Configuration
@ConfigurationProperties(prefix = "bonaparte")
public class BaseProps {
    String domainUrl;

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }
}
