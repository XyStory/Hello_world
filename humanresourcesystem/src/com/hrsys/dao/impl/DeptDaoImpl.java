package com.hrsys.dao.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DeptDao;
import com.hrsys.entity.Dept;
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
public class DeptDaoImpl implements DeptDao {
    @Override
    public boolean insertDept(Dept dept) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("insert into dept_inf(`NAME`, REMARK) VALUES(?, ?)");
            prep.setString(1, dept.getName());
            prep.setString(2, dept.getRemark());
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
    public int removeDeptById(int[] ids) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("delete from dept_inf where ID=?");
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
    public Dept getDeptById(int id) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("select * from dept_inf where ID=?");
            prep.setInt(1, id);
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                Dept dept = new Dept();
                dept.setDeptId(resultSet.getInt(1));
                dept.setName(resultSet.getString(2));
                dept.setRemark(resultSet.getString(3));

                return dept;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prep != null) {
                    prep.close();
                    DruidUtil.closeConn(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public List<Dept> listDepts(String deptName, PageModel pageModel) {
        List<Dept> deptList = new ArrayList<>();
        StringBuffer sqlBuf = new StringBuffer("SELECT * FROM dept_inf WHERE `name` like ?");
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            sqlBuf.append(" limit " + pageModel.getStartIndex() + "," + pageModel.getPageSize());
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, deptName);
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String remark = resultSet.getString(3);
                deptList.add(new Dept(id, name, remark));
            }
            return deptList;
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
        return null;
    }

    @Override
    public boolean updateDept(Dept dept) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("update dept_inf set `name`=?, remark=? where id=?");
            prep.setString(1, dept.getName());
            prep.setString(2, dept.getRemark());
            prep.setInt(3, dept.getDeptId());
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
    public int countTotalRecordSum(String deptName) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;

        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("select count(*) from dept_inf");
            if (deptName != null && !Objects.equals(deptName, "")) {
                sqlBuf.append(" where `name` like '%" + deptName + "%';");
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
