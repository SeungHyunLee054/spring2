package kr.mjc.lsh2.web.servlets.model1.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 기본 매핑
 */
@WebServlet("/model1/*")
public class DefaultMappingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        request.getRequestDispatcher("/WEB-INF/jsp/model1" + pathInfo + ".jsp")
                .forward(request, response);
    }
}