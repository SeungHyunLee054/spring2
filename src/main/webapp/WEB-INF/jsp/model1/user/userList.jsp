<%@ page import="java.util.List" %>
<%@ page import="kr.mjc.lsh2.web.dao.User" %>
<%
  List<User> userList = (List<User>) request.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
  <title>회원목록</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <base href="<%=request.getContextPath()%>/"/>
  <style>
    .grid {
      display: grid;
      grid-template-columns: repeat(3, auto);
    }
  </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/model1/top.jsp" %>
<h3>회원목록</h3>
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