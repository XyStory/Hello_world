package com.hrsys.dao;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Job;

import java.util.List;

/**
 * @author steve
 */
public interface JobDao {
    /**
     * insertJob
     * @param job
     * @return
     */
    boolean insertJob(Job job);

    /**
     * removeJobById
     * @param ids
     * @return
     */
    int removeJobById(int[] ids);

    /**
     * getHobById
     * @param id
     * @return
     */
    Job getJobById(int id);

    /**
     * listJobs
     * @param jobName
     * @param pageModel
     * @return
     */
    List<Job> listJobs(String jobName, PageModel pageModel);

    /**
     * updateJob
     * @param job
     * @return
     */
    boolean updateJob(Job job);

    /**
     * countTotalRecordSum 用于统计表中数据数量
     * @param jobName
     * @return 返回表中数据条数
     */
    int countTotalRecordSum(String jobName);
}
