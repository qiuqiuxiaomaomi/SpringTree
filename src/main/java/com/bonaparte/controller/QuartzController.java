package com.bonaparte.controller;

import com.alibaba.fastjson.JSON;
import com.bonaparte.constant.ControllerConstants;
import com.bonaparte.dao.mapper.QuartzTaskMapper;
import com.bonaparte.entity.QuartzTask;
import com.bonaparte.service.QuartzService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * Created by yangmingquan on 2018/7/10.
 */
@Api(value = "QuartzController", description = "Quartz API")
@RestController
@RequestMapping("/quartz")
public class QuartzController {
    @Autowired
    public QuartzService quartzService;
    @Autowired
    public QuartzTaskMapper quartzTaskMapper;

    @ApiOperation(value = "保存定时任务接口", notes = "保存定时任务接口", httpMethod = "POST")
    @ApiImplicitParam(name = "quartzTask", value = "定时任务数据", required = true, dataType = "QuartzTask")
    @ApiResponse(code = 200, message = "定时任务数据")
    @RequestMapping(value = "/saveTask")
    public Object saveTask(@RequestBody QuartzTask quartzTask) throws RuntimeException{
        System.out.println(JSON.toJSONString(quartzTask));
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        if (quartzTask.getTaskId() != null) {
            quartzService.modifyJobTime(quartzTask.getTriggerName(), quartzTask.getTime(), quartzTask.getTaskId());
        } else {
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-","");
            quartzTask.setTaskId(uuid);
            quartzService.schedule(quartzTask.getTaskId(), quartzTask.getTriggerName(), quartzTask.getTime());
            quartzTaskMapper.insertSelective(quartzTask);
        }
        map.put(ControllerConstants.BODY_KEY,quartzTask.getTaskId());
        return map;
    }

    @ApiOperation(value = "重启一个触发器", notes = "重启一个触发器", httpMethod = "GET")
    @ApiImplicitParam(name = "taskId", value = "重启一个触发器", required = true, dataType = "String")
    @ApiResponse(code = 200, message = "重启一个触发器")
    @RequestMapping("/resumeTask")
    public Object resumeTask( String taskId) throws Exception{
        Map<String, Object> result = ControllerUtil.defaultSuccResult();
        try{
            System.out.println("重启任务:"+taskId);
            QuartzTask quartzTask = quartzTaskMapper.selectByPrimaryKey(taskId);
            if (quartzTask != null) {
                quartzService.resumeTrigger(quartzTask.getTriggerName());
            }
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof RuntimeException){
                result.put(ControllerConstants.MSG_KEY,e.getMessage());
            }else{
                result.put(ControllerConstants.MSG_KEY,"重启任务失败");
            }
        }

        return result;
    }

    @ApiOperation(value = "暂停一个触发器", notes = "暂停一个触发器", httpMethod = "GET")
    @ApiImplicitParam(name = "taskId", value = "暂停一个触发器", required = true, dataType = "String")
    @ApiResponse(code = 200, message = "暂停一个触发器")
    @RequestMapping("/pauseTask")
    public Object pauseTask( String taskId) throws Exception{
        Map<String, Object> result = ControllerUtil.defaultSuccResult();
        try{
            System.out.println("暂停任务:"+taskId);
            QuartzTask quartzTask = quartzTaskMapper.selectByPrimaryKey(taskId);
            if (quartzTask != null) {
                quartzService.pauseTrigger(quartzTask.getTriggerName());
            }
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof RuntimeException){
                result.put(ControllerConstants.MSG_KEY,e.getMessage());
            }else{
                result.put(ControllerConstants.MSG_KEY,"暂停任务失败");
            }
        }

        return result;
    }

    @ApiOperation(value = "删除一个触发器", notes = "删除一个触发器", httpMethod = "GET")
    @ApiImplicitParam(name = "taskId", value = "删除一个触发器", required = true, dataType = "String")
    @ApiResponse(code = 200, message = "暂停一个触发器")
    @RequestMapping("/deleteTask")
    public Object deleteTask( String taskId) throws Exception{
        Map<String, Object> result = ControllerUtil.defaultSuccResult();
        try{
            System.out.println("删除任务:"+taskId);
            QuartzTask quartzTask = quartzTaskMapper.selectByPrimaryKey(taskId);
            if (quartzTask != null) {
                quartzService.removeTrigger(quartzTask.getTriggerName());
                quartzTaskMapper.deleteByPrimaryKey(taskId);
            }
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof RuntimeException){
                result.put(ControllerConstants.MSG_KEY,e.getMessage());
            }else{
                result.put(ControllerConstants.MSG_KEY,"删除任务失败!");
            }
        }
        return result;
    }
}