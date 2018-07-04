package com.bonaparte.controller;

import com.bonaparte.service.MybatisSeniorService;
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
@Api(value = "Mybatis接口", description = "Mybatis")
@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    @Autowired
    private MybatisSeniorService mybatisSeniorService;

    @ApiOperation(value = "Mybatis操作接口",notes = "Mybatis操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object mybatisInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        mybatisSeniorService.basicOp();
        return map;
    }
}
