package com.bonaparte.controller;

import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/16.
 */
@Api(value = "ElasticsearchController", description = "Elasticsearch API")
@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    @ApiOperation(value = "查询接口",notes = "查询详情",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回es详情")
    })
    @RequestMapping("/query")
    public Object queryEs(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();

        return map;
    }
}
