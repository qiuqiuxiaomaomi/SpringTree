package com.bonaparte.controller;

import com.bonaparte.service.StringService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.Control;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/4.
 */
@Api(value="String接口API", description = "String接口API")
@RestController
@RequestMapping("/string")
public class StringController {
    @Autowired
    public StringService stringService;

    @ApiOperation(value = "String操作接口",notes = "String操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object getInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        stringService.testString1();
        return map;
    }
}
