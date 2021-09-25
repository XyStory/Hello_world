package com.hrsys.service.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DaoFactory;
import com.hrsys.dao.JobDao;
import com.hrsys.entity.Job;
import com.hrsys.service.JobService;

import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
public class JobServiceImpl implements JobService {
    JobDao jobDao;

    public JobServiceImpl() {
        jobDao = DaoFactory.jobDao();
    }

    @Override
    public boolean insertJobService(Job job) {
        if (job.getJobName() == null || Objects.equals(job.getJobName(), "")) {
            return false;
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        List<Job> jobs = listJobByName("", pageModel);
        for (Job jobQuery : jobs) {
            if (Objects.equals(jobQuery.getJobName(), job.getJobName())) {
                return false;
            }
        }

        return jobDao.insertJob(job);
    }

    @Override
    public int[] removeJobService(int[] ids) {
        int success = jobDao.removeJobById(ids);
        int fail = ids.length - success;
        int[] deleteCount = {success, fail};
        return deleteCount;
    }

    @Override
    public Job getJobService(int id) {
        return jobDao.getJobById(id);
    }

    @Override
    public List<Job> listJobByName(String jobName, PageModel pageModel) {
        if (jobName == null || Objects.equals(jobName, "")) {
            jobName = "%";
        } else {
            jobName = "%" + jobName + "%";
        }
        int totalCount = jobDao.countTotalRecordSum(jobName);
        pageModel.setTotalRecordSum(totalCount);
        return jobDao.listJobs(jobName, pageModel);
    }

    @Override
    public boolean updateJobService(Job job) {
        return jobDao.updateJob(job);
    }
}
