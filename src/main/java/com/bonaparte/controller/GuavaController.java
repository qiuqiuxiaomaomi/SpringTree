package com.bonaparte.controller;

import com.bonaparte.bean.Competition;
import com.bonaparte.entity.Charge;
import com.bonaparte.service.GuavaSeniorService;
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
@Api(value="Guava包操作", description = "Guava包操作")
@RestController
@RequestMapping("/guava")
public class GuavaController {
    @Autowired
    private GuavaSeniorService guavaSeniorService;

    @ApiOperation(value = "guava详情接口",notes = "guava变量详情",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回系统环境变量详情")
    })
    @RequestMapping("/detail")
    public Object getDetail(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        guavaSeniorService.preconditionCheck(true, null, null, 1, 2);
        guavaSeniorService.normalCheckMulti(new Competition());
        Charge charge = new Charge(1, 345.6);
        guavaSeniorService.optionalCheck(charge);
        Competition competition = new Competition();
        guavaSeniorService.optionalCheckMulti(competition);
        guavaSeniorService.consumerPrint();
        return map;
    }
}
