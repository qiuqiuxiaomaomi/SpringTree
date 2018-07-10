package com.bonaparte.controller;

import com.bonaparte.service.MemcachedService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/10.
 */
@Api(value = "MemcachedController", description = "Memcached缓存Controller")
@RequestMapping("/memcached")
@RestController("/memcached")
public class MemcachedController {
    public static final Log logger = LogFactory.getLog(MemcachedController.class);
    @Autowired
    private MemcachedService memcachedService;

    @ApiOperation(value = "memcached操作接口",notes = "memcached操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object getInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try {
            memcachedService.basicOp();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return map;
    }
}
