<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/boardJS.js?ver=1"></script>
<link rel="stylesheet" href="/css/board.css?ver=1">
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<!-- 상단 메뉴 부분 -->
	<header class="topmenu">
		<div class="grid_header">
			<div class="basic"><a id=mainlink href="/main/getCal">MAIN</a></div>
			<div class="statistics"><a id=staticlink href="/statistics/show">GRAPH</a></div>
			<div class="assest"><a id=assetlink href="/asset/view">ASSETS</a></div>
			<div class="board"><a id=boardlink href="/board/show">BOARD</a></div>
			<div class="gomypage"><button class="gomypage">MYPAGE</button></div>
			<div class="gologout"><button class="gologout">LOGOUT</button></div>
		</div>
	</header>
<div id="boardcontent">
<div>
<div class="boardtitle">FreeBoard</div>
<table class="boardlist">
<colgroup>
		<col width="10%">
		<col width="50%">
		<col width="15%">
		<col width="15%">
		<col width="5%">
		<col width="5%">
	</colgroup>
	<thead>
		<tr ><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>추천</th><th>조회</th></tr>
	</thead>
	<tbody>
		<c:forEach var="b" items="${bList}" varStatus="vs">
		<tbody onclick="showContent(this)" class="${b.boardId}">						
		<tr class="boardtitlehead"><td>${vs.count}</td><td>${b.title }<c:if test="${b.commentCount>0}">[${b.commentCount }]</c:if></td>
		<td>${b.writer }</td><td>${b.regDate}</td><td>${b.likes }</td><td>${b.hits}</td></tr>
		<tr class="boardID" ><td colspan="6"><div class="contentdiv" >${b.boardId}</div></td></tr>
		</tbody>
		</c:forEach>
	</tbody>

</table>
<button id="writeBoard">글쓰기</button>

</div>
</div>
</div>
</body>