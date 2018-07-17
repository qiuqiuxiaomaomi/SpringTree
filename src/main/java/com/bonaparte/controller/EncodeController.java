package com.bonaparte.controller;

import com.bonaparte.service.EnCodingService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/17.
 */
@Api(value = "EncodeController", description = "Encode API")
@RestController
@RequestMapping("/encode")
public class EncodeController {
    @Autowired
    private EnCodingService enCodingService;

    @ApiOperation(value = "编解码接口",notes = "编解码详情",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回编解码详情")
    })
    @RequestMapping("/query")
    public Object query() throws UnsupportedEncodingException {
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        enCodingService.readWriteFile();
        return map;
    }
}
