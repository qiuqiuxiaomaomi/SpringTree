package com.bonaparte.bean;

import java.io.Serializable;

/**
 * Created by yangmingquan on 2018/7/3.
 * 国家数据
 */
public class Nation implements Serializable{
    private String name;
    private String id;
    private String area;
    private String peopleNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Nation(String name, String id, String area, String peopleNum) {
        this.name = name;
        this.id = id;
        this.area = area;
        this.peopleNum = peopleNum;
    }

    public Nation() {
    }
}
