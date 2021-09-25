package com.hrsys.dao.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.JobDao;
import com.hrsys.entity.Job;
import com.hrsys.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
public class JobDaoImpl implements JobDao {
    @Override
    public boolean insertJob(Job job) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("insert into job_inf(NAME, REMARK) VALUES(?, ?)");
            prep.setString(1, job.getJobName());
            prep.setString(2, job.getRemark());
            int update = prep.executeUpdate();

            if (update != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
                DruidUtil.closeConn(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int removeJobById(int[] ids) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("delete from job_inf where ID=?");
            for (int id : ids) {
                prep.setInt(1, id);
                int update = prep.executeUpdate();
                count += update;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
                DruidUtil.closeConn(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public Job getJobById(int id) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("select * from job_inf where ID=?");
            prep.setInt(1, id);
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                Job job = new Job();
                job.setJobId(resultSet.getInt(1));
                job.setJobName(resultSet.getString(2));
                job.setRemark(resultSet.getString(3));
                return job;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Job> listJobs(String jobName, PageModel pageModel) {
        List<Job> jobs = new ArrayList<>();
        StringBuffer sqlBuf = new StringBuffer("SELECT * FROM job_inf WHERE `name` like ?");
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            sqlBuf.append(" limit " + pageModel.getStartIndex() + "," + pageModel.getPageSize());
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, jobName);
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String remark = resultSet.getString(3);
                jobs.add(new Job(id, name, remark));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
                DruidUtil.closeConn(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return jobs;
    }

    @Override
    public boolean updateJob(Job job) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("update job_inf set `name`=?, remark=? where id=?");
            prep.setString(1, job.getJobName());
            prep.setString(2, job.getRemark());
            prep.setInt(3, job.getJobId());
            int update = prep.executeUpdate();

            if (update != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
                DruidUtil.closeConn(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int countTotalRecordSum(String jobName) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("select count(*) from job_inf");
            if (jobName != null && !Objects.equals(jobName, "")) {
                sqlBuf.append(" where `name` like '%" + jobName + "%';");
            }
            prep = connection.prepareStatement(sqlBuf.toString());
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
                DruidUtil.closeConn(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
