<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=request.getContextPath()%>/"/>
</head>
<body>
<%@ include file="/WEB-INF/jsp/model1/top.jsp" %>
<h3>로그인</h3>
<form action="./model1/user/signin" method="post">
    <p><input type="email" name="email" placeholder="이메일" required autofocus/>
    </p>
    <p><input type="password" name="password" placeholder="비밀번호" required/></p>
    <p>
        <button type="submit">로그인</button>
    </p>
</form>
</body>
</html>