<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글수정</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<link rel="stylesheet" href="/css/writeboard.css?2">
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<script type="text/javascript" src="/js/writeBoardJS.js?ver=2"></script>
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
<div class="content">
<form class="writeForm" name="writeForm" action="/board/contentOneShow" method="post">
	<input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
	<input type="hidden" name="pNum" value="${pNum}">
	<input type="hidden" name="boardId" value="${board.boardId}">
  <input class="writeboardtitle" type="text" name="title" 
  	value="${board.title}" required="required" maxlength="45"><br>
  <textarea id="summernote" name="content">${board.content}</textarea>
  <input class="submit" type="submit" value="글수정">
</form>
</div>
</div>
</body>
</html>