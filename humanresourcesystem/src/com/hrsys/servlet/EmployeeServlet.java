package com.hrsys.servlet;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Dept;
import com.hrsys.entity.Employee;
import com.hrsys.entity.Job;
import com.hrsys.service.DeptService;
import com.hrsys.service.EmployeeService;
import com.hrsys.service.JobService;
import com.hrsys.service.impl.DeptServiceImpl;
import com.hrsys.service.impl.EmployeeServiceImpl;
import com.hrsys.service.impl.JobServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
@WebServlet(urlPatterns = {"/show-employee",
        "/save-employee",
        "/saveforward-employee",
        "/update-employee",
        "/updateforward-employee",
        "/delete-employee"})
public class EmployeeServlet extends HttpServlet {
    private final String SHOW = "show-employee";
    private final String SAVE = "save-employee";
    private final String SAVE_FORWARD = "saveforward-employee";
    private final String UPDATE = "update-employee";
    private final String UPDATE_FORWARD = "updateforward-employee";
    private final String DELETE = "delete-employee";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String action = uri.substring(uri.indexOf("/") + 1, uri.length());
        EmployeeService employeeService = new EmployeeServiceImpl();


        if (Objects.equals(action, SHOW)) {
            PageModel pageModel = new PageModel();
            Employee employee = new Employee();

            String pageIndex = req.getParameter("pageIndex");
            if (pageIndex == null || Objects.equals(pageIndex, "")) {
                pageModel.setPageIndex(1);
            } else {
                pageModel.setPageIndex(Integer.parseInt(pageIndex));
            }

            String jobId = req.getParameter("jobId");
            String deptId = req.getParameter("deptId");
            String employeeName = req.getParameter("employeeName");
            String sex = req.getParameter("sex");
            String cardId = req.getParameter("cardId");
            String phone = req.getParameter("phone");

            employee.setEmployeeName(employeeName);
            employee.setCardId(cardId);
            employee.setPhone(phone);
            if (jobId != null && !Objects.equals(jobId, "")) {
                employee.setJobId(Integer.parseInt(jobId));
            }
            if (deptId != null && !Objects.equals(deptId, "")) {
                employee.setDeptId(Integer.parseInt(deptId));
            }
            if (sex != null && !Objects.equals(sex, "")) {
                employee.setSex(Integer.parseInt(sex));
            }

            List<Dept> deptList = listAllDepts();
            List<Job> jobs = listAllJobs();
            List<Employee> employees = employeeService.listEmployeesService(employee, pageModel);

            for (Employee emp : employees) {
                System.out.println(emp);
            }
            req.setAttribute("deptList", deptList);
            req.setAttribute("jobs", jobs);
            req.setAttribute("employees", employees);
            req.setAttribute("pageModel", pageModel);

            req.setAttribute("jobId", jobId);
            req.setAttribute("deptId", deptId);
            req.setAttribute("employeeName", employeeName);
            req.setAttribute("sex", sex);
            req.setAttribute("cardId", cardId);
            req.setAttribute("phone", phone);
            req.getRequestDispatcher("/WEB-INF/jsp/employee/employeelist.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE_FORWARD)) {
            List<Dept> deptList = listAllDepts();
            List<Job> jobs = listAllJobs();
            req.setAttribute("deptList", deptList);
            req.setAttribute("jobs", jobs);
            req.getRequestDispatcher("/WEB-INF/jsp/employee/employeeadd.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE)) {
            try {
                int deptId = Integer.parseInt(req.getParameter("deptId"));
                int jobId = Integer.parseInt(req.getParameter("jobId"));
                String employeeName = req.getParameter("employeeName");
                String cardId = req.getParameter("cardId");
                String address = req.getParameter("address");
                String postCode = req.getParameter("postCode");
                String tel = req.getParameter("tel");
                String phone = req.getParameter("phone");
                String qqNum = req.getParameter("qqNum");
                String email = req.getParameter("email");
                int sex = Integer.parseInt(req.getParameter("sex"));
                String party = req.getParameter("party");
                String birthday = req.getParameter("birthday");
                Date date = null;
                if (birthday != null && !Objects.equals(birthday, "")) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                }
                String race = req.getParameter("race");
                String education = req.getParameter("education");
                String speciality = req.getParameter("speciality");
                String hobby = req.getParameter("hobby");
                String remark = req.getParameter("remark");
                Employee employee = new Employee(deptId, jobId, employeeName, cardId, address, postCode, tel, phone, qqNum, email, sex, party, date, race, education, speciality, hobby, remark);

                if (employeeService.insertEmployeeService(employee)) {
                    resp.getWriter().print('1');
                } else {
                    resp.getWriter().print('0');
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (Objects.equals(action, UPDATE_FORWARD)) {
            List<Dept> deptList = listAllDepts();
            List<Job> jobs = listAllJobs();
            int employeeId = Integer.parseInt(req.getParameter("employeeId"));
            Employee employee = employeeService.getEmployeeService(employeeId);
            req.setAttribute("employee", employee);
            req.setAttribute("deptList", deptList);
            req.setAttribute("jobs", jobs);
            req.getRequestDispatcher("/WEB-INF/jsp/employee/employeeEdit.jsp").forward(req, resp);

        } else if (Objects.equals(action, UPDATE)) {
            try {
                int employeeId = Integer.parseInt(req.getParameter("employeeId"));
                int deptId = Integer.parseInt(req.getParameter("deptId"));
                int jobId = Integer.parseInt(req.getParameter("jobId"));
                String employeeName = req.getParameter("employeeName");
                String cardId = req.getParameter("cardId");
                String address = req.getParameter("address");
                String postCode = req.getParameter("postCode");
                String tel = req.getParameter("tel");
                String phone = req.getParameter("phone");
                String qqNum = req.getParameter("qqNum");
                String email = req.getParameter("email");
                int sex = Integer.parseInt(req.getParameter("sex"));
                String party = req.getParameter("party");
                String birthday = req.getParameter("birthday");
                Date date = null;
                if (birthday != null && !Objects.equals(birthday, "")) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                }
                String race = req.getParameter("race");
                String education = req.getParameter("education");
                String speciality = req.getParameter("speciality");
                String hobby = req.getParameter("hobby");
                String remark = req.getParameter("remark");
                Employee employee = new Employee(deptId, jobId, employeeName, cardId, address, postCode, tel, phone, qqNum, email, sex, party, date, race, education, speciality, hobby, remark);
                employee.setEmployeeId(employeeId);

                if (employeeService.updateEmployeesService(employee)) {
                    resp.getWriter().print('1');
                } else {
                    resp.getWriter().print('0');
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(action, DELETE)) {
            String[] employeeIds = req.getParameterValues("employeeIds");
            int[] ids = new int[employeeIds.length];
            for (int i = 0; i < employeeIds.length; i++) {
                ids[i] = Integer.parseInt(employeeIds[i]);
            }
            int[] removeCount = employeeService.removeEmployeeService(ids);

            resp.sendRedirect(req.getServletContext().getContextPath() + "/show-employee");
        }
    }

    private List<Dept> listAllDepts() {
        DeptService deptService = new DeptServiceImpl();
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        List<Dept> deptList = deptService.listDeptByName(null, pageModel);
        return deptList;
    }

    private List<Job> listAllJobs() {
        JobService jobService = new JobServiceImpl();
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        List<Job> jobs = jobService.listJobByName(null, pageModel);
        return jobs;
    }
}
