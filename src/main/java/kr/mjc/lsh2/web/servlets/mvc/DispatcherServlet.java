package kr.mjc.lsh2.web.servlets.mvc;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mvc/*")
public class DispatcherServlet extends HttpServlet {

    private UserController userController;
    private ArticleController articleController;

    @Override
    public void init() {
        WebApplicationContext wac =
                (WebApplicationContext) getServletContext().getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        userController = wac.getBean(UserController.class);
        articleController = wac.getBean(ArticleController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/user/userList" -> userController.userList(request, response);
            case "/user/signout" -> userController.signout(request, response);
            case "/user/userInfo" -> userController.userInfo(request, response);
            case "/article/articleList" -> articleController.articleList(request, response);
            case "/article/articleView" -> articleController.articleView(request, response);
            case "/article/articleForm" -> articleController.articleForm(request, response);
            case "/article/deleteArticle" -> articleController.deleteArticle(request, response);
            case "/article/articleEdit" -> articleController.articleEdit(request, response);
            default -> mapDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/user/signin" -> userController.signin(request, response);
            case "/user/signup" -> userController.signup(request, response);
            case "/user/updatePassword" ->
                    userController.updatePassword(request, response);
            case "/user/leave" -> userController.leave(request, response);
            case "/article/addArticle" -> articleController.addArticle(request, response);
            case "/article/updateArticle" -> articleController.updateArticle(request, response);
        }
    }

    /**
     * 디폴트 매핑
     */
    private void mapDefault(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        request.getRequestDispatcher("/WEB-INF/jsp/mvc" + pathInfo + ".jsp")
                .forward(request, response);
    }
}