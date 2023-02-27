<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
    <style>
        h3 {background-color: #ffaaaa;}
    </style>
</head>
<body>
<h3>회원가입</h3>
<form action="./mvc/user/signup" method="post">
    <p><input type="email" name="email" placeholder="이메일" required autofocus/>
    </p>
    <p><input type="password" name="password" placeholder="비밀번호" required/>
    </p>
    <p><input type="text" name="name" placeholder="이름" required/></p>
    <p>
        <button type="submit">등록</button>
    </p>
</form>
</body>
</html>