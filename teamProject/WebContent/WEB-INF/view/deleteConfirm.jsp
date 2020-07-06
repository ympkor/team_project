<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${result=='삭제성공' }">탈퇴에 성공하셨습니다.<%session.invalidate();%>
		<a href="/member/login">로그인페이지로 돌아갑니다</a>
	 </c:when>
	<c:otherwise>탈퇴 실패입니다
		<a href="/member/mypage">마이페이지로 돌아갑니다</a>
	</c:otherwise>
</c:choose>


</body>
</html>