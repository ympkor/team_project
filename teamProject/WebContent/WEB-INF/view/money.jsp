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
	<a href="/statistics/show">통계로 가자!!</a>
</body>
</html>