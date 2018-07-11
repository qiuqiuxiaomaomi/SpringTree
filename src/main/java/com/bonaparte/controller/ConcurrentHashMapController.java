package com.bonaparte.controller;

import com.bonaparte.service.ConcurrentService;
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
@Api(value = "ConcurrentController",description = "")
@RequestMapping("/concurrentHashMap")
@RestController
public class ConcurrentHashMapController {
    @Autowired
    private ConcurrentService concurrentService;

    @ApiOperation(value = "ConcurrentHashMap操作接口",notes = "ConcurrentHashMap操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object getInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        concurrentService.basicConcurrent();
        return map;
    }
}
