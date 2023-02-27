<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
    <style>
        h3 {background-color: #ffaaaa;}
        input[name=redirectUrl] {width: 600px;}
    </style>
</head>
<body>
<h3>로그인</h3>
<form action="./mvc/user/signin" method="post">
    <p><input type="email" name="email" placeholder="이메일" required autofocus/>
    </p>
    <p><input type="password" name="password" placeholder="비밀번호" required/></p>
    <p>
        <button type="submit">로그인</button>
    </p>
    <c:choose>
        <c:when test="${!empty param.redirectUrl}">
            <input type="text" name="redirectUrl" value="${param.redirectUrl}"/>
        </c:when>
        <c:otherwise>
            <input type="text" name="redirectUrl" value="${header.referer}" readonly/>
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>