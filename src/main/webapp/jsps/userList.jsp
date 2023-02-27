<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="kr.mjc.lsh2.web.dao.User" %>
<%@ page import="kr.mjc.lsh2.web.dao.UserDao" %>
<%
    WebApplicationContext wac = (WebApplicationContext) application.getAttribute(
            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    UserDao userDao = wac.getBean(UserDao.class);
    List<User> userList = userDao.listUsers(20, 1);
%>
<!DOCTYPE html>
<html>
<head>
    <title>회원 목록</title>
    <style>
        .grid {
            display: grid;
            grid-template-columns: auto auto auto;
        }
    </style>
</head>
<body>
<h3>회원 목록</h3>
<div class="grid">
    <div>번호</div>
    <div>이메일</div>
    <div>이름</div>
    <% for (User user : userList) {%>
    <div><%=user.getUserId()%>
    </div>
    <div><%=user.getEmail()%>
    </div>
    <div><%=user.getName()%>
    </div>
    <% }%>
</div>
</body>
</html>