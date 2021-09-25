package com.hrsys.dao.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.EmployeeDao;
import com.hrsys.entity.Employee;
import com.hrsys.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean insertEmployee(Employee employee) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("insert into employee_inf(DEPT_ID, JOB_ID, `NAME`, CARD_ID, ADDRESS, POST_CODE, TEL, PHONE, QQ_NUM,"
                    + " EMAIL, SEX, PARTY, BIRTHDAY, RACE, EDUCATION, SPECIALITY, HOBBY, REMARK, CREATE_DATE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prep.setInt(1, employee.getDeptId());
            prep.setInt(2, employee.getJobId());
            prep.setString(3, employee.getEmployeeName());
            prep.setString(4, employee.getCardId());
            prep.setString(5, employee.getAddress());
            prep.setString(6, employee.getPostCode());
            prep.setString(7, employee.getTel());
            prep.setString(8, employee.getPhone());
            prep.setString(9, employee.getQqNum());
            prep.setString(10, employee.getEmail());
            prep.setInt(11, employee.getSex());
            prep.setString(12, employee.getParty());
            prep.setDate(13, new Date(employee.getBirthday().getTime()));
            prep.setString(14, employee.getRace());
            prep.setString(15, employee.getEducation());
            prep.setString(16, employee.getSpeciality());
            prep.setString(17, employee.getHobby());
            prep.setString(18, employee.getRemark());
            prep.setTimestamp(19, new Timestamp(System.currentTimeMillis()));

            int update = prep.executeUpdate();
            if (update != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prep != null) {
                    prep.close();
                }
                DruidUtil.closeConn(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int removeEmployeeById(int[] ids) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("delete from employee_inf where ID=?");
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
    public boolean updateEmployee(Employee employee) {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("update employee_inf set DEPT_ID=?, JOB_ID=?, `NAME`=?, CARD_ID=?, ADDRESS=?, POST_CODE=?, "
                    + "TEL=?, PHONE=?, QQ_NUM=?, EMAIL=?, SEX=?, PARTY=?, BIRTHDAY=?, RACE=?, EDUCATION=?, SPECIALITY=?, HOBBY=?, "
                    + "REMARK=? where ID=?");
            prep.setInt(1, employee.getDeptId());
            prep.setInt(2, employee.getJobId());
            prep.setString(3, employee.getEmployeeName());
            prep.setString(4, employee.getCardId());
            prep.setString(5, employee.getAddress());
            prep.setString(6, employee.getPostCode());
            prep.setString(7, employee.getTel());
            prep.setString(8, employee.getPhone());
            prep.setString(9, employee.getQqNum());
            prep.setString(10, employee.getEmail());
            prep.setInt(11, employee.getSex());
            prep.setString(12, employee.getParty());
            prep.setDate(13, new Date(employee.getBirthday().getTime()));
            prep.setString(14, employee.getRace());
            prep.setString(15, employee.getEducation());
            prep.setString(16, employee.getSpeciality());
            prep.setString(17, employee.getHobby());
            prep.setString(18, employee.getRemark());
            prep.setInt(19, employee.getEmployeeId());

            if (prep.executeUpdate() != 0) {
                return true;
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
        return false;
    }

    @Override
    public Employee getEmployeeById(int id) {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            prep = connection.prepareStatement("SELECT e.*"
                    + " FROM employee_inf e, job_inf j, dept_inf d"
                    + " WHERE JOB_ID=j.ID AND DEPT_ID=d.ID AND e.ID=?");
            prep.setInt(1, id);
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(resultSet.getInt("ID"));
                emp.setDeptId(resultSet.getInt("DEPT_ID"));
                emp.setJobId(resultSet.getInt("JOB_ID"));
                emp.setEmployeeName(resultSet.getString("NAME"));
                emp.setCardId(resultSet.getString("CARD_ID"));
                emp.setAddress(resultSet.getString("ADDRESS"));
                emp.setPostCode(resultSet.getString("POST_CODE"));
                emp.setTel(resultSet.getString("TEL"));
                emp.setPhone(resultSet.getString("PHONE"));
                emp.setQqNum(resultSet.getString("QQ_NUM"));
                emp.setEmail(resultSet.getString("EMAIL"));
                emp.setSex(resultSet.getInt("SEX"));
                emp.setParty(resultSet.getString("PARTY"));
                emp.setBirthday(resultSet.getDate("BIRTHDAY"));
                emp.setRace(resultSet.getString("RACE"));
                emp.setEducation(resultSet.getString("EDUCATION"));
                emp.setSpeciality(resultSet.getString("SPECIALITY"));
                emp.setHobby(resultSet.getString("HOBBY"));
                emp.setRemark(resultSet.getString("REMARK"));
                emp.setCreateDate(resultSet.getDate("CREATE_DATE"));

                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> listEmployees(Employee employee, PageModel pageModel) {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("SELECT e.*"
                    + " FROM employee_inf e, job_inf j, dept_inf d"
                    + " WHERE JOB_ID=j.ID AND DEPT_ID=d.ID"
                    + " AND e.`NAME` LIKE ?"
                    + " AND e.PHONE LIKE ?"
                    + " AND e.CARD_ID LIKE ?");
            if (employee.getSex() != 0) {
                sqlBuf.append(" AND e.`SEX` LIKE '" + employee.getSex() +"'");
            }
            if (employee.getJobId() != 0) {
                sqlBuf.append(" AND e.`JOB_ID` LIKE '" + employee.getJobId() +"'");
            }
            if (employee.getDeptId() != 0) {
                sqlBuf.append(" AND e.`DEPT_ID` LIKE '" + employee.getDeptId() +"'");
            }
            sqlBuf.append(" limit " + pageModel.getStartIndex() + "," + pageModel.getPageSize());
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, employee.getEmployeeName());
            prep.setString(2, employee.getPhone());
            prep.setString(3, employee.getCardId());
            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(resultSet.getInt("ID"));
                emp.setDeptId(resultSet.getInt("DEPT_ID"));
                emp.setJobId(resultSet.getInt("JOB_ID"));
                emp.setEmployeeName(resultSet.getString("NAME"));
                emp.setCardId(resultSet.getString("CARD_ID"));
                emp.setAddress(resultSet.getString("ADDRESS"));
                emp.setPostCode(resultSet.getString("POST_CODE"));
                emp.setTel(resultSet.getString("TEL"));
                emp.setPhone(resultSet.getString("PHONE"));
                emp.setQqNum(resultSet.getString("QQ_NUM"));
                emp.setEmail(resultSet.getString("EMAIL"));
                emp.setSex(resultSet.getInt("SEX"));
                emp.setParty(resultSet.getString("PARTY"));
                emp.setBirthday(resultSet.getDate("BIRTHDAY"));
                emp.setRace(resultSet.getString("RACE"));
                emp.setEducation(resultSet.getString("EDUCATION"));
                emp.setSpeciality(resultSet.getString("SPECIALITY"));
                emp.setHobby(resultSet.getString("HOBBY"));
                emp.setRemark(resultSet.getString("REMARK"));
                emp.setCreateDate(resultSet.getDate("CREATE_DATE"));

                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public int countTotalRecordSum(Employee employee) {
        Connection connection = null;
        PreparedStatement prep = null;
        int count = 0;
        try {
            connection = DruidUtil.getConnection();
            StringBuffer sqlBuf = new StringBuffer("SELECT count(*)"
                    + " FROM employee_inf e, job_inf j, dept_inf d"
                    + " WHERE JOB_ID=j.ID AND DEPT_ID=d.ID"
                    + " AND e.`NAME` LIKE ?"
                    + " AND e.PHONE LIKE ?"
                    + " AND e.CARD_ID LIKE ?");
            if (employee.getSex() != 0) {
                sqlBuf.append(" AND e.`SEX` LIKE '" + employee.getSex() +"'");
            }
            if (employee.getJobId() != 0) {
                sqlBuf.append(" AND e.`JOB_ID` LIKE '" + employee.getJobId() +"'");
            }
            if (employee.getDeptId() != 0) {
                sqlBuf.append(" AND e.`DEPT_ID` LIKE '" + employee.getDeptId() +"'");
            }
            prep = connection.prepareStatement(sqlBuf.toString());
            prep.setString(1, employee.getEmployeeName());
            prep.setString(2, employee.getPhone());
            prep.setString(3, employee.getCardId());
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
