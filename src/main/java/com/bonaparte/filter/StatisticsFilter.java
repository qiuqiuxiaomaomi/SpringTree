package com.bonaparte.filter;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yangmingquan on 2018/6/29.
 */
public class StatisticsFilter implements Filter {
    private static Log log = LogFactory.getLog("StatisticsFilter");

    public StatisticsFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String url = httpRequest.getRequestURI();
        long startTime = System.currentTimeMillis();
        boolean var20 = false;

        try {
            var20 = true;
            chain.doFilter(request, response);
            var20 = false;
        } finally {
            if(var20) {
                long var14 = System.currentTimeMillis();
                long time = var14 - startTime;
                String clientIp = httpRequest.getHeader("x-forwarded-for");
                if(clientIp == null) {
                    clientIp = httpRequest.getRemoteAddr();
                }

                log.info("客户端IP：" + clientIp + "" + ", 请求地址：" + url + ", 响应时间：" + time + "ms" + ", 请求参数：" + JSON.toJSONString(request.getParameterMap()));
            }
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        String clientIp = httpRequest.getHeader("x-forwarded-for");
        if(clientIp == null) {
            clientIp = httpRequest.getRemoteAddr();
        }

        log.info("客户端IP：" + clientIp + "" + ", 请求地址：" + url + ", 响应时间：" + time + "ms" + ", 请求参数：" + JSON.toJSONString(request.getParameterMap()));
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}
