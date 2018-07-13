package com.bonaparte.controller;

import com.bonaparte.service.ZookeeperLock;
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
 * Created by yangmingquan on 2018/7/10.
 */
@Api(value = "ZookeeperController", description = "Zookeeper API")
@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {
    @Autowired
    private ZookeeperLock zookeeperLock;

    @ApiOperation(value = "Zookeeper操作接口",notes = "Zookeeper操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/basicOp")
    public Object basicOp(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        zookeeperLock.tryLock();
        zookeeperLock.unlock();
        return map;
    }
}
