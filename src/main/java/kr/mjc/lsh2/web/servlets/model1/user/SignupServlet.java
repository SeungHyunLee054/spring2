package kr.mjc.lsh2.web.servlets.model1.user;

import kr.mjc.lsh2.web.dao.User;
import kr.mjc.lsh2.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 회원 등록
 */
@WebServlet("/model1/user/signup")
@Slf4j
public class SignupServlet extends HttpServlet {

    @Autowired
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));

        try {
            userDao.addUser(user);
            // 등록 성공
            response.sendRedirect(
                    request.getContextPath() + "/model1/user/signinForm");
        } catch (DataAccessException e) { // 등록 실패
            log.error(e.toString());
            response.sendRedirect(
                    request.getContextPath() + "/model1/user/signupForm");
        }
    }
}