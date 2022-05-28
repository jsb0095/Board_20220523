<%--
  Created by IntelliJ IDEA.
  User: anfzh
  Date: 2022-05-24
  Time: 오후 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/saveFile" enctype="multipart/form-data">
    <input type="text" name="id" value="${boardDTO.id}" hidden>
    <h6>제목</h6>
    <input type="text" name="boardTitle" value="${boardDTO.boardTitle}">
    <h6>작성자</h6>
    <input type="text"  name="boardWriter" value="${boardDTO.boardWriter}">
    <h6>비밀번호</h6>
    <input type="password" name="boardPassword" id="passwordConfirm"><br>
    <h6>내용</h6>
    <textarea name="boardContents">${boardDTO.boardContents}</textarea>
    <br>
    <h6>첨부파일</h6>
    <input type="file" name="boardFile"><br>
    <input type="submit"  value="글등록" onclick="update()">
</form>
</body>
<script>

</script>
</html>
