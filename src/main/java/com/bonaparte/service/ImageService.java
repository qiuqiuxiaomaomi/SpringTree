package com.bonaparte.service;

import com.bonaparte.constant.BaseProps;
import com.bonaparte.constant.ImageProps;
import com.bonaparte.util.ControllerUtil;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yangmingquan on 2018/9/6.
 * 图片的处理
 */
@Service
public class ImageService {
    @Autowired
    private ImageProps imageProps;
    @Autowired
    private BaseProps baseProps;

    /**
     * 通过url 读取远程图片数据流返回给前端
     * 微信外链的图片格式:  "http://`......?wx_fmt=jpg"
     * */
    public Object readRemoteImage(String remoteUrl) throws IOException {
        Map<String, Object> result = ControllerUtil.defaultSuccResult();
        Map<String, Object> urlMap = new HashedMap();
        if (!StringUtils.isEmpty(remoteUrl)) {
            //图片后缀名(jpeg,jpg,gif等)
            File serverFile = new File(imageProps.getAttachmentUrl() + System.currentTimeMillis() + "." + StringUtils.substringAfter(remoteUrl, "wx_fmt="));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(serverFile));
            try{
                URL url = new URL(remoteUrl);
                HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
                httpUrl.connect();
                BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
                IOUtils.copy(bis, out);
                String md5 = UUID.randomUUID() + "";
                urlMap.put("remoteUrl", remoteUrl);
                urlMap.put("localUrl", baseProps.getDomainUrl() + serverFile.getPath().replace(imageProps.getAttachmentUrl(), "/attachment/"));
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                out.flush();
            }finally {
                out.close();
            }
        }
        result.put("body", urlMap);
        return result;
    }
}
