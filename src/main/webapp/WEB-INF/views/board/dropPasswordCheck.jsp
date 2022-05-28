<%--
  Created by IntelliJ IDEA.
  User: anfzh
  Date: 2022-05-23
  Time: 오후 4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/password" >
    <input type="text" name="id" value="${id}" hidden>
    <input type="password"  name="boardPassword" >
    <input type="submit" value="비밀번호체크">
</form>
</body>
</html>
