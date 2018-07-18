package com.bonaparte.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/7/18.
 */
@Configuration
@ConfigurationProperties(prefix = "tencentcloud")
public class TencentCloudProps {
    public String facedetect;
    public String imgBaseUrl;
    public String appId;
    public String secretId;
    public String secretKey;
    public String bucketName;
    private String facecompare;
    private String faceBaseUrl;
    private String newperson;
    private String personinfo;
    private String setpersoninfo;
    private String identify;


    public String getFacedetect() {
        return facedetect;
    }

    public void setFacedetect(String facedetect) {
        this.facedetect = facedetect;
    }

    public String getImgBaseUrl() {
        return imgBaseUrl;
    }

    public void setImgBaseUrl(String imgBaseUrl) {
        this.imgBaseUrl = imgBaseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFacecompare() {
        return facecompare;
    }

    public void setFacecompare(String facecompare) {
        this.facecompare = facecompare;
    }

    public String getFaceBaseUrl() {
        return faceBaseUrl;
    }

    public void setFaceBaseUrl(String faceBaseUrl) {
        this.faceBaseUrl = faceBaseUrl;
    }

    public String getNewperson() {
        return newperson;
    }

    public void setNewperson(String newperson) {
        this.newperson = newperson;
    }

    public String getPersoninfo() {
        return personinfo;
    }

    public void setPersoninfo(String personinfo) {
        this.personinfo = personinfo;
    }

    public String getSetpersoninfo() {
        return setpersoninfo;
    }

    public void setSetpersoninfo(String setpersoninfo) {
        this.setpersoninfo = setpersoninfo;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}
