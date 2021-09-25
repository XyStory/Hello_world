package com.hrsys.dao.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DocumentDao;
import com.hrsys.entity.Document;
import com.hrsys.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author steve
 */
public class DocumentDaoImpl implements DocumentDao {
    @Override
    public boolean insertDocument(Document document) {
        Connection connection = null;
        PreparedStatement prep = null;
        int update = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("insert into document_inf(TITLE, filename, REMARK, CREATE_DATE, USER_ID) values(?, ?, ?, ?, ?)");
            prep.setString(1, document.getTitle());
            prep.setString(2, document.getFilename());
            prep.setString(3, document.getRemark());
            prep.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            prep.setInt(5, document.getUserId());

            update = prep.executeUpdate();
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
        return update != 0;
    }

    @Override
    public int removeDocumentById(int[] ids) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("delete from document_inf where ID=?");
            for (int id : ids) {
                prep.setInt(1, id);
                int update = prep.executeUpdate();
                if (update != 0) {
                    count++;
                }
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
    public List<Document> listDocuments(Document document, PageModel pageModel) {
        List<Document> documents = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prep = null;

        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("select * from document_inf where TITLE like ? and filename like ?");
            sqlBuf.append(" limit " + pageModel.getStartIndex() + "," + pageModel.getPageSize());
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, document.getTitle());
            prep.setString(2, document.getFilename());
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                int documentId = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String filename = resultSet.getString(3);
                String remark = resultSet.getString(4);
                Date createDate = new Date(resultSet.getTimestamp(5).getTime());
                int userId = resultSet.getInt(6);
                documents.add(new Document(documentId, title, filename, remark, createDate, userId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    @Override
    public int countTotalRecordSum(Document document) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("select count(*) from document_inf where TITLE like ? and filename like ?;");
            prep.setString(1, document.getTitle());
            prep.setString(2, document.getFilename());
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

    @Override
    public List<String> listFileNames(int[] ids) {
        List<String> fileNames = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("select `filename` from document_inf where ID=?");
            for (int id : ids) {
                prep.setInt(1, id);
                ResultSet resultSet = prep.executeQuery();

                while (resultSet.next()) {
                    String fileName = resultSet.getString(1);
                    fileNames.add(fileName);
                }
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
        return fileNames;
    }
}
