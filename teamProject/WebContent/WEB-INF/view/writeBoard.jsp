<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글쓰기</title>

</head>
<body>
<form class="writeboardtitle" action="/board/show" method="post">
	<input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
	제목:<input class="writeboardtitle" type="text" name="title"><br>
	내용:<TEXTAREA class="writeboardcontent" name="content" COLS=30 ROWS=3></TEXTAREA>
	<input type="submit" value="글등록">
</form>
</body>
</html>