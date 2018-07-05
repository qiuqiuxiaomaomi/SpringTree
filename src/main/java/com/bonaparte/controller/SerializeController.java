package com.bonaparte.controller;

import com.bonaparte.service.SerializeService;
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
 * Created by yangmingquan on 2018/7/5.
 */
@Api(value = "SerializeController", description = "序列化API")
@RestController
@RequestMapping("/serialize")
public class SerializeController {
    @Autowired
    private SerializeService serializeService;

    @ApiOperation(value = "序列化操作接口",notes = "序列化操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/serializeInfo")
    public Object serializeInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        serializeService.serializeCheck();
        return map;
    }
}
