package com.hrsys.dao;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Employee;

import java.util.List;

/**
 * @author steve
 */
public interface EmployeeDao {
    /**
     * insertEmployee
     * @param employee
     * @return
     */
    boolean insertEmployee(Employee employee);

    /**
     * removeEmployee
     * @param ids
     * @return
     */
    int removeEmployeeById(int[] ids);

    /**
     * updateEmployeeById
     * @param employee
     * @return
     */
    boolean updateEmployee(Employee employee);

    /**
     * getEmployeeById
     * @param id
     * @return
     */
    Employee getEmployeeById(int id);

    /**
     * listEmployees
     * @param employee
     * @param pageModel
     * @return
     */
    List<Employee> listEmployees(Employee employee, PageModel pageModel);

    /**
     * countTotalRecordSum 用于统计表中数据数量
     * @param employee
     * @return 返回表中数据条数
     */
    int countTotalRecordSum(Employee employee);
}
