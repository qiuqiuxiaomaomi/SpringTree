package com.bonaparte.config;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yangmingquan on 2018/7/24.
 */
@Configuration
public class ElasticSearchConfig {
    @Value("${elasticsearch.cluster-nodes}")
    private String nodes;
    @Value("${elasticsearch.name}")
    private String name;


    @Bean
    public TransportClient elasticsearchClient() {
        String[] clusterNodes = nodes.split(",");
        TransportAddress[] transportAddresses = new TransportAddress[clusterNodes.length];
        for (int i=0;i<clusterNodes.length;i++) {
            String [] params = clusterNodes[i].split(":");
            try {
                transportAddresses[i] = new InetSocketTransportAddress(InetAddress.getByName(params[0]), Integer.parseInt(params[1]));
            } catch (NumberFormatException | UnknownHostException e) {
                e.printStackTrace();
            }
        }
        Settings settings = null;
        if(StringUtils.isEmpty(name)){
            settings = Settings.EMPTY;
        }else{
            settings = Settings.builder().put("cluster.name", name).build();
        }
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddresses(transportAddresses);
        return client;
    }
}
