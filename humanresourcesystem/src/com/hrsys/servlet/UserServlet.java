package com.hrsys.servlet;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.User;
import com.hrsys.service.UserService;
import com.hrsys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
@WebServlet(urlPatterns = {
        "/login",
        "/save-user",
        "/saveuser-forward",
        "/show-user",
        "/update-user",
        "/updateforward-user",
        "/logout",
        "/delete-user"})
public class UserServlet extends HttpServlet {
    private final String LOGIN = "login";
    private final String SAVE = "save-user";
    private final String SAVE_FORWARD = "saveuser-forward";
    private final String SHOW = "show-user";
    private final String UPDATE = "update-user";
    private final String UPDATE_FORWARD = "updateforward-user";
    private final String LOGOUT = "logout";
    private final String DELETE = "delete-user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String action = uri.substring(uri.indexOf("/") + 1, uri.length());
        UserService userService = new UserServiceImpl();

        if (Objects.equals(action, LOGIN)) {
            HttpSession session = req.getSession();
            User user = new User();
            user.setLoginName(req.getParameter("loginName"));
            user.setPwd(req.getParameter("password"));
            if (userService.loginService(user)) {
                session.setAttribute("loginStatus", "ok");
                session.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/jsp/sys/main.jsp").forward(req, resp);
            } else if (Objects.equals(session.getAttribute("loginStatus"), "ok")) {
                req.getRequestDispatcher("/WEB-INF/jsp/sys/main.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");
            }

        } else if (Objects.equals(action, SHOW)) {
            User user = new User();
            PageModel pageModel = new PageModel();
            String username = req.getParameter("username");
            String status = req.getParameter("status");
            String pageIndex = req.getParameter("pageIndex");

            if (pageIndex != null && !Objects.equals(pageIndex, "")) {
                pageModel.setPageIndex(Integer.parseInt(pageIndex));
            } else {
                pageModel.setPageIndex(1);
            }
            if (status != null && !Objects.equals(status, "")) {
                user.setStatus(Integer.parseInt(status));
            }
            user.setUsername(username);
            List<User> users = userService.listUsersService(user, pageModel);
            req.setAttribute("users", users);
            req.setAttribute("pageModel", pageModel);
            req.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE_FORWARD)) {
            req.getRequestDispatcher("/WEB-INF/jsp/user/useradd.jsp").forward(req, resp);

        } else if (Objects.equals(action, SAVE)) {
            String loginName = req.getParameter("loginName");
            String pwd = req.getParameter("pwd");
            int status = Integer.parseInt(req.getParameter("status"));
            String username = req.getParameter("username");
            User user = new User(loginName, pwd, status, username);
            boolean flag = userService.insertUserService(user);

            if (flag) {
                resp.getWriter().println('1');
            } else {
                resp.getWriter().println('0');
            }

        } else if (Objects.equals(action, UPDATE_FORWARD)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            User user = userService.getUserService(userId);
            req.setAttribute("userEdit", user);
            req.getRequestDispatcher("/WEB-INF/jsp/user/userEdit.jsp").forward(req, resp);

        } else if (Objects.equals(action, UPDATE)) {
            int id = Integer.parseInt(req.getParameter("userId"));
            String loginName = req.getParameter("loginName");
            String pwd = req.getParameter("pwd");
            int status = Integer.parseInt(req.getParameter("status"));
            String username = req.getParameter("username");
            User user = new User(loginName, pwd, status, username);
            user.setUserId(id);
            if (userService.updateUserService(user)) {
                resp.getWriter().print('1');
            } else {
                resp.getWriter().print('0');
            }

        } else if (Objects.equals(action, LOGOUT)) {
            HttpSession session = req.getSession(false);
            session.invalidate();
            resp.sendRedirect("/login.jsp");

        } else if (Objects.equals(action, DELETE)) {
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            String[] userIds = req.getParameterValues("userIds");
            List<Integer> ids = new ArrayList<>();
            for (int i = 0; i < userIds.length; i++) {
                ids.add(Integer.parseInt(userIds[i]));
            }
            int[] ints = userService.removeUserService(ids, user.getUserId());

            resp.sendRedirect(req.getServletContext().getContextPath() + "/show-user");
        }
    }
}
