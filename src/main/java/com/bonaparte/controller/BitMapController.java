package com.bonaparte.controller;

import com.bonaparte.service.BitMapService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/19.
 */
@Api(value = "BitMapController", description = "BitMap Api")
@RestController
@RequestMapping("/bitmap")
public class BitMapController {
    @Autowired
    private BitMapService bitMapService;

    @ApiOperation(value = "BitMap操作接口",notes = "BitMap操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/basicop")
    public Object basicOp(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        bitMapService.bitSetOp();
        return map;
    }
}
