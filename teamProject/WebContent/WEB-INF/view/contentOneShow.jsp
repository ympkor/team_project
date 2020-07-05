<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/boardOneContentJS.js?1"></script>
<link rel="stylesheet" href="/css/boardOneContent.css">
</head>
<body>
<div id="topmenu">
<div class="basic"><a href="/main/getCal">가계부</a></div>
<div class="statistics"><a href="/statistics/show">통계</a></div>
<div class="assest"><a href="/asset/view">자산</a></div>
<div class="board"><a href="/board/show">게시판</a></div>
<button class="gomypage">마이페이지</button>
<button class="gologout">로그아웃</button>
</div>
<div id="middlecontent">
<table>
	<thead class="contentonehead">
		<tr ><th>제목</th><th>작성자</th><th>작성일</th><th>추천</th><th>조회</th></tr>
	</thead>
	<tbody>
		<tr class="boardtitlehead"><td>${currentBoard.title }</td><td>${currentBoard.writer }</td><td>${currentBoard.regDate}</td><td>${currentBoard.likes }</td><td>${currentBoard.hits}</td></tr>
		<tr>
			<td colspan="5" class="boardcontent">
				<div class="contentdiv" ><pre>${currentBoard.content }</pre></div>
				<div class="udbutton"><button class="commentwriteshow">댓글</button> <label>죠아요<input type="checkbox"></label>
			 		<c:if test="${currentBoard.userKey==userKey }"><button class="boardupdate">수정</button><button class="boarddelete">삭제</button></c:if>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<div class="commentListshow">
<c:forEach var="co" items="${cList}">
	<div class="commentshow">작성자: ${co.commentWriter}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${co.regDate}
		<c:if test="${co.userKey==userKey }"><span class="commentUDbuttons"><button class="commentupdate" name="${co.commentId}">수정</button><button class="commentdelete" name="${co.commentId}">삭제</button></span></c:if>
		<div class="commentcontent">${co.comment}</div>				
	</div>
</c:forEach> 
</div>
<form class="commentwriteinput" action="/board/commentwrite" method="post">
	<input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
	<input type="hidden" name="boardId" value="${currentboardId}">
	<TEXTAREA class="writeboardcontent" name="comment" COLS=30 ROWS=3 ></TEXTAREA>
	<input type="submit" value="댓글등록">
</form>

	<c:forEach var="b1" items="${bList}" varStatus="vs1">	
		<c:if test="${vs1.count==1 && b1.boardId==(currentboardId-1) }">이전글:<a href="/board/contentOneShow?boardId=${b1.boardId}">${b1.title}</a><br></c:if>
		<c:if test="${vs1.count==3}">다음글:<a href="/board/contentOneShow?boardId=${b1.boardId}">${b1.title}</a><br></c:if>
		<c:if test="${vs1.count==2 && b1.boardId==(currentboardId+1)}">다음글:<a href="/board/contentOneShow?boardId=${b1.boardId}">${b1.title}</a><br></c:if>
	</c:forEach>
<button class="showboardList">목록보기</button>
</div>
<script type="text/javascript">
var boardId= ${currentBoard.boardId}
</script>
</body>
</html>