package kr.mjc.lsh2.web.servlets.mvc;

import kr.mjc.lsh2.web.dao.Article;
import kr.mjc.lsh2.web.dao.ArticleDao;
import kr.mjc.lsh2.web.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    private final ArticleDao articleDao;


    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void articleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count = NumberUtils.toInt(request.getParameter("count"), 20);
        int page = NumberUtils.toInt(request.getParameter("page"), 1);

        List<Article> articleList = articleDao.listArticles(count, page);
        request.setAttribute("articleList", articleList);
        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleList.jsp")
                .forward(request, response);
    }

    public void articleView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));

        Article article = articleDao.getArticle(articleId);
        request.setAttribute("article", article);
        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleView.jsp")
                .forward(request, response);
    }


    public void deleteArticle(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User me = (User) session.getAttribute("ME");
        int articleId = Integer.parseInt(request.getParameter("articleId"));

        int deleteRows =
                articleDao.deleteArticle(articleId, me.getUserId());
        if (deleteRows >= 1) // 업데이트 성공
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    }

    public void addArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        User me = (User) session.getAttribute("ME");
        if(me == null){
            response.sendRedirect(request.getContextPath()+"/mvc/user/signinForm");
            return;
        }
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setUserId(me.getUserId());
        article.setName(me.getName());

        articleDao.addArticle(article);
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    }

    public void articleForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        User me = (User) session.getAttribute("ME");
        String redirectUrl =
                StringUtils.defaultIfEmpty(request.getParameter("redirectUrl"),
                        request.getContextPath() + "/mvc/article/articleForm");
        if(me == null){
            response.sendRedirect(
                    request.getContextPath() + "/mvc/user/signinForm?redirectUrl=" +
                            URLEncoder.encode(redirectUrl, Charset.defaultCharset()));
            return;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleForm.jsp").forward(request, response);
    }

    public void updateArticle(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User me = (User) session.getAttribute("ME");
        Article article = new Article();
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setUserId(me.getUserId());

        int updatedRows = articleDao.updateArticle(article);
        if (updatedRows >=1)
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleView?articleId=" + article.getArticleId());
        else return;
    }

    public void articleEdit(HttpServletRequest request,
                              HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User me = (User) session.getAttribute("ME");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        Article article = articleDao.getArticle(articleId);

        request.setAttribute("article", article);
        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleEdit.jsp").forward(request, response);
    }
}
