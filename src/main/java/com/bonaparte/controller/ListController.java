package com.bonaparte.controller;

import com.bonaparte.service.ListService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/3.
 */
@Api(value = "ListController", description = "List操作")
@RestController
@RequestMapping("/list")
public class ListController {
     @Autowired
     private ListService listService;

    @ApiOperation(value = "List操作接口",notes = "List操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object getInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        listService.listBasicOp();
        return map;
    }
}
