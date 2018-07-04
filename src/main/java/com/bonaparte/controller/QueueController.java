package com.bonaparte.controller;

import com.bonaparte.service.QueueService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/4.
 */
@Api(value = "Queue队列操作API", description = "队列操作Api")
@RestController
@RequestMapping("/queue")
public class QueueController {
    @Autowired
    private QueueService queueService;

    @ApiOperation(value = "queue基本操作接口",notes = "queue操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/check")
    public Object queueBasic(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        queueService.testQueue();
        return map;
    }
}
