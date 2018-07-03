package com.bonaparte.controller;

import com.bonaparte.service.HashService;
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
@Api(value = "CommonController", description = "通用controller")
@RequestMapping("/common")
@RestController("/common")
public class CommonController {
    @Autowired
    private HashService hashService;

    @ApiOperation(value = "Hash操作接口",notes = "Hash操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/hashinfo")
    public Object getHashInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        hashService.hashCheck();
        return map;
    }
}
