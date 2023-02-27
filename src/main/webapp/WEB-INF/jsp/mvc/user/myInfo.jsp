<!DOCTYPE html>
<html>
<head>
    <title>내정보</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
    <style>
        .grid {
            display: grid;
            grid-template-columns: repeat(2, auto);
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/mvc/top.jsp" %>
<h3>내정보</h3>
<div class="grid">
    <div>회원번호</div>
    <div>${sessionScope.ME.userId}</div>
    <div>이메일</div>
    <div>${sessionScope.ME.email}</div>
    <div>이름</div>
    <div>${sessionScope.ME.name}</div>
</div>
<p>
    <a href="./mvc/user/passwordEdit">비밀번호 수정</a>
    <a href="./mvc/user/leaveForm">탈퇴</a>
</p>
</body>
</html>