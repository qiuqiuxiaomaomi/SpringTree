package com.bonaparte.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bonaparte.service.EnvService;

import java.util.Properties;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Api(value = "EnvController", description = "系统环境变量相关api")
@RestController("/envd")
@RequestMapping("/envd")
public class EnvController {
    @Autowired
    private EnvService envService;

    @ApiOperation(value = "系统环境变量详情接口",notes = "返回系统环境变量详情",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回系统环境变量详情")
    })
    @RequestMapping("/detail")
    public Object getEnvObject(){
        Properties properties = envService.getSystemProperty();
        return properties;
    }
}
