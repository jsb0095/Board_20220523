<%--
  Created by IntelliJ IDEA.
  User: anfzh
  Date: 2022-05-23
  Time: 오전 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/resources/js/jquery.js" ></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false"></jsp:include>
<form action="/board/save" method="post">
    <input type="text"  name="boardWriter" placeholder="작성자"><br>
    <br>
    <input type="text" name="boardTitle" placeholder="제목"><br>
    <br>
    <input type="password" name="boardPassword" placeholder="비밀번호"><br>
    <br>
    <textarea name="boardContents" type="text" placeholder="내용"></textarea>
    <br>
    <input type="submit" value="글작성">
</form>
</body>
</html>
