package com.hrsys.service.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DaoFactory;
import com.hrsys.dao.EmployeeDao;
import com.hrsys.entity.Employee;
import com.hrsys.service.EmployeeService;

import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        employeeDao = DaoFactory.employeeDao();
    }

    @Override
    public boolean insertEmployeeService(Employee employee) {
        if (employee.getDeptId() == 0 || employee.getJobId() == 0 || Objects.equals(employee.getEmployeeName(), "")
                || Objects.equals(employee.getCardId(), "") || Objects.equals(employee.getAddress(), "")
                || Objects.equals(employee.getPhone(), "") || Objects.equals(employee.getEmail(), "")) {
            return false;
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        List<Employee> employees = listEmployeesService(new Employee(), pageModel);
        for (Employee emp : employees) {
            if (Objects.equals(emp.getCardId(), employee.getCardId())) {
                return false;
            }
        }

        return employeeDao.insertEmployee(employee);
    }

    @Override
    public int[] removeEmployeeService(int[] ids) {
        int success = employeeDao.removeEmployeeById(ids);
        int fail = ids.length - success;
        int[] deleteCount = {success, fail};
        return deleteCount;
    }

    @Override
    public List<Employee> listEmployeesService(Employee employee, PageModel pageModel) {
        if (employee.getEmployeeName() == null || Objects.equals(employee.getEmployeeName(), "")) {
            employee.setEmployeeName("%");
        } else {
            employee.setEmployeeName("%" + employee.getEmployeeName() + "%");
        }

        if (employee.getCardId() == null || Objects.equals(employee.getCardId(), "")) {
            employee.setCardId("%");
        } else {
            employee.setCardId("%" + employee.getCardId() + "%");
        }

        if (employee.getPhone() == null || Objects.equals(employee.getPhone(), "")) {
            employee.setPhone("%");
        } else {
            employee.setPhone("%" + employee.getPhone() + "%");
        }

        int totalCount = employeeDao.countTotalRecordSum(employee);
        pageModel.setTotalRecordSum(totalCount);
        return employeeDao.listEmployees(employee, pageModel);
    }

    @Override
    public boolean updateEmployeesService(Employee employee) {
        if (employee.getDeptId() == 0 || employee.getJobId() == 0 || Objects.equals(employee.getEmployeeName(), "")
                || Objects.equals(employee.getCardId(), "") || Objects.equals(employee.getAddress(), "")
                || Objects.equals(employee.getPhone(), "") || Objects.equals(employee.getEmail(), "")) {
            return false;
        }
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public Employee getEmployeeService(int id) {
        return employeeDao.getEmployeeById(id);
    }
}
