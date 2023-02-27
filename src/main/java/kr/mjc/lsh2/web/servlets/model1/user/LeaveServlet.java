package kr.mjc.lsh2.web.servlets.model1.user;

import kr.mjc.lsh2.web.dao.User;
import kr.mjc.lsh2.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/model1/user/leave")
public class LeaveServlet extends HttpServlet {

    @Autowired
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User me = (User) session.getAttribute("ME");
        String currentPassword = request.getParameter("currentPassword");
        int deleteRows =
                userDao.deleteUser(me.getUserId(), currentPassword);
        if (deleteRows >= 1) // 업데이트 성공
            response.sendRedirect(request.getContextPath() + "/model1/user/signout");
        else  // 업데이트 실패
            response.sendRedirect(
                    request.getContextPath() + "/model1/user/leaveForm");
    }
}