package com.hrsys.servlet;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Notice;
import com.hrsys.entity.User;
import com.hrsys.service.NoticeService;
import com.hrsys.service.UserService;
import com.hrsys.service.impl.NoticeServiceImpl;
import com.hrsys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author steve
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/show-notice",
        "/save-notice",
        "/saveForward-notice",
        "/delete-notice"})
public class NoticeServlet extends HttpServlet {
    private final String SHOW = "show-notice";
    private final String SAVE = "save-notice";
    private final String SAVE_FORWARD = "saveForward-notice";
    private final String DELETE = "delete-notice";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String action = uri.substring(uri.indexOf("/") + 1, uri.length());
        NoticeService noticeService = new NoticeServiceImpl();

        if (Objects.equals(action, SHOW)) {
            PageModel pageModel = new PageModel();
            String pageIndex = req.getParameter("pageIndex");
            if (pageIndex == null || Objects.equals(pageIndex, "")) {
                pageModel.setPageSize(1);
            } else {
                pageModel.setPageSize(Integer.parseInt(pageIndex));
            }
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            int userId = Integer.parseInt(req.getParameter("userId"));
            Notice notice = new Notice(title, content, userId);
            
            List<Notice> notices = noticeService.listNoticesService(notice, pageModel);
            List<User> users = listAllUsers();
            req.setAttribute("notices", notices);
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/notice/noticelist.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE_FORWARD)) {
            req.getRequestDispatcher("/WEB-INF/jsp/notice/noticeadd.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE)) {
            HttpSession session = req.getSession(false);
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            User user = (User) session.getAttribute("user");

            boolean flag = noticeService.insertNoticeService(new Notice(title, content, user.getUserId()));
            if (flag) {
                resp.getWriter().print('1');
            } else {
                resp.getWriter().print('0');
            }

        } else if (Objects.equals(action, DELETE)) {
            String[] noticeIds = req.getParameterValues("noticeId");
            int[] ids = new int[noticeIds.length];
            for (int i = 0; i < noticeIds.length; i++) {
                ids[i] = Integer.parseInt(noticeIds[i]);
            }
            int[] ints = noticeService.removeNoticeService(ids);

            resp.sendRedirect(req.getServletContext().getContextPath() + "/show-notice");
        }
    }

    private List<User> listAllUsers() {
        UserService userService = new UserServiceImpl();
        User user = new User();
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        return userService.listUsersService(user, pageModel);
    }
}
