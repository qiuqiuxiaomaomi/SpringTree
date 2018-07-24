package com.bonaparte.controller;

import com.bonaparte.service.JpaService;
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
 * Created by yangmingquan on 2018/7/24.
 */
@Api(value = "JpaController", description = "JPA API接口")
@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    private JpaService jpaService;

    @ApiOperation(value = "JPA操作接口",notes = "JPA操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object info(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try{
            jpaService.jpaCheck();
        }catch (Exception e){
            map = ControllerUtil.defaultErrResult();
        }
        return map;
    }
}
