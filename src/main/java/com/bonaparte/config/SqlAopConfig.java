package com.bonaparte.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by yangmingquan on 2018/9/17.
 */
@Aspect
@Configuration
public class SqlAopConfig {
    private static Log log = LogFactory.getLog(SqlAopConfig.class);

    @AfterReturning("execution(* com.karakal.dao.mapper.*Mapper.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        log.info("Completed: " + joinPoint);
    }


    /**
     * 监控com.caiyi.financial.nirvana..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.karakal.dao.mapper.*Mapper.*(..))")
    private void pointCutMethod() {

    }

    /**
     * 声明环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();
        log.info("调用Mapper方法：参数：执行耗时：纳秒，耗时：毫秒\n" + pjp.getSignature().toString() + "; " + Arrays.toString(pjp.getArgs()) +
                "; " + String.valueOf(end - begin) + "; " + String.valueOf((end - begin) / 1000000));
        return obj;
    }
}
