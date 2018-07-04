package com.bonaparte.controller;

import com.bonaparte.service.MultiThreadService;
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
@Api(value ="ThreadController", description = "线程操作")
@RestController
@RequestMapping("/thread")
public class ThreadController {
    @Autowired
    private MultiThreadService multiThreadService;

    @ApiOperation(value = "Thread操作接口",notes = "Thread操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object threadInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        multiThreadService.ThreadCheck();
        return map;
    }
}
