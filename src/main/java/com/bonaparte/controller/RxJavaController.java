package com.bonaparte.controller;

import com.bonaparte.service.RxJavaService;
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
@Api(value = "Rxjava接口", description = "Rxjava操作接口")
@RestController
@RequestMapping("/rxjava")
public class RxJavaController{
    @Autowired
    private RxJavaService rxJavaService;

    @ApiOperation(value = "Mysql操作接口",notes = "Mysql操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object rxjavaInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        rxJavaService.rxJavaCheck();
        return map;
    }
}
