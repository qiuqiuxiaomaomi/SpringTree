package com.bonaparte.controller;

import com.bonaparte.service.MapService;
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
@Api(value = "MapController", description = "Map的基本操作")
@RestController
@RequestMapping(value = "/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @ApiOperation(value = "Map操作接口",notes = "Map操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object mapInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        mapService.mapBasicOperate();
        return map;
    }
}
