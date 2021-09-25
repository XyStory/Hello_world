package com.hrsys.dao.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.NoticeDao;
import com.hrsys.entity.Notice;
import com.hrsys.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author steve
 * @version 1.0
 */
public class NoticeDaoImpl implements NoticeDao {
    @Override
    public boolean insertNotice(Notice notice) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("insert into notice_inf(TITLE, CONTEXT, CREATE_DATE, USER_ID)"
                    + " values (?,?,?,?)");
            prep.setString(1, notice.getTitle());
            prep.setString(2, notice.getContent());
            prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            prep.setInt(4, notice.getUserId());

            if (prep.executeUpdate() > 0) {
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
    public int removeNotice(int[] ids) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("delete from notice_inf where ID=?");
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
    public List<Notice> listNotices(Notice notice, PageModel pageModel) {
        List<Notice> notices = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("select * from notice_inf where TITLE like ?");
            if (notice.getUserId() != 0) {
                sqlBuf.append(" AND USER_ID=" + notice.getUserId());
            }
            sqlBuf.append(" limit " + pageModel.getStartIndex() + "," + pageModel.getPageSize());
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, notice.getTitle());
            ResultSet resultSet = prep.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("TITLE");
                String content = resultSet.getString("CONTENT");
                Date createDate = new Date(resultSet.getTimestamp("CREATE_DATE").getTime());
                int userId = resultSet.getInt("USER_ID");
                notices.add(new Notice(id, title, content, createDate, userId));
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
        return notices;
    }

    @Override
    public int countTotalRecordSum(Notice notice) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("select count(*) from notice_inf");
            if (notice.getTitle() != null && !Objects.equals(notice.getTitle(), "")) {
                sqlBuf.append(" where TITLE like '%" + notice.getTitle() + "%'");
            }
            if (notice.getUserId() != 0) {
                sqlBuf.append(" where USER_ID=" + notice.getUserId());
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
