package com.bonaparte.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by yangmingquan on 2018/6/30.
 */
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProp {
    private int    expireSeconds;
    private String clusterNodes;
    private int    commandTimeout;
    private int poolMaxIdle;
    private int poolMaxTotal;
    private int poolMaxWaitMillis;
    private boolean poolTestOnBorrow;
    private String masterHost;
    private int    masterPort;

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public int getPoolMaxIdle() {
        return poolMaxIdle;
    }

    public void setPoolMaxIdle(int poolMaxIdle) {
        this.poolMaxIdle = poolMaxIdle;
    }

    public int getPoolMaxTotal() {
        return poolMaxTotal;
    }

    public void setPoolMaxTotal(int poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }

    public int getPoolMaxWaitMillis() {
        return poolMaxWaitMillis;
    }

    public void setPoolMaxWaitMillis(int poolMaxWaitMillis) {
        this.poolMaxWaitMillis = poolMaxWaitMillis;
    }

    public boolean isPoolTestOnBorrow() {
        return poolTestOnBorrow;
    }

    public void setPoolTestOnBorrow(boolean poolTestOnBorrow) {
        this.poolTestOnBorrow = poolTestOnBorrow;
    }

    public String getMasterHost() {
        return masterHost;
    }

    public void setMasterHost(String masterHost) {
        this.masterHost = masterHost;
    }

    public int getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(int masterPort) {
        this.masterPort = masterPort;
    }
}
