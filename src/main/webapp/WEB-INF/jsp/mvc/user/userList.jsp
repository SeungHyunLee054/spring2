<!DOCTYPE html>
<html>
<head>
  <title>회원목록</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <base href="${pageContext.request.contextPath}/"/>
  <style>
    .grid {
      display: grid;
      grid-template-columns: repeat(3, auto);
    }
  </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/mvc/top.jsp" %>
<h3>회원목록</h3>
<div class="grid">
  <div>번호</div>
  <div>이메일</div>
  <div>이름</div>
  <c:forEach var="user" items="${userList}">
    <div><a href="./mvc/user/userInfo?userId=${user.userId}">${user.userId}</a>
    </div>
    <div>${user.email}</div>
    <div>${user.name}</div>
  </c:forEach>
</div>
</body>
</html>