package com.hrsys.servlet;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Dept;
import com.hrsys.service.DeptService;
import com.hrsys.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
@WebServlet(urlPatterns = {
        "/show-dept",
        "/save-dept",
        "/saveforward",
        "/update-dept",
        "/updateforward",
        "/delete-dept"})
public class DeptServlet extends HttpServlet {
    private final String SHOW = "show-dept";
    private final String SAVE = "save-dept";
    private final String SAVE_FORWARD = "saveforward";
    private final String UPDATE = "update-dept";
    private final String UPDATE_FORWARD = "updateforward";
    private final String DELETE = "delete-dept";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String action = uri.substring(uri.indexOf("/") + 1, uri.length());
        DeptService deptService = new DeptServiceImpl();

        if (Objects.equals(action, SHOW)) {
            PageModel pageModel = new PageModel();
            String deptName = req.getParameter("deptName");
            String pageIndex = req.getParameter("pageIndex");
            if (pageIndex != null && !Objects.equals(pageIndex, "")) {
                pageModel.setPageIndex(Integer.parseInt(pageIndex));
            } else {
                pageModel.setPageIndex(1);
            }
            List<Dept> deptList = deptService.listDeptByName(deptName, pageModel);
            req.setAttribute("deptList", deptList);
            req.setAttribute("pageModel", pageModel);
            req.setAttribute("deptName", deptName);
            req.getRequestDispatcher("/WEB-INF/jsp/dept/deptlist.jsp").forward(req, resp);
        } else if (Objects.equals(action, SAVE_FORWARD)) {
            req.getRequestDispatcher("/WEB-INF/jsp/dept/deptadd.jsp").forward(req, resp);
        } else if (Objects.equals(action, SAVE)) {
            String deptName = req.getParameter("deptName");
            String deptRemark = req.getParameter("deptRemark");
            Dept dept = new Dept(deptName, deptRemark);
            boolean flag = deptService.insertDeptService(dept);

            resp.setCharacterEncoding("UTF-8");
            if (flag) {
                resp.getWriter().println('1');
            } else {
                resp.getWriter().println('0');
            }

        } else if (Objects.equals(action, UPDATE_FORWARD)) {
            int deptId = Integer.parseInt(req.getParameter("deptId"));
            Dept dept = deptService.getDeptService(deptId);
            req.setAttribute("dept", dept);
            req.getRequestDispatcher("/WEB-INF/jsp/dept/deptEdit.jsp").forward(req, resp);

        } else if (Objects.equals(action, UPDATE)) {
            int id = Integer.parseInt(req.getParameter("deptId"));
            String deptName = req.getParameter("deptName");
            String deptRemark = req.getParameter("deptRemark");
            Dept dept = new Dept(id, deptName, deptRemark);
            boolean flag = deptService.updateDeptService(dept);

            resp.setCharacterEncoding("UTF-8");
            if (flag) {
                resp.getWriter().println('1');
            } else {
                resp.getWriter().println('0');
            }
        } else if (Objects.equals(action, DELETE)) {
            String[] deptIds = req.getParameterValues("deptIds");
            int[] ids = new int[deptIds.length];
            for (int i = 0; i < deptIds.length; i++) {
                ids[i] = Integer.parseInt(deptIds[i]);
            }
            int[] removeSum = deptService.removeDeptService(ids);

            resp.sendRedirect(req.getServletContext().getContextPath() + "/show-dept");
        }
    }
}
