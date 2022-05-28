<%--
  Created by IntelliJ IDEA.
  User: anfzh
  Date: 2022-05-24
  Time: 오후 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/update" name="update1" >
  <input type="text" name="id" value="${boardDTO.id}" hidden>
    <h6>제목</h6>
    <input type="text" name="boardTitle" value="${boardDTO.boardTitle}">
    <h6>비밀번호</h6>
    <input type="password" name="boardPassword" id="passwordConfirm"><br>
    <h6>내용</h6>
    <textarea name="boardContents">${boardDTO.boardContents}</textarea>
    <br>
  <input type="button" value="수정" onclick="update()">
</form>

</body>
<script>
  function update(){
    const passwordConfirm= document.getElementById("passwordConfirm").value;
    const passwordDB= '${boardDTO.boardPassword}';

    if(passwordConfirm==passwordDB){
      update1.submit()


    }
  }
</script>
</html>
