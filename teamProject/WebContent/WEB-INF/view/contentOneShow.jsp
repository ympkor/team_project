<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/boardOneContentJS.js"></script>
<link rel="stylesheet" href="/css/boardOneContent.css">
</head>
<body>
<table>
	<thead class="contentonehead">
		<tr ><th>제목</th><th>작성자</th><th>작성일</th><th>추천</th><th>조회</th></tr>
	</thead>
	<tbody>
		<tr class="boardtitlehead"><td>${currentBoard.title }</td><td>${currentBoard.writer }</td><td>${currentBoard.regDate}</td><td>${currentBoard.likes }</td><td>${currentBoard.hits}</td></tr>
		<tr><td colspan="5" class="boardcontent">
			<div class="contentdiv" ><pre>${currentBoard.content }</pre></div>
			<div class="udbutton"><button class="contentwriteshow">댓글</button> <label>죠아요<input type="checkbox"></label><button>수정</button><button>삭제</button></div></td>
		</tr>
	</tbody>
</table>
<div class="commentListshow">
<c:forEach var="co" items="${cList}">
	<div>작성자: ${co.userId}<br>
		${co.comment}<br>
		${co.regDate}
	</div>
</c:forEach> 
</div>
<form class="commentwriteinput" action="/board/commentwrite" method="post">
	<input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
	<input type="hidden" name="boardId" value="${currentboardId}">
	<TEXTAREA class="writeboardcontent" name="comment" COLS=30 ROWS=3></TEXTAREA>
	<input type="submit" value="댓글등록">
</form>

	<c:forEach var="b1" items="${bList}" varStatus="vs1">	
		<c:if test="${vs1.count==1 && b1.boardId==(currentboardId-1) }">이전글:<a href="/board/contentOneShow?boardId=${b1.boardId}">${b1.title}</a><br></c:if>
		<c:if test="${vs1.count==3}">다음글:<a href="/board/contentOneShow?boardId=${b1.boardId}">${b1.title}</a><br></c:if>
		<c:if test="${vs1.count==2 && b1.boardId==(currentboardId+1)}">다음글:<a href="/board/contentOneShow?boardId=${b1.boardId}">${b1.title}</a><br></c:if>
	</c:forEach>
<button class="showboardList">목록보기</button>

</body>
</html>