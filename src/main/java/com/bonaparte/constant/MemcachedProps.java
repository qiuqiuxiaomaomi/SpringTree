package com.bonaparte.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Configuration
@ConfigurationProperties(prefix = "memcached")
public class MemcachedProps {
    public String server;
    public Integer initConn;
    public Integer minConn;
    public Integer maxConn;
    public Integer maintSleep;
    public boolean nagle;
    public Integer socketTO;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getInitConn() {
        return initConn;
    }

    public void setInitConn(Integer initConn) {
        this.initConn = initConn;
    }

    public Integer getMinConn() {
        return minConn;
    }

    public void setMinConn(Integer minConn) {
        this.minConn = minConn;
    }

    public Integer getMaxConn() {
        return maxConn;
    }

    public void setMaxConn(Integer maxConn) {
        this.maxConn = maxConn;
    }

    public Integer getMaintSleep() {
        return maintSleep;
    }

    public void setMaintSleep(Integer maintSleep) {
        this.maintSleep = maintSleep;
    }

    public boolean isNagle() {
        return nagle;
    }

    public void setNagle(boolean nagle) {
        this.nagle = nagle;
    }

    public Integer getSocketTO() {
        return socketTO;
    }

    public void setSocketTO(Integer socketTO) {
        this.socketTO = socketTO;
    }
}
