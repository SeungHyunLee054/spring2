<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>글수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
    <style>
        form input{width: 100%}

        form textarea{width: 100%; height: 200px;}
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/mvc/top.jsp" %>
<h3>글쓰기</h3>
<form action="./mvc/article/updateArticle" method="post">
    <p><input type="text" name="title" value="${article.title}" placeholder="제목" required autofocus/>
    </p>
    <p><textarea name="content" required>${article.content}</textarea></p>
    <p>
        <button type="submit">등록</button>
    </p>
    <input type="hidden" name="articleId" value="${article.articleId}"/>
</form>
</body>
</html>