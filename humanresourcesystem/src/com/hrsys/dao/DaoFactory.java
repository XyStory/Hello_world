package com.hrsys.dao;

import com.hrsys.dao.impl.*;

/**
 * @author steve
 */
public class DaoFactory {
    public static UserDao userDao() {
        return new UserDaoImpl();
    }

    public static DeptDao deptDao() {
        return new DeptDaoImpl();
    }

    public static JobDao jobDao() {
        return new JobDaoImpl();
    }

    public static EmployeeDao employeeDao() {
        return new EmployeeDaoImpl();
    }

    public static DocumentDao documentDao() {
        return new DocumentDaoImpl();
    }

    public static NoticeDao noticeDao() {
        return new NoticeDaoImpl();
    }
}
