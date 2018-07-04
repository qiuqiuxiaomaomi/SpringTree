package com.bonaparte.controller;

import com.bonaparte.service.SocketService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/4.
 */
@Api(value = "Socket通信基础", description = "Socket通信基础")
@RestController
@RequestMapping("/socket")
public class SocketController {
    @Autowired
    private SocketService socketService;

    @ApiOperation(value = "Socket操作接口",notes = "Socket操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object socketInfo() throws IOException {
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        socketService.readFromUrl();
        return map;
    }

    @ApiOperation(value = "获取主机信息接口",notes = "获取主机信息接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/hostinfo")
    public Object hostInfo() throws IOException {
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        socketService.InetAddress();
        return map;
    }
}
