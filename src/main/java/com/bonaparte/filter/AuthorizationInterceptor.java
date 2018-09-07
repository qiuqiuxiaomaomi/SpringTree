package com.bonaparte.filter;

import com.alibaba.fastjson.JSON;
import com.bonaparte.annotation.Authorization;
import com.bonaparte.annotation.NoAuthorization;
import com.bonaparte.util.ControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/9/7.
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    // 日志系统
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权信息的无用前缀，默认为空
    private String httpHeaderPrefix = "";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    public void setHttpHeaderName(String httpHeaderName) {
        this.httpHeaderName = httpHeaderName;
    }

    public void setHttpHeaderPrefix(String httpHeaderPrefix) {
        this.httpHeaderPrefix = httpHeaderPrefix;
    }

    public void setUnauthorizedErrorMessage(String unauthorizedErrorMessage) {
        this.unauthorizedErrorMessage = unauthorizedErrorMessage;
    }

    public void setUnauthorizedErrorCode(int unauthorizedErrorCode) {
        this.unauthorizedErrorCode = unauthorizedErrorCode;
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 1. 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 2. 是否有NoAuthorization权限注解，如果有，则不需要检查token
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        NoAuthorization methodNoAnnotation = method.getAnnotation(NoAuthorization.class);
        Authorization methodAnnotation = method.getAnnotation(Authorization.class);

        if(methodNoAnnotation != null) {
            //为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
            request.setAttribute(REQUEST_CURRENT_KEY, null);
            return true;
        }

        // 3. 默认情况以及带有@Authorization权限注解，则需要检查token
        String token = request.getHeader(httpHeaderName);
        if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0) {
            token = token.substring(httpHeaderPrefix.length());
            //解析JWT Token
            logger.debug("Token:{}", token);
            // 检查是否是有效的token
            if (token != null)
            {
                response.setStatus(unauthorizedErrorCode);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
                //输出转换为Json字符串
                Map<String, Object> result = ControllerUtil.defaultErrResult();
                result.put("msg", "Invalid Token");
                writer.write(JSON.toJSONString(result, true));
                writer.close();
                return false;
            }

            {
                //判断是否有特定的权限
                return true;
            }
        }

        response.setStatus(unauthorizedErrorCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        //输出转换为Json字符串
        Map<String, Object> result = ControllerUtil.defaultErrResult();
        result.put("msg", unauthorizedErrorMessage);
        writer.write(JSON.toJSONString(result, true));
        writer.close();
        return false;
    }

    /**
     * 判断用户是否有用需要的权限
     * @param owned 以冒号分隔的权限列表
     * @param required 以冒号分隔的权限列表
     * @return
     */
    public boolean hasPermission(List<String> owned, String required) {
        logger.debug("[Owned]{}", owned);
        logger.debug("[Required]{}", required);

        if (required == null || required.length() <= 0) {
            return true;
        }

        if (owned == null || owned.size() == 0) {
            return false;
        }

        String[] requiredList = required.split(":");
        for(String requiredSingleAuth : requiredList) {
            for(String ownedPermission : owned) {
                if (ownedPermission.contains(requiredSingleAuth)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
