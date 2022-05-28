<%--
  Created by IntelliJ IDEA.
  User: anfzh
  Date: 2022-05-23
  Time: 오후 1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/jquery.js"></script>

</head>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<body>
<jsp:include page="../layout/header.jsp" flush="false"></jsp:include>
<div class="container">

    <table class="table">
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>내용</th>
            <th>조회수</th>
            <th>시간</th>
            <th>삭제</th>
            <th>수정</th>
            <th>페이징목록</th>


        </tr>


        <tr>
            <td>${result.id}</td>
            <td>${result.boardTitle}</td>
            <td>${result.boardWriter}</td>
            <td>${result.boardContents}</td>
            <td>${result.boardHits}</td>
            <td>${result.boardCreatedDate}</td>
            <td><a href="/password?id=${result.id}">삭제</a><br></td>
            <td><a href="/update?id=${result.id}">수정</a><br></td>
            <img src="${pageContext.request.contextPath}/upload/${result.boardFileName}" alt="" height="100"
                 width="100">
            <td><a href="/board/paging?page=${page}">페이징목록</a></td>


        </tr>
    </table>
</div>
<div class="container">
    <div id="comment-write" class="input-group mb-3">
        <input type="text" id="commentWriter" class="form-control" placeholder="작성자">
        <%--        <label for="comment-write">작성자</label>--%>
        <input type="text" id="commentContents" class="form-control" placeholder="내용"><br>
        <button id="comment-write-btn"  class="btn btn-primary">댓글작성</button>
    </div>
</div>
<div id="comment-list" class="container" >
    <table class="table">
        <tr>

            <th>댓글번호</th>
            <th>작성자</th>
            <th>내용</th>
            <th>작성시간</th>

        </tr>
        <c:forEach items="${commentList}" var="comment">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.commentWriter}</td>
                <td>${comment.commentContents}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"
                                    value="${comment.commentCreatedDate}"></fmt:formatDate></td>


            </tr>
        </c:forEach>
    </table>
</div>


</body>
<script>


    $("#comment-write-btn").click(function () {
        // alert("나눌렀어 ?");
//    댓글 작성자 내용을 가져오고
//    ajax 문법을 활용하여 /comment/save 주소로 post 방식으로 작성자,내용,글번호,
//    새개의 값을 보내는 코드를 작성하시오
        const commentContents = document.getElementById("commentContents").value;
        const commentWriter = document.getElementById("commentWriter").value;
        const boardId = '${result.id}';

        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                "commentContents": commentContents,
                "commentWriter": commentWriter,
                "boardId": boardId
            },
            datatype: "json",//json==DTO타입
            success: function (result) {
                            //commentDTOList 리턴받아와 ==result 값에 저장
                console.log(result);
                let output = "<table class='table'>";
                output += "<tr><th>댓글번호</th>";
                output += "<th>작성자</th>";
                output += "<th>내용</th>";
                output += "<th>작성시간</th></tr>";

                for (let i in result) {//forEach문
                    output += "<tr>";
                    output += "<td>" + result[i].id + "</td>";
                    output += "<td>" + result[i].commentWriter + "</td>";
                    output += "<td>" + result[i].commentContents + "</td>";
                    output += "<td>" + moment(result[i].commentCreatedDate).format("YYYY-MM-DD HH:mm:ss") + "</td>";
                    output += "</tr>";//moment 순간
                }
                output += "</table>";
                document.getElementById('comment-list').innerHTML = output;
                document.getElementById('commentWriter').value = '';//입력받은 작성자를 입력한값을 공백값으로 바꿔준다
                document.getElementById('commentContents').value = '';//입력받은 내용 입력한값을 공백값으로 바꿔준다
            },
            error: function () {
                console.log("x")
            }
        })

    });
</script>
</html>
