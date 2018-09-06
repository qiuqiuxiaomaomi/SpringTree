package com.bonaparte.controller;

import com.bonaparte.constant.BaseProps;
import com.bonaparte.constant.ImageProps;
import com.bonaparte.util.ControllerUtil;
import com.bonaparte.util.HttpClientUtil;
import com.bonaparte.util.PatternUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by yangmingquan on 2018/9/6.
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    public static Pattern pattern = Pattern.compile("<h1>MD5: ([^<]*)</h1>");
    @Autowired
    private ImageProps imageProps;
    @Autowired
    private BaseProps baseProps;

    @ApiOperation(value = "图片上传接口",notes = "图片上传",httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "图片MD5", response = Map.class)
    })
    @RequestMapping("img/upload")
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        Map<String, Object> result = ControllerUtil.defaultSuccResult();
        try {
            File lf = new File(imageProps.getAttachmentUrl() + System.currentTimeMillis() + ".jpg");
            file.transferTo(lf);
            String rsp = HttpClientUtil.upload(imageProps.getUploadUrl(), "userfile", lf).toString();
            String md5 = PatternUtil.matcherGroupOne(pattern, rsp, 1);
            result.put("body", md5);
            if(!lf.delete()){
                lf.delete();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            result = ControllerUtil.defaultErrResult();
            result.put("msg", "上传图片失败");
        }
        return result;
    }

}
