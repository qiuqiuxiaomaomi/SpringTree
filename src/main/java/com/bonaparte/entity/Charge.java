package com.bonaparte.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Table(name = "p_charge")
public class Charge {
    /**
     * 订单号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 金额
     */
    private Double money;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date utime;

    /**
     * 交易状态
     */
    private Integer status;

    /**
     * 党费缴纳的时间段的起止时间
     */
    @Column(name = "charge_time")
    private String chargeTime;

    /**
     * 交易成功返回的交易
     */
    @Column(name = "transaction_id")
    private String transactionId;

    /**
     * 1:支付宝 2：微信支付 3:银行卡支付 4：线下支付
     */
    private Integer type;

    /**
     * 编辑者ID
     * */
    private Integer uid;

    /**
     * 党费缴纳描述信息
     * */
    private String description;

    /**
     * 订单ID
     *
     */
    @Column(name = "order_id")
    private String orderId;

    @Transient
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取订单号
     *
     * @return id - 订单号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订单号
     *
     * @param id 订单号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置操作人名称
     *
     * @param name - 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取更新时间
     *
     * @return utime - 更新时间
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * 设置更新时间
     *
     * @param utime 更新时间
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    /**
     * 获取交易状态
     *
     * @return status - 交易状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置交易状态
     *
     * @param status 交易状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取交易成功返回的交易
     *
     * @return transaction_id - 交易成功返回的交易
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 设置交易成功返回的交易
     *
     * @param transactionId 交易成功返回的交易
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 获取1:支付宝 2：微信支付 3:银行卡支付 4：线下支付
     *
     * @return type - 1:支付宝 2：微信支付 3:银行卡支付 4：线下支付
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1:支付宝 2：微信支付 3:银行卡支付 4：线下支付
     *
     * @param type 1:支付宝 2：微信支付 3:银行卡支付 4：线下支付
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Charge(String name, Double money, Date ctime, Date utime, Integer status, String chargeTime, String transactionId, Integer type, Integer uid, String description, String orderId, String openId) {
        this.name = name;
        this.money = money;
        this.ctime = ctime;
        this.utime = utime;
        this.status = status;
        this.chargeTime = chargeTime;
        this.transactionId = transactionId;
        this.type = type;
        this.uid = uid;
        this.description = description;
        this.orderId = orderId;
        this.openId = openId;
    }

    public Charge(Integer uid, Double money) {
        this.uid = uid;
        this.money = money;
    }
}
