package com.bonaparte.config;

import com.alibaba.fastjson.JSON;
import com.bonaparte.util.ControllerUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/8/29.
 * 面向切面编程
 * 响应结果处理
 */
@Aspect
@Configuration
public class AopController {
    private static Log log = LogFactory.getLog(AopController.class);
    // 定义切点Pointcut
    @Pointcut("execution(* com.bonaparte.controller.EnvController + .*(..)) ")
    public void excudeService() {
    }

    @SuppressWarnings("unchecked")
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp)  {
        Object[] args = pjp.getArgs();
        try{
            log.info("开始调用接口"+pjp.getSignature().getDeclaringTypeName()+"." + pjp.getSignature().getName()+",对应参数为"+ JSON.toJSONString(args));
        }catch (Throwable e){
            log.error(e.getMessage());
        }
        Map<String, Object> outMap = ControllerUtil.defaultSuccResult();
        // result的值就是被拦截方法的返回值
        try {
            Object result = pjp.proceed();
            if (result instanceof Map) {
                outMap = (Map) result;
            } else {
                outMap.put("body", result);
            }
            //业务异常需要返回前台
        } catch (RuntimeException e) {
            log.info("出现业务异常："+ e.getMessage());
            e.printStackTrace();
            outMap.put("code", "error");
            outMap.put("msg", e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            outMap.put("code", "error");
            outMap.put("msg", "后台系统异常");
        }
        return outMap;
    }
}
