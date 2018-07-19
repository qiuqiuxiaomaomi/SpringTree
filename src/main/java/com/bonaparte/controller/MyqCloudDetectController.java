package com.bonaparte.controller;

import com.bonaparte.service.FaceCheckService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    @ApiOperation(value = "人脸图片检测接口",notes = "人脸检测接口",httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/facecheck")
    public Object faceDetect(@RequestParam(value = "file", required = false) MultipartFile file){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try {
            Object object= faceCheckService.faceCheck(file);
            map.put("body", object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value = "人脸图片比对接口",notes = "人脸图片比对接口",httpMethod = "POST")
    @ApiImplicitParam(name = "data", value = "人脸图片比对接口", required = false, dataType = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/facecompare")
    public Object faceCompare(@RequestParam(value = "file", required = false) List<MultipartFile> fileList){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try {
            Object object = faceCheckService.faceCompare(fileList.get(0), fileList.get(1));
            map.put("body", object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value = "个体信息管理接口",notes = "个体信息管理接口",httpMethod = "POST")
    @ApiImplicitParam(name = "data", value = "个体信息管理接口", required = false, dataType = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/newperson")
    public Object newPerson(@RequestParam(value = "file", required = false) MultipartFile file){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try{
            Object object = faceCheckService.newPerson(file);
            map.put("body", object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value = "人脸检索接口",notes = "人脸检索接口",httpMethod = "POST")
    @ApiImplicitParam(name = "data", value = "人脸检索接口", required = false, dataType = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/identifyperson")
    public Object identifyPerson(@RequestParam(value = "file", required = false) MultipartFile file){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try{
            Object object = faceCheckService.identifyPerson(file);
            map.put("body", object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
