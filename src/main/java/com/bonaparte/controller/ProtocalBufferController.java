package com.bonaparte.controller;

import com.bonaparte.service.ProtocalBufferService;
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
 * Created by yangmingquan on 2018/7/11.
 */
@Api(value = "ProtocalBufferController", description = "Protocal Buffer 序列化工具")
@RestController
@RequestMapping("/pb")
public class ProtocalBufferController {
    @Autowired
    private ProtocalBufferService protocalBufferService;

    @ApiOperation(value = "pb操作接口",notes = "pb操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object info(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        protocalBufferService.basicOp();
        return map;
    }
}
