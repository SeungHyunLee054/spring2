<!DOCTYPE html>
<html>
<head>
    <title>게시글</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<%@ include file="/WEB-INF/jsp/mvc/top.jsp" %>
<h3>게시글</h3>
<div>
    <c:if test="${article.userId == ME.userId}">
        <p><a href="./mvc/article/articleEdit?articleId=${article.articleId}">수정</a> |
            <a id="btnDel" href="./mvc/article/deleteArticle?articleId=${article.articleId}" >삭제</a></p>
    </c:if>
    <p>${article.title}</p>
    <p><a href="./mvc/user/userInfo?userId=${article.userId}">${article.name}</a> | ${article.udate}</p>
    <p>${article.contentHtml}</p>
</div>
<script>
    document.querySelector("#btnDel").onclick = function (){
        return confirm("삭제하시겠습니까?");
    };
</script>
</body>
</html>