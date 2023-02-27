<!DOCTYPE html>
<html>
<head>
    <title>내정보</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=request.getContextPath()%>/"/>
    <style>
        .grid {
            display: grid;
            grid-template-columns: repeat(2, auto);
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/model1/top.jsp" %>
<h3>내정보</h3>
<div class="grid">
    <div>회원번호</div>
    <div><%=me.getUserId()%>
    </div>
    <div>이메일</div>
    <div><%=me.getEmail()%>
    </div>
    <div>이름</div>
    <div><%=me.getName()%>
    </div>
</div>
<p><a href="./model1/user/passwordEdit">비밀번호 수정</a>
<a href="./model1/user/leaveForm">탈퇴</a></p>
</body>
</html>