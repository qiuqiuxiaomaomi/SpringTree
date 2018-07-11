package com.bonaparte.controller;

import com.bonaparte.service.LamdaProgramService;
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
 * Created by yangmingquan on 2018/6/30.
 */
@Api(value = "LamdaController", description="Lamda函数式编程")
@RestController
@RequestMapping("/lamda")
public class LamdaController {
    @Autowired
    private LamdaProgramService lamdaProgramService;

    @ApiOperation(value = "Lamda基础接口",notes = "Lamda基础接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/basicOp")
    public Object basicOp(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        lamdaProgramService.lamdaProgram();
        lamdaProgramService.consumerBasic();
        lamdaProgramService.lamdaPredicate();
        return map;
    }
}

