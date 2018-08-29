package com.bonaparte.filter;

import com.alibaba.fastjson.JSONObject;
import com.bonaparte.annotation.CurrentMember;
import com.bonaparte.bean.Member;
import com.bonaparte.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangmingquan on 2018/8/29.
 */
public class UserAgrsRosolver implements HandlerMethodArgumentResolver {
    private int unauthorizedErrorCode = 401;
    private String httpHeaderName = "Authorization";

    private static final Logger logger = LoggerFactory.getLogger(UserAgrsRosolver.class);

    public UserAgrsRosolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSupport = parameter.getParameterType().isAssignableFrom(Member.class) && parameter.hasParameterAnnotation(CurrentMember.class);
        return isSupport;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = servletRequest.getHeader(this.httpHeaderName);
        if (token == null) {
            return null;
        }
        logger.debug("token-->" + token);
        JSONObject member = JsonUtil.decode(token);
        Member user = member.toJavaObject(Member.class);

        logger.debug("[resolveArgument]取出鉴权时存入的登录用户模型:{}", user);
        return user;
    }
}
