<!DOCTYPE html>
<html>
<head>
    <title>게시판</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
    <style>
        .grid {
            display: grid;
            grid-template-columns: repeat(4, auto);
        }
        input[name=redirectUrl] {width: 600px;}
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/mvc/top.jsp" %>
<h3>게시판</h3>
<p><a href="./mvc/article/articleForm">글쓰기</a></p>
<div class="grid">
    <div>번호</div>
    <div>제목</div>
    <div>글쓴이</div>
    <div>등록일시</div>
    <c:forEach var="article" items="${articleList}">
        <div>${article.articleId}</div>
        <div><a href="./mvc/article/articleView?articleId=${article.articleId}">${article.title}</a>
        </div>
        <div><a href="./mvc/user/userInfo?userId=${article.userId}">${article.name}</a>
        </div>
        <div>${article.udate}</div>
    </c:forEach>
</div>
</body>
</html>