<!DOCTYPE html>
<html>
<head>
    <title>회원정보</title>
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
<h3>회원정보</h3>
<div class="grid">
    <div>회원번호</div>
    <div>${user.userId}</div>
    <div>이메일</div>
    <div>${user.email}</div>
    <div>이름</div>
    <div>${user.name}</div>
</div>
</body>
</html>