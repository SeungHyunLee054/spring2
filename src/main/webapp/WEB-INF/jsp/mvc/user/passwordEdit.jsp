<!DOCTYPE html>
<html>
<head>
    <title>비밀번호 변경</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<%@ include file="/WEB-INF/jsp/mvc/top.jsp" %>
<h3>비밀번호 변경</h3>
<form action="./mvc/user/updatePassword" method="post">
    <p><input type="password" name="currentPassword" placeholder="현재 비밀번호"
              required autofocus/></p>
    <p><input type="password" name="newPassword" placeholder="새 비밀번호"
              required/>
    </p>
    <p>
        <button type="submit">저장</button>
    </p>
</form>
</body>
</html>