package com.hrsys.entity;

/**
 * @author steve
 */
public class Dept {
    private int deptId;
    private String name;
    private String remark;

    public Dept() {
    }

    public Dept(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public Dept(int deptId, String name, String remark) {
        this.deptId = deptId;
        this.name = name;
        this.remark = remark;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
