package com.bonaparte.entity;

import javax.persistence.*;

@Table(name = "m_ftp_address")
public class FtpAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address_name")
    private String addressName;

    private String host;

    private Integer port;

    @Column(name = "user_name")
    private String userName;

    private String password;

    /**
     * 目录
     */
    private String path;

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

    /**
     * @return address_name
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * @param addressName
     */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    /**
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取目录
     *
     * @return path - 目录
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置目录
     *
     * @param path 目录
     */
    public void setPath(String path) {
        this.path = path;
    }
}