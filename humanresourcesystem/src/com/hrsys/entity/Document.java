package com.hrsys.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author steve
 */
public class Document {
    private int documentId;
    private String title;
    private String filename;
    private String remark;
    private Date createDate;
    private int userId;

    public Document() {
    }

    public Document(String title, String filename, String remark, int userId) {
        this.title = title;
        this.filename = filename;
        this.remark = remark;
        this.userId = userId;
    }

    public Document(int documentId, String title, String filename, String remark, Date createDate, int userId) {
        this.documentId = documentId;
        this.title = title;
        this.filename = filename;
        this.remark = remark;
        this.createDate = createDate;
        this.userId = userId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(createDate);
        return time;
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

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", title='" + title + '\'' +
                ", filename='" + filename + '\'' +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", userId=" + userId +
                '}';
    }
}
