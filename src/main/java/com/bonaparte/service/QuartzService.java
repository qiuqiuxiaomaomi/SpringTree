package com.bonaparte.service;

import com.bonaparte.bean.MyQuartzJobBean;
import com.bonaparte.constant.QuartzConstants;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yangmingquan on 2018/7/1.
 * 定时任务
 *    Cron表达式
 *    与再代码中使用注解实现定时任务的关系，优劣势
 */
@Service
public class QuartzService {
    @Resource(name = "quartzScheduler")
    private StdScheduler scheduler;

    public void schedule(String taskId, String name,String cronExpression) throws RuntimeException {
        try{
            schedule(taskId, name, new CronExpression(cronExpression));
        }catch(Exception e){
            throw new RuntimeException("操作失败");
        }
    }

    public void addTrigger(String name,String cronExpression,String taskId) throws Exception{
        try{
            this.addschedule(new CronExpression(cronExpression), name,taskId);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("新增任务失败");
        }
    }

    public void schedule(String taskId,String tname, CronExpression cronExpression) throws RuntimeException {
        try{
            JobDetail jobDetail = JobBuilder.newJob(MyQuartzJobBean.class).withIdentity(tname, QuartzConstants.JOB_GROUP).build();
            jobDetail.getJobDataMap().put(QuartzConstants.JOB_PARAM_KEY,taskId);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(tname, Scheduler.DEFAULT_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                    .build();
            scheduler.scheduleJob(jobDetail,cronTrigger);
        }catch(Exception e){
            throw new RuntimeException("新增定时任务失败");
        }
    }

    public void addschedule(CronExpression cronExpression,String name,String taskId ) throws RuntimeException {
        try {
            JobDetail jobDetail = JobBuilder.newJob(MyQuartzJobBean.class).withIdentity(name,QuartzConstants.JOB_GROUP).build();
            jobDetail.getJobDataMap().put(QuartzConstants.JOB_PARAM_KEY, taskId);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(name,Scheduler.DEFAULT_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                    .build();

            scheduler.scheduleJob(jobDetail,cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("新增触发器失败");
        }
    }

    public void schedule(Date startTime, Date endTime) {
        schedule(startTime, endTime, 0);
    }

    public void schedule(String name, Date startTime, Date endTime) {
        schedule(name, startTime, endTime, 0);
    }

    public void schedule(Date startTime, Date endTime, int repeatCount) {
        schedule(null, startTime, endTime, 0);
    }

    public void schedule(String name, Date startTime, Date endTime,
                         int repeatCount) {
        schedule(name, startTime, endTime, 0, 0L);
    }

    public void schedule(String name, Date startTime, Date endTime,
                         int repeatCount, long repeatInterval) {
        if (name == null || name.trim().equals("")) {
            name = UUID.randomUUID().toString();
        }

        try {
            JobDetail jobDetail = JobBuilder.newJob(MyQuartzJobBean.class).withIdentity(name,QuartzConstants.JOB_GROUP).build();
            jobDetail.getJobDataMap().put(QuartzConstants.JOB_PARAM_KEY, "");
            scheduler.addJob(jobDetail, true);
            SimpleTrigger SimpleTrigger = TriggerBuilder.newTrigger().withIdentity(name,
                    Scheduler.DEFAULT_GROUP).startAt(startTime).endAt(endTime).withSchedule(
                    SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds((int) repeatInterval)//时间间隔
                            .withRepeatCount(repeatCount)//重复次数（将执行6次）
            ).build();
            scheduler.scheduleJob(SimpleTrigger);
            scheduler.rescheduleJob(new TriggerKey(name, Scheduler.DEFAULT_GROUP),
                    SimpleTrigger);

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 重启一个触发器
     *
     * @param triggerName
     * @throws RuntimeException
     * @throws SchedulerException
     */
    public boolean resumeTrigger(String triggerName) throws RuntimeException {
        if (triggerName == null || "".equals(triggerName)) {
            return false;
        }
        System.out.println(triggerName+":启用");
        try {
            this.scheduler.resumeTrigger(new TriggerKey(triggerName, Scheduler.DEFAULT_GROUP));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("重启任务失败");
        }
        return true;
    }

    /**
     * 删除一个触发器
     *
     * @param triggerName
     * @return
     * @throws RuntimeException
     * @throws SchedulerException
     */
    public boolean removeTrigger(String triggerName) throws RuntimeException  {
        try{
            if (triggerName == null || "".equals(triggerName)) {
                return false;
            }
            this.scheduler.pauseTrigger(new TriggerKey(triggerName, Scheduler.DEFAULT_GROUP));
            return this.scheduler.unscheduleJob(new TriggerKey(triggerName,
                    Scheduler.DEFAULT_GROUP));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("删除任务失败");
        }
    }

    /**
     * 暂停一个触发器
     *
     * @param triggerName
     * @throws RuntimeException
     * @throws SchedulerException
     */
    public boolean pauseTrigger(String triggerName) throws RuntimeException  {
        if (triggerName == null || "".equals(triggerName)) {
            return false;
        }
        System.out.println(triggerName+":禁用");
        try {
            this.scheduler.pauseTrigger(new TriggerKey(triggerName, Scheduler.DEFAULT_GROUP));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("禁用任务失败");
        }
        return true;
    }

    /**
     * 修改触发器的时间
     *
     * @param triggerName
     * @param time
     * @return
     * @throws RuntimeException
     * @throws SchedulerException
     * @throws ParseException
     */
    public boolean modifyJobTime(String triggerName, String time,String taskId) throws RuntimeException{
        try{
            this.removeTrigger(triggerName);
            this.addTrigger(triggerName, time, taskId);
            System.out.println("修改触发器");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("修改任务失败");
        }
    }
}
