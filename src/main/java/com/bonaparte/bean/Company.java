package com.bonaparte.bean;

import java.util.List;

/**
 * Created by yangmingquan on 2018/7/11.
 */
public class Company {
    private long id;
    private String name;
    private long memberCount;
    private List<String> aliasName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(long memberCount) {
        this.memberCount = memberCount;
    }

    public List<String> getAliasName() {
        return aliasName;
    }

    public void setAliasName(List<String> aliasName) {
        this.aliasName = aliasName;
    }
}
