package com.bonaparte.bean;

import com.bonaparte.util.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by yangmingquan on 2018/8/29.
 */
public class StatisticBean {
    @ApiModelProperty(value = "组织id")
    private Integer orgId;
    private String code;
    @ApiModelProperty(value = "三会一课类型")
    private String classifyType;
    @ApiModelProperty(value = "资讯类型")
    private Integer infoType;
    @ApiModelProperty(value = "0:周 ，1:月")
    private Integer groupType;
    @ApiModelProperty(value = "资讯ID")
    private Long infoId;
    private Date infoDate;
    @ApiModelProperty(value = "是否包含下属各级子组织 0:不包含 1:包含")
    private Integer includeType;
    @ApiModelProperty(value = "组织id集合")
    private List<Integer> orgIds;
    @ApiModelProperty(value = "组织的code")
    private List<String> orgCodes;
    @ApiModelProperty(value = "orderPriority 0: 默认排序即组织名称" +
            "1：阅读量 2：转好友 3：转朋友圈")
    private Integer orderPriority;
    @ApiModelProperty(value = "0：升序，1：降序")
    private Integer orderByType;
    @ApiModelProperty(value = "0： 已读  1: 未读")
    private Integer type;
    @ApiModelProperty(value = "评论描述")
    private String content;
    @ApiModelProperty(value = "开始时间")
    protected Date startTime;
    @ApiModelProperty(value = "结束时间")
    protected Date endTime;
    @ApiModelProperty(value = "页数")
    private Integer pageNo = 1;
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 20;
    @ApiModelProperty(value = "从第几条导出")
    private Integer startNo;
    @ApiModelProperty(value = "导出到第几条")
    private Integer endNo;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public String getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(String classifyType) {
        this.classifyType = classifyType;
    }

    public void checkOrUpdateTime() {
        if (this.endTime == null || startTime == null) {
            Date dt = new Date();
            endTime = dt;
            int days = 7 * 6;
            if (groupType != null && groupType != 0) {
                days = 30 * 6;
            }
            startTime = DateUtils.adjustDate(dt, -days, 0, 0);
        } else {
            endTime = DateUtils.adjustDate(endTime, 1, 0, 0);
        }
    }

    public void studyCheckTime() {
        if (endTime == null || startTime == null) {
            Date dt = new Date();
            endTime = dt;
            int days = 31;
            startTime = DateUtils.adjustDate(dt, -days, 0, 0);
        } else {
            endTime = DateUtils.adjustDate(endTime, 1, 0, 0);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getInfoId(){
        return this.infoId;
    }

    public Date getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
    }

    public Integer getIncludeType() {
        return includeType;
    }

    public void setIncludeType(Integer includeType) {
        this.includeType = includeType;
    }

    public List<Integer> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Integer> orgIds) {
        this.orgIds = orgIds;
    }

    public List<String> getOrgCodes() {
        return orgCodes;
    }

    public void setOrgCodes(List<String> orgCodes) {
        this.orgCodes = orgCodes;
    }

    public Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public Integer getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(Integer orderByType) {
        this.orderByType = orderByType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStartRow() {
        if (pageNo == null || pageSize == null) {
            return 0;
        }
        return (pageNo - 1) * pageSize;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    public Integer getEndNo() {
        return endNo;
    }

    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }
}
