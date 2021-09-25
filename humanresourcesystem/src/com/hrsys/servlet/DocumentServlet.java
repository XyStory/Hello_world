package com.hrsys.servlet;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Document;
import com.hrsys.entity.User;
import com.hrsys.service.DocumentService;
import com.hrsys.service.UserService;
import com.hrsys.service.impl.DocumentServiceImpl;
import com.hrsys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/show-document",
        "/upload",
        "/upload-forward",
        "/download",
        "/delete-document"})
public class DocumentServlet extends HttpServlet {
    private final String SHOW = "show-document";
    private final String UPLOAD = "upload";
    private final String UPLOAD_FORWARD = "upload-forward";
    private final String DOWNLOAD = "download";
    private final String DELETE = "delete-document";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DocumentService documentService = new DocumentServiceImpl();
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String action = uri.substring(uri.indexOf("/") + 1, uri.length());

        if (Objects.equals(action, SHOW)) {
            Document document = new Document();
            PageModel pageModel = new PageModel();
            String title = req.getParameter("title");
            String fileName = req.getParameter("filename");
            String pageIndex = req.getParameter("pageIndex");

            if (pageIndex == null || Objects.equals(pageIndex, "")) {
                pageModel.setPageIndex(1);
            } else {
                pageModel.setPageIndex(Integer.parseInt(pageIndex));
            }
            document.setTitle(title);
            document.setFilename(fileName);
            List<Document> documents = documentService.listDocuments(document, pageModel);
            List<User> users = listAllUsers();

            req.setAttribute("users", users);
            req.setAttribute("documents", documents);
            req.setAttribute("pageModel", pageModel);
            req.getRequestDispatcher("WEB-INF/jsp/document/documentlist.jsp").forward(req, resp);

        } else if (Objects.equals(action, UPLOAD_FORWARD)) {
            req.getRequestDispatcher("/WEB-INF/jsp/document/documentadd.jsp").forward(req, resp);

        } else if (Objects.equals(action, UPLOAD)) {
            String title = req.getParameter("title");
            String remark = req.getParameter("remark");
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            Part part = req.getPart("file");
            String fileName = part.getSubmittedFileName();

            List<Document> documents = listAllFileName(documentService);
            for (Document document : documents) {
                if (Objects.equals(fileName, document.getFilename())) {
                    resp.getWriter().print('0');
                    return;
                }
            }

            Document document = new Document(title, fileName, remark, user.getUserId());

            String realPath = getServletContext().getRealPath("/WEB-INF/upload");

            System.out.println(title + "\n" + remark + "\n" + part + "\n" + fileName + "\n" + realPath);
            System.out.println(document.toString());

            boolean flag = documentService.insertDocumentService(document);
            resp.setCharacterEncoding("UTF-8");
            if (flag) {
                File file = new File(realPath, fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                InputStream in = part.getInputStream();
                FileOutputStream out = new FileOutputStream(file);
                int len = -1;
                byte[] bytes = new byte[1024 * 8];
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                out.close();
                in.close();

                resp.getWriter().print('1');
            } else {
                resp.getWriter().print('0');
            }

        } else if (Objects.equals(action, DOWNLOAD)) {
            String[] documentIds = req.getParameterValues("documentIds");
            int[] ids = new int[documentIds.length];
            for (int i = 0; i < documentIds.length; i++) {
                ids[i] = Integer.parseInt(documentIds[i]);
            }
            List<String> fileNameList = documentService.listFileNamesService(ids);
            for (String fileName : fileNameList) {
                String realPath = getServletContext().getRealPath("/WEB-INF/upload");
                File filePath = new File(realPath, fileName);

                fileName = URLEncoder.encode(fileName, "UTF-8");
                resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                resp.setContentType("multipart/form-data");

                BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
                BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
                int len = -1;
                while ((len = in.read()) != -1) {
                    out.write(len);
                    out.flush();
                }
                out.close();
                in.close();
            }

        } else if (Objects.equals(action, DELETE)) {
            boolean flag = false;
            String[] documentIds = req.getParameterValues("documentIds");
            int[] ids = new int[documentIds.length];
            for (int i = 0; i < documentIds.length; i++) {
                ids[i] = Integer.parseInt(documentIds[i]);
            }
            String realPath = getServletContext().getRealPath("/WEB-INF/upload");
            List<String> fileNames = documentService.listFileNamesService(ids);
            for (String fileName : fileNames) {
                File filePath = new File(realPath, fileName);
                if (filePath.exists()) {
                    flag = filePath.delete();
                    System.out.println(flag);
                }
            }
            if (flag) {
                int[] removeArray = documentService.removeDocumentService(ids);
            } else {
                //TODO 输出删除失败
                
            }
            resp.sendRedirect(req.getServletContext().getContextPath() + "/show-document");
        }
    }

    private List<User> listAllUsers() {
        UserService userService = new UserServiceImpl();
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        return userService.listUsersService(new User(), pageModel);
    }

    private List<Document> listAllFileName(DocumentService documentService) {
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        return documentService.listDocuments(new Document(), pageModel);
    }
}
