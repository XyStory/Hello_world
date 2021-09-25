package com.hrsys.service;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Employee;

import java.util.List;

/**
 * @author steve
 */
public interface EmployeeService {
    /**
     * insertEmployeeService
     * @param employee
     * @return
     */
    boolean insertEmployeeService(Employee employee);

    /**
     * removeEmployeeService
     * @param ids
     * @return
     */
    int[] removeEmployeeService(int[] ids);

    /**
     * listEmployeesService
     * @param employee
     * @param pageModel
     * @return
     */
    List<Employee> listEmployeesService(Employee employee, PageModel pageModel);

    /**
     * updateEmployeesService
     * @param employee
     * @return
     */
    boolean updateEmployeesService(Employee employee);

    /**
     * getEmployeeService
     * @param id
     * @return
     */
    Employee getEmployeeService(int id);
}
