<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Object user_key = session.getAttribute("userKey");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=user_key%>
	<br><a href="/member/mypage">마이페이지로</a>
	<br><a href="/statistics/show">통계로 가보까</a>
	<br>
	<a href="/member/logout">로그아웃</a>
</body>
</html>