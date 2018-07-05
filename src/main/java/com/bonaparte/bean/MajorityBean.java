package com.bonaparte.bean;

import java.io.Serializable;

/**
 * Created by yangmingquan on 2018/7/5.
 */
public class MajorityBean implements Serializable{
    private String code;
    private String majorityName;
    private String universityName;
    private String adress;
    private Integer studentCount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMajorityName() {
        return majorityName;
    }

    public void setMajorityName(String majorityName) {
        this.majorityName = majorityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
}
