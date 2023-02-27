<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background-color:#ffaaaa; padding:0.5rem;">
    <a href="./">홈</a> |
    <a href="./mvc/user/userList?count=20&page=1">회원</a> |
    <a href="./mvc/article/articleList?count=20&page=1">게시판</a> |
    <c:if test="${empty sessionScope.ME}">
        <a href="./mvc/user/signinForm">로그인</a> |
        <a href="./mvc/user/signupForm">회원가입</a>
    </c:if>
    <c:if test="${!empty sessionScope.ME}">
        <a href="./mvc/user/myInfo">${sessionScope.ME.name}님</a> |
        <a href="./mvc/user/signout">로그아웃</a>
    </c:if>
</div>