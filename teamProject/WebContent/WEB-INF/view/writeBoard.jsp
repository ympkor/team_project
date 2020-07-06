<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글쓰기</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<link rel="stylesheet" href="/css/writeboard.css">
<script type="text/javascript" src="/js/writeBoardJS.js?ver=1"></script>
</head>
<body>
<div class="wrapper">
<div id="topmenu">
<div class="basic"><a href="/main/getCal">가계부</a></div>
<div class="statistics"><a href="/statistics/show">통계</a></div>
<div class="assest"><a href="/asset/view">자산</a></div>
<div class="board"><a href="/board/show">게시판</a></div>
<button class="gomypage">마이페이지</button>
<button class="gologout">로그아웃</button>
</div>
<div class="content">
<form class="writeForm" name="writeForm" action="/board/show" method="post">
	<input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
  <input class="writeboardtitle" type="text" name="title" placeholder="제목"><br>
  <textarea id="summernote" name="content"></textarea>
  <input class="submit" type="submit" value="글등록">
</form>
</div>
</div>
</body>
</html>