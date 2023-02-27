<!DOCTYPE html>
<html>
<head>
    <title>탈퇴</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<%@ include file="/WEB-INF/jsp/mvc/top.jsp" %>
<h3>탈퇴</h3>
<form action="./mvc/user/leave" method="post">
    <p><input type="password" name="currentPassword" placeholder="현재 비밀번호"
              required autofocus/></p>
    <p>
        <button type="submit">탈퇴</button>
    </p>
</form>
</body>
</html>