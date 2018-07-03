package com.bonaparte.controller;

import com.bonaparte.service.FastJsonSeniorService;
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
 * Created by yangmingquan on 2018/7/3.
 */
@Api(value = "FastJsonController",description = "FastJson 接口文档")
@RestController
@RequestMapping("/fastJson")
public class FastJsonController {
    @Autowired
    private FastJsonSeniorService fastJsonSeniorService;

    @ApiOperation(value = "系统环境变量详情接口",notes = "返回系统环境变量详情",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回系统环境变量详情")
    })
    @RequestMapping("/detail")
    public Object fastJsonBasic(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        fastJsonSeniorService.jsonConvert();
        return map;
    }
}
