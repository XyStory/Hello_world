package com.hrsys.entity;

import java.util.Date;

/**
 * @author steve
 *
 *   ID INT(11) NOT NULL AUTO_INCREMENT,
 *   DEPT_ID INT(11) NOT NULL,
 *   JOB_ID INT(11) NOT NULL,
 *   NAME VARCHAR(20) NOT NULL,
 *   CARD_ID VARCHAR(18) NOT NULL,
 *   ADDRESS VARCHAR(50) NOT NULL,
 *   POST_CODE VARCHAR(50) DEFAULT NULL,
 *   TEL VARCHAR(16) DEFAULT NULL,
 *   PHONE VARCHAR(11) NOT NULL,
 *   QQ_NUM VARCHAR(10) DEFAULT NULL,
 *   EMAIL VARCHAR(50) NOT NULL,
 *   SEX INT(11) NOT NULL DEFAULT '1',
 *   PARTY VARCHAR(10) DEFAULT NULL,
 *   BIRTHDAY DATETIME DEFAULT NULL,
 *   RACE VARCHAR(100) DEFAULT NULL,
 *   EDUCATION VARCHAR(10) DEFAULT NULL,
 *   SPECIALITY VARCHAR(20) DEFAULT NULL,
 *   HOBBY VARCHAR(100) DEFAULT NULL,
 *   REMARK VARCHAR(500) DEFAULT NULL,
 *   CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
 */
public class Employee {
    private int employeeId;
    private int deptId;
    private int jobId;
    private String employeeName;
    private String cardId;
    private String address;
    private String postCode;
    private String tel;
    private String phone;
    private String qqNum;
    private String email;
    private int sex;
    private String party;
    private Date birthday;
    private String race;
    private String education;
    private String speciality;
    private String hobby;
    private String remark;
    private Date createDate;

    public Employee() {
    }

    public Employee(int deptId, int jobId, String employeeName, String cardId, String address, String phone, String email, int sex) {
        this.deptId = deptId;
        this.jobId = jobId;
        this.employeeName = employeeName;
        this.cardId = cardId;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
    }

    public Employee(int deptId, int jobId, String employeeName, String cardId, String address, String postCode, String tel, String phone, String qqNum, String email, int sex, String party, Date birthday, String race, String education, String speciality, String hobby, String remark) {
        this.deptId = deptId;
        this.jobId = jobId;
        this.employeeName = employeeName;
        this.cardId = cardId;
        this.address = address;
        this.postCode = postCode;
        this.tel = tel;
        this.phone = phone;
        this.qqNum = qqNum;
        this.email = email;
        this.sex = sex;
        this.party = party;
        this.birthday = birthday;
        this.race = race;
        this.education = education;
        this.speciality = speciality;
        this.hobby = hobby;
        this.remark = remark;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", deptId=" + deptId +
                ", jobId=" + jobId +
                ", employeeName='" + employeeName + '\'' +
                ", cardId='" + cardId + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", tel='" + tel + '\'' +
                ", phone='" + phone + '\'' +
                ", qqNum='" + qqNum + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", party='" + party + '\'' +
                ", birthday=" + birthday +
                ", race='" + race + '\'' +
                ", education='" + education + '\'' +
                ", speciality='" + speciality + '\'' +
                ", hobby='" + hobby + '\'' +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
