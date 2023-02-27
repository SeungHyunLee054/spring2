<%@ page import="kr.mjc.lsh2.web.dao.User" %>
<%
    User me = (User) session.getAttribute("ME");
%>
<div style="background-color:#aaaaff; padding:0.5rem;">
    <a href="./model1/user/userList?count=20&page=1">회원</a> |
    <% if (me == null) { // 로그인 안함 %>
    <a href="./model1/user/signinForm">로그인</a> |
    <a href="./model1/user/signupForm">회원가입</a>
    <% } else { // 로그인 함 %>
    <a href="./model1/user/myInfo"><%=me.getName()%>님</a> |
    <a href="./model1/user/signout">로그아웃</a>
    <% } %>
</div>