package com.bonaparte.controller;

import com.bonaparte.service.MysqlSeniorService;
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
@Api(value = "MysqlController", description = "Mysql操作")
@RestController
@RequestMapping("/mysql")
public class MysqlController {
    @Autowired
    private MysqlSeniorService mysqlSeniorService;

    @ApiOperation(value = "Mysql操作接口",notes = "Mysql操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object mysqlBasic(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        mysqlSeniorService.mysqlBasicOp();
        return map;
    }
}
