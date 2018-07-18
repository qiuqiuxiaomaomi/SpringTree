package com.bonaparte.controller;

import com.bonaparte.service.FaceCheckService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/17.
 * 腾讯云人脸识别
 */
@Api(value = "MyqCloudDetectController", description = "腾讯云人脸识别API")
@RestController
@RequestMapping("/myqcloud")
public class MyqCloudDetectController {
    @Autowired
    private FaceCheckService faceCheckService;

    @ApiOperation(value = "人脸检测接口",notes = "人脸检测接口",httpMethod = "GET")
    @ApiImplicitParam(name = "data", value = "人脸检测接口", required = false, dataType = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/facecheck")
    public Object faceDetect(String data){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try {
            Object object= faceCheckService.faceCheck("702b657a9305890c25e2abd87e2b070f");
            map.put("body", object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
