package com.bonaparte.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bonaparte.constant.TencentCloudProps;
import com.bonaparte.util.TencentCloudUtil;
import com.google.common.collect.Lists;
import com.squareup.okhttp.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangmingquan on 2018/7/18.
 */
@Service
public class FaceCheckService {
    @Autowired
    private TencentCloudProps tencentCloudProps;

    /**
     * 人脸检测
     * param: 图片名称：MD5值
     * */
    public Object faceCheck(String imgAMd5) throws Exception {
        Map<String, Object> param = new HashedMap();
        param.put("appid", "1257128504");
        param.put("mode", 1);
        param.put("url", "https://ponaparte-1257128504.cos.ap-chengdu.myqcloud.com/123.jpg");
        Object object = jsonpPost(tencentCloudProps.getFacedetect(), JSONUtils.toJSONString(param));
        System.out.println(object);
        return object;
    }

    /**
     * 人脸相似度比较
     * */
    public Object faceCompare(String imgAMd5, String imgBMd5) throws Exception {
        Map<String, Object> param = new HashedMap();
        param.put("appid", tencentCloudProps.getAppId());
        param.put("urlA", "https://ponaparte-1257128504.cos.ap-chengdu.myqcloud.com/123.jpg");
        param.put("urlB", "https://ponaparte-1257128504.cos.ap-chengdu.myqcloud.com/456.jpg");
        Object object = jsonpPost(tencentCloudProps.getFacecompare(), JSONUtils.toJSONString(param));
        System.out.println(object);
        return object;
    }

    public Object newPerson() throws Exception{
        Map<String, Object> param = new HashedMap();
        param.put("appid", tencentCloudProps.getAppId());
        List<String> groupIds = Lists.newArrayList();
        groupIds.add("ponaparte");
        param.put("group_ids", groupIds);
        param.put("person_id", "100003");
        param.put("person_name", "杨钰莹");
        param.put("url", "https://ponaparte-1257128504.cos.ap-chengdu.myqcloud.com/123.jpg");
        Object object = jsonpPost(tencentCloudProps.getNewperson(), JSONUtils.toJSONString(param));
        System.out.println(object);
        return object;
    }

    public Object identifyPerson() throws Exception{
        Map<String, Object> param = new HashedMap();
        param.put("appid", tencentCloudProps.getAppId());
        List<String> groupIds = Lists.newArrayList();
        groupIds.add("ponaparte");
        param.put("group_ids", groupIds);
        param.put("url", "https://ponaparte-1257128504.cos.ap-chengdu.myqcloud.com/112.jpg");
        Object object = jsonpPost(tencentCloudProps.getIdentify(), JSONUtils.toJSONString(param));
        return object;
    }

    /**
     * 执行请求
     * url, 使用腾讯云的bucket 文件引用的形式
     * */
    public Object jsonpPost(String path, String str){
        Object body = null;
        try{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(path);
            String respContent = null;
            String authorization = TencentCloudUtil.appSign(Integer.valueOf(tencentCloudProps.appId),
                    tencentCloudProps.getSecretId(),
                    tencentCloudProps.getSecretKey(),
                    tencentCloudProps.getBucketName(),
                    0);
            post.setHeader("host", "recognition.image.myqcloud.com");
            post.setHeader("content-type", "application/json");
            post.setHeader("authorization", authorization);
            StringEntity entity = new StringEntity( str, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);
            HttpResponse resp = client.execute(post);
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            JSONObject resultJson = JSON.parseObject(respContent.toString());
            body = resultJson.get("data");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("请求es数据失败");
        }
        return body;
    }

    private void setMultiPartEntity(MultipartBuilder multipartBuilder, Map<String, Object> params, Map<String, File> files, Map<String, byte[]> fileContents)
            throws FileNotFoundException {

        multipartBuilder.type(MultipartBuilder.FORM);
        for (String paramKey : params.keySet()) {
            multipartBuilder.addFormDataPart(paramKey, String.valueOf(params.get(paramKey)));
        }

        if (files.size() > 0) {
            for (String key : files.keySet()) {
                File file = files.get(key);
                if (file == null) {
                    throw new FileNotFoundException("File is null: " + key);
                }
                if (!file.exists()) {
                    throw new FileNotFoundException("File Not Exists: " + file.getAbsolutePath());
                }
                multipartBuilder.addPart(
                        Headers.of("Content-Disposition", String.format("form-data; name=\"%s\"; filename=\"%s\"", key, file.getName())),
                        RequestBody.create(MediaType.parse("image/jpg"), file)
                );
            }
        }
    }

    /**
     * Multipart/format 格式图片的检索
     * */
    protected Object sendPostRequest(String path, Map<String, Object> map, MultipartFile file) throws Exception {
        Object object = new Object();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
        mOkHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
        mOkHttpClient.setWriteTimeout(10000,TimeUnit.MILLISECONDS);
        String authorization = TencentCloudUtil.appSign(Integer.valueOf(tencentCloudProps.getAppId()),
                tencentCloudProps.getSecretId(),
                tencentCloudProps.getSecretKey(),
                tencentCloudProps.getBucketName(),
                0);
        Map<String, String> headers = new HashedMap();
        headers.put("host", "recognition.image.myqcloud.com");
        headers.put("content-type", "multipart/form-data");
        headers.put("authorization", authorization);
        File lf = new File("E:\\pictures\\" + System.currentTimeMillis() + ".jpg");
        file.transferTo(lf);
        HashMap<String, File> imageList = new HashMap<>();
        imageList.put("image", lf);
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        setMultiPartEntity(multipartBuilder, map, imageList, null);
        RequestBody requestBody = multipartBuilder.build();
        Request.Builder requestBuilder = new Request.Builder()
                .url(tencentCloudProps.getIdentify())
                .post(requestBody);
        for (String headerKey : headers.keySet()) {
            requestBuilder.addHeader(headerKey, headers.get(headerKey));
        }
        Response response = null;
        try {
            response = mOkHttpClient.newCall(requestBuilder.build()).execute();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        String string = null;
        try {
            string = response.body().string();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        object = JSONObject.parseObject(string);
        return object;
    }
}
