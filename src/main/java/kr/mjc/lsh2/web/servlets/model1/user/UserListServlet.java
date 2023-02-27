package kr.mjc.lsh2.web.servlets.model1.user;

import kr.mjc.lsh2.web.dao.User;
import kr.mjc.lsh2.web.dao.UserDao;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 회원 목록 화면
 */
@WebServlet("/model1/user/userList")
public class UserListServlet extends HttpServlet {

    @Autowired
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count = NumberUtils.toInt(request.getParameter("count"), 20);
        int page = NumberUtils.toInt(request.getParameter("page"), 1);
        List<User> userList = userDao.listUsers(count, page);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/WEB-INF/jsp/model1/user/userList.jsp")
                .forward(request, response);
    }
}