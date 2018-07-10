package com.bonaparte.bean;

import com.bonaparte.constant.QuartzConstants;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by yangmingquan on 2018/7/10.
 */
public class MyQuartzJobBean extends QuartzJobBean{
    public ApplicationContext applicationContext = null;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String taskId = (String) jobExecutionContext.getMergedJobDataMap().get(QuartzConstants.JOB_PARAM_KEY);
        try{
            //获取ApplicationContext
            applicationContext = getAppliCxt(jobExecutionContext);
            applicationContext.getBean("");
            //获取bean
            //调用bean的方法处理定时任务的执行逻辑
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ApplicationContext getAppliCxt(JobExecutionContext context){
        try {
            return (ApplicationContext)context.getScheduler().getContext().get("applicationContextKey");
        } catch (SchedulerException e) {
            System.out.println("获取spring的applicationContext失败!原因为"+e.getMessage());
        }
        return null;
    }
}
