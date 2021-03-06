package com.hrsys.entity;

import java.util.Date;

/**
 * @author steve
 * @version 1.0
 */
public class Notice {
    private int id;
    private String title;
    private String content;
    private Date createDate;
    private int userId;

    public Notice() {
    }

    public Notice(String title, String content, int userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Notice(int id, String title, String content, Date createDate, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
