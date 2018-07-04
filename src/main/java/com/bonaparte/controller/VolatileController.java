package com.bonaparte.controller;

import com.bonaparte.service.VolatileService;
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
 * Created by yangmingquan on 2018/7/4.
 */
@Api(value = "Volatile接口", description = "Volatile 内存可见性接口")
@RestController
@RequestMapping("/volatile")
public class VolatileController {
    @Autowired
    private VolatileService volatileService;

    @ApiOperation(value = "volatile操作接口",notes = "volatile操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object volatileInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        volatileService.muiltyThread();
        return map;
    }
}
