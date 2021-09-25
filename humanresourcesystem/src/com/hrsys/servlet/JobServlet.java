package com.hrsys.servlet;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Job;
import com.hrsys.service.JobService;
import com.hrsys.service.impl.JobServiceImpl;

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
@WebServlet(urlPatterns = {"/show-job",
        "/save-job",
        "/savejob-forward",
        "/update-job",
        "/updatejob-forward",
        "/delete-job"})
public class JobServlet extends HttpServlet {
    private final String SHOW = "show-job";
    private final String SAVE = "save-job";
    private final String SAVE_FORWARD = "savejob-forward";
    private final String UPDATE = "update-job";
    private final String UPDATE_FORWARD = "updatejob-forward";
    private final String DELETE = "delete-job";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String action = uri.substring(uri.indexOf("/") + 1, uri.length());
        JobService jobService = new JobServiceImpl();

        if (Objects.equals(action, SHOW)) {
            PageModel pageModel = new PageModel();
            String jobName = req.getParameter("jobName");
            String pageIndex = req.getParameter("pageIndex");
            if (pageIndex != null && !Objects.equals(pageIndex, "")) {
                pageModel.setPageIndex(Integer.parseInt(pageIndex));
            } else {
                pageModel.setPageIndex(1);
            }
            List<Job> jobList = jobService.listJobByName(jobName, pageModel);

            //测试
            for (Job job : jobList) {
                System.out.println(job);
            }

            req.setAttribute("jobList", jobList);
            req.setAttribute("pageModel", pageModel);
            req.getRequestDispatcher("/WEB-INF/jsp/job/joblist.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE_FORWARD)) {
            req.getRequestDispatcher("/WEB-INF/jsp/job/jobadd.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE)) {
            String jobName = req.getParameter("jobName");
            String jobRemark = req.getParameter("jobRemark");
            Job job = new Job(jobName, jobRemark);
            boolean flag = jobService.insertJobService(job);
            if (flag) {
                resp.getWriter().println('1');
            } else {
                resp.getWriter().println('0');
            }

        } else if (Objects.equals(action, UPDATE_FORWARD)) {
            int jobId = Integer.parseInt(req.getParameter("jobId"));
            Job job = jobService.getJobService(jobId);
            req.setAttribute("job", job);
            req.getRequestDispatcher("/WEB-INF/jsp/job/jobEdit.jsp").forward(req, resp);

        } else if (Objects.equals(action, UPDATE)) {
            int id = Integer.parseInt(req.getParameter("jobId"));
            String jobName = req.getParameter("jobName");
            String jobRemark = req.getParameter("jobRemark");
            Job job = new Job(id, jobName, jobRemark);

            if (jobService.updateJobService(job)) {
                resp.getWriter().print('1');
            } else {
                resp.getWriter().print('0');
            }
        } else if (Objects.equals(action, DELETE)) {
            String[] jobIds = req.getParameterValues("jobIds");
            int[] ids = new int[jobIds.length];
            for (int i = 0; i < jobIds.length; i++) {
                ids[i] = Integer.parseInt(jobIds[i]);
            }
            int[] removeSum = jobService.removeJobService(ids);

            //TODO 输出删除成功和删除失败的个数
        }
    }
}
