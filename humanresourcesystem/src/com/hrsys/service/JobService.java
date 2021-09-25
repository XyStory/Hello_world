package com.hrsys.service;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Job;

import java.util.List;

/**
 * @author steve
 */
public interface JobService {
    /**
     * insertJobService
     * @param job 需要添加的职位对象
     * @return 返回是否添加成功
     */
    boolean insertJobService(Job job);

    /**
     * removeJobService
     * @param ids 需要删除的数据的id
     * @return 返回成功了多少条数据
     */
    int[] removeJobService(int[] ids);

    /**
     * getJobService
     * @param id
     * @return
     */
    Job getJobService(int id);

    /**
     * listJobByName
     * @param jobName 职位名
     * @param pageModel 页码对象
     * @return
     */
    List<Job> listJobByName(String jobName, PageModel pageModel);

    /**
     * updateJobService
     * @param job
     * @return 返回数据更新是否成功
     */
    boolean updateJobService(Job job);
}
