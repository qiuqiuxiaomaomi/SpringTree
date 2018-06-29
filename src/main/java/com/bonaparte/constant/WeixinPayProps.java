package com.bonaparte.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置属性
 * Created by yangmingquan on 2018/1/23.
 */
@Configuration
@ConfigurationProperties(prefix = "weixin")
public class WeixinPayProps {
    public String mchId;
    public String key;
    public String appId;
    public String wxUnifiedorder;
    public String notifyUrl;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxUnifiedorder() {
        return wxUnifiedorder;
    }

    public void setWxUnifiedorder(String wxUnifiedorder) {
        this.wxUnifiedorder = wxUnifiedorder;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
