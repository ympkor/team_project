<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/boardJS.js"></script>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
<div id="topmenu">
<div class="basic"><a href="/main/getCal">가계부</a></div>
<div class="statistics"><a href="/statistics/show">통계</a></div>
<div class="assest"><a href="/asset/view">자산</a></div>
<div class="board"><a href="/board/show">게시판</a></div>
</div>
<div id="boardcontent">
<div>공지사항</div>
<div>1:1 문의 내역  <button>1:1 문의하기</button> </div>
<div> <h3>자유게시판</h3>
<table>
	<thead>
		<tr ><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>추천</th><th>조회</th></tr>
	</thead>
	<tbody>
		<c:forEach var="b" items="${bList}" varStatus="vs">
		<tbody onclick="showContent(this)" class="${b.boardId}">						
		<tr class="boardtitlehead"><td>${vs.count}</td><td>${b.title }</td><td>${b.writer }</td><td>${b.regDate}</td><td>${b.likes }</td><td>${b.hits}</td></tr>
		<tr class="boardID" ><td colspan="6"><div class="contentdiv" >${b.boardId}</div></td></tr>
		</tbody>
		</c:forEach>
	</tbody>

</table>
<button id="writeBoard">글쓰기</button>

</div>


</div>

</body>
</html>