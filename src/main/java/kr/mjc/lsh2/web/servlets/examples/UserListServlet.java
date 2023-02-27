package kr.mjc.lsh2.web.servlets.examples;

import kr.mjc.lsh2.web.dao.User;
import kr.mjc.lsh2.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/examples/userList")
public class UserListServlet extends HttpServlet {

    @Autowired
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> userList = userDao.listUsers(20, 1);

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        for (User user : userList)
            sb.append("<p>").append(user.toString()).append("</p>");
        sb.append("</body></html>");

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(sb);
        writer.close();
    }
}
