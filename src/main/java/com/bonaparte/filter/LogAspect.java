package com.bonaparte.filter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * Created by yangmingquan on 2018/7/10.
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(public * com.bonaparte.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequest.getRequest();
        //记录详情内容
        System.out.println("URL: " + request.getRequestURL());
        System.out.println("HTTP_METHOD: " + request.getMethod());
        System.out.println("IP: " + request.getRemoteAddr());
        System.out.println("ARGS: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("" + request.getHeader("Authorization"));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        System.out.println("方法的返回值: " + ret);
    }

    @AfterThrowing("webLog()")
    public void throwss(JoinPoint joinPoint){
        System.out.println("方法异常时返回");
    }

    @Order(1)
    @After("webLog()")
    public void after1(JoinPoint joinPoint){
        System.out.println("方法最后执行1");
    }

    @Order(2)
    @After("webLog()")
    public void after2(JoinPoint joinPoint){
        System.out.println("方法最后执行2");
    }
}
