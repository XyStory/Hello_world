package com.hrsys.dao.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.UserDao;
import com.hrsys.entity.User;
import com.hrsys.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author steve
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(User user) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("select * from user_inf where loginname=? and password=?");
            prep.setString(1, user.getLoginName());
            prep.setString(2, user.getPwd());
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int status = resultSet.getInt(4);
                Date createDate = resultSet.getDate(5);
                String username = resultSet.getString(6);
                user.setUserId(id);
                user.setStatus(status);
                user.setCreateDate(createDate);
                user.setUsername(username);
                return user;
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
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("insert into user_inf(loginname, PASSWORD, STATUS, createdate, username) values(?, ?, ?, ?, ?)");
            prep.setString(1, user.getLoginName());
            prep.setString(2, user.getPwd());
            prep.setInt(3, user.getStatus());
            prep.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            prep.setString(5, user.getUsername());

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
    public int removeUserById(List<Integer> ids) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("delete from user_inf where ID=?");
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
    public boolean updateUserById(User user) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("update user_inf PASSWORD=?, `STATUS`=?, username=? where ID=?;");
            prep.setString(1, user.getPwd());
            prep.setInt(2, user.getStatus());
            prep.setString(3, user.getUsername());
            prep.setInt(4, user.getUserId());

            if (prep.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("select * from user_inf where ID=?");
            prep.setInt(1, id);
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String loginName = resultSet.getString(2);
                String pwd = resultSet.getString(3);
                int status = resultSet.getInt(4);
                Date createDate = resultSet.getDate(5);
                String username = resultSet.getString(6);
                return new User(userId, loginName, pwd, status, createDate, username);
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
    public List<User> listUsers(User user, PageModel pageModel) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("select * from user_inf where username like ?");
            if (user.getStatus() != -1) {
                sqlBuf.append(" and `status` like " + user.getStatus());
            }
            sqlBuf.append(" limit " + pageModel.getStartIndex() + "," + pageModel.getPageSize());
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, user.getUsername());
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String loginName = resultSet.getString(2);
                String pwd = resultSet.getString(3);
                int status = resultSet.getInt(4);
                Date createDate = resultSet.getDate(5);
                String username = resultSet.getString(6);
                users.add(new User(userId, loginName, pwd, status, createDate, username));
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
        return users;
    }

    @Override
    public int countTotalRecordSum(User user) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;

        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("select count(*) from user_inf where username like ?");
            if (user.getStatus() != -1) {
                sqlBuf.append(" and `status` like " + user.getStatus());
            }
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, user.getUsername());
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
