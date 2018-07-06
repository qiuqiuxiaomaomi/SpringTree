package com.bonaparte.controller;

import com.bonaparte.service.JvmSeniorService;
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
 * Created by yangmingquan on 2018/7/6.
 */
@Api(value = "JvmController", description = "JVM API")
@RestController
@RequestMapping("/jvm")
public class JvmController {
    @Autowired
    private JvmSeniorService jvmSeniorService;

    @ApiOperation(value = "JVM基础接口",notes = "JVM基础接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回JVM详情")
    })
    @RequestMapping("/detail")
    public Object jvmDetail(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        jvmSeniorService.jvmCheck();
        return map;
    }
}
