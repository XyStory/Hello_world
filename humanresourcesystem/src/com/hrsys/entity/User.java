package com.hrsys.entity;

import java.util.Date;

/**
 * @author steve
 */
public class User {
    private int userId;
    private String loginName;
    private String pwd;
    private int status = -1;
    private Date createDate;
    private String username;

    public User() {
    }

    public User(String loginName, String pwd) {
        this.loginName = loginName;
        this.pwd = pwd;
    }

    public User(String loginName, String pwd, int status, String username) {
        this.loginName = loginName;
        this.pwd = pwd;
        this.status = status;
        this.username = username;
    }

    public User(int userId, String loginName, String pwd, int status, Date createDate, String username) {
        this.userId = userId;
        this.loginName = loginName;
        this.pwd = pwd;
        this.status = status;
        this.createDate = createDate;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
