package com.bonaparte.bean;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/8/29.
 */
public class Member {
    @ApiModelProperty(value = "主键id，新建无此字段")
    private Integer id;

    @ApiModelProperty(value = "企业微信成员userid")
    private String userid;

    @ApiModelProperty(value = "序号")
    private Integer num;

    @ApiModelProperty(value = "标识")
    private String flag;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "该字段不能为空")
    private String name;

    @ApiModelProperty(value = "性别,1 男,0 女")
    private Integer sex;

    @ApiModelProperty(value = "国家")
    private String nation;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "生日")
    private String birth;

    @ApiModelProperty(value = "工作日期")
    private String jobDate;

    @ApiModelProperty(value = "身份证号")
    private String cardNum;

    @ApiModelProperty(value = "个人身份")
    private String identity;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "一线情况")
    private String lately;

    @ApiModelProperty(value = "新阶层")
    private String newClass;

    @ApiModelProperty(value = "技术职务")
    private String techPosition;

    @ApiModelProperty(value = "工作单位")
    private String unitName;

    @ApiModelProperty(value = "单位性质")
    @Column(name = "unit_attr")
    private String unitAttr;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "家庭地址")
    private String address;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "座机")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "名字的拼音")
    private String pinyin;

    @ApiModelProperty(value = "创建时间")
    private Date ctime;

    @ApiModelProperty(value = "更新时间")
    private Date utime;

    @ApiModelProperty
    private Integer uid;

    @ApiModelProperty(value = "用户名")
    private String userName;

    public void communityProperties(Member member) {
        this.name = member.getName();
        this.userName = member.getUserName();
        this.sex = member.getSex();
        this.degree = member.getDegree();
        this.cardNum = member.getCardNum();
        this.phone = member.getPhone();
        // 座机和email信息
        this.mobile = member.getMobile();
        this.email = member.getEmail();
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取序号
     *
     * @return num - 序号
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置序号
     *
     * @param num 序号
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取标识
     *
     * @return flag - 标识
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置标识
     *
     * @param flag 标识
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        if (!StringUtils.isEmpty(name)) {
            this.name = name.trim();
        }
    }

    /**
     * 获取性别,1 男,0 女
     *
     * @return sex - 性别,1 男,0 女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别,1 男,0 女
     *
     * @param sex 性别,1 男,0 女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取国家
     *
     * @return nation - 国家
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置国家
     *
     * @param nation 国家
     */
    public void setNation(String nation) {
        if (!StringUtils.isEmpty(nation)) {
            this.nation = nation.trim();
        }
    }

    /**
     * 获取籍贯
     *
     * @return native_place - 籍贯
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * 设置籍贯
     *
     * @param nativePlace 籍贯
     */
    public void setNativePlace(String nativePlace) {
        if (!StringUtils.isEmpty(nativePlace)) {
            this.nativePlace = nativePlace.trim();
        }
    }

    /**
     * 获取生日
     *
     * @return birth - 生日
     */
    public String getBirth() {
        return birth;
    }

    /**
     * 设置生日
     *
     * @param birth 生日
     */
    public void setBirth(String birth) {
        if (!StringUtils.isEmpty(birth)) {
            this.birth = birth.trim();
        }
    }

    /**
     * 获取工作日期
     *
     * @return job_date - 工作日期
     */
    public String getJobDate() {
        return jobDate;
    }

    /**
     * 设置工作日期
     *
     * @param jobDate 工作日期
     */
    public void setJobDate(String jobDate) {
        if (!StringUtils.isEmpty(jobDate)) {
            this.jobDate = jobDate.trim();
        }
    }

    /**
     * 获取身份证号
     *
     * @return card_num - 身份证号
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * 设置身份证号
     *
     * @param cardNum 身份证号
     */
    public void setCardNum(String cardNum) {
        if (!StringUtils.isEmpty(cardNum)) {
            this.cardNum = cardNum.trim();
        }
    }

    /**
     * 获取个人身份
     *
     * @return identity - 个人身份
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置个人身份
     *
     * @param identity 个人身份
     */
    public void setIdentity(String identity) {
        if (!StringUtils.isEmpty(identity)) {
            this.identity = identity.trim();
        }
    }

    /**
     * 获取学位
     *
     * @return degree - 学位
     */
    public String getDegree() {
        return degree;
    }

    /**
     * 设置学位
     *
     * @param degree 学位
     */
    public void setDegree(String degree) {
        if (!StringUtils.isEmpty(degree)) {
            this.degree = degree.trim();
        }
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        if (!StringUtils.isEmpty(education)) {
            this.education = education.trim();
        }
    }

    /**
     * 获取一线情况
     *
     * @return lately - 一线情况
     */
    public String getLately() {
        return lately;
    }

    /**
     * 设置一线情况
     *
     * @param lately 一线情况
     */
    public void setLately(String lately) {
        if (!StringUtils.isEmpty(lately)) {
            this.lately = lately.trim();
        }
    }

    /**
     * 获取新阶层
     *
     * @return new_class - 新阶层
     */
    public String getNewClass() {
        return newClass;
    }

    /**
     * 设置新阶层
     *
     * @param newClass 新阶层
     */
    public void setNewClass(String newClass) {
        if (!StringUtils.isEmpty(newClass)) {
            this.newClass = newClass.trim();
        }
    }

    /**
     * 获取技术职务
     *
     * @return tech_position - 技术职务
     */
    public String getTechPosition() {
        return techPosition;
    }

    /**
     * 设置技术职务
     *
     * @param techPosition 技术职务
     */
    public void setTechPosition(String techPosition) {
        if (!StringUtils.isEmpty(techPosition)) {
            this.techPosition = techPosition.trim();
        }
    }

    /**
     * 获取工作单位
     *
     * @return unit_name - 工作单位
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置工作单位
     *
     * @param unitName 工作单位
     */
    public void setUnitName(String unitName) {
        if (!StringUtils.isEmpty(unitName)) {
            this.unitName = unitName.trim();
        }
    }

    /**
     * 获取单位性质
     *
     * @return unit_attr - 单位性质
     */
    public String getUnitAttr() {
        return unitAttr;
    }

    /**
     * 设置单位性质
     *
     * @param unitAttr 单位性质
     */
    public void setUnitAttr(String unitAttr) {
        if (!StringUtils.isEmpty(unitAttr)) {
            this.unitAttr = unitAttr.trim();
        }
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        if (!StringUtils.isEmpty(phone)) {
            this.phone = phone.trim();
        }
    }

    /**
     * 获取家庭地址
     *
     * @return address - 家庭地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置家庭地址
     *
     * @param address 家庭地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
