package com.hrsys.entity;

/**
 * @author steve
 */
public class Job {
    private int jobId;
    private String jobName;
    private String remark;

    public Job() {
    }

    public Job(String jobName, String remark) {
        this.jobName = jobName;
        this.remark = remark;
    }

    public Job(int jobId, String jobName, String remark) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.remark = remark;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobName='" + jobName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
