package com.bonaparte.controller;

import com.bonaparte.service.LamdaProgramService;
import com.bonaparte.service.LockService;
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
 * Created by yangmingquan on 2018/7/10.
 */
@Api(value = "LockController", description = "Lock API")
@RestController
@RequestMapping("/lock")
public class LockController {
    @Autowired
    private LockService lockService;

    @ApiOperation(value = "Lock基础接口",notes = "Lock基础接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/basicOp")
    public Object basicOp(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        lockService.redisAddLock("hello", "ponaparte");
        lockService.redisGet("hello");
        lockService.redisUnLock("hello");
        lockService.redisGet("hello");
        return map;
    }
}
