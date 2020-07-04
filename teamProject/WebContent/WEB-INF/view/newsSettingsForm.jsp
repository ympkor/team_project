<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 수신 설정</title>

</head>
<body>

	<h1>뉴스서비스 키워드 설정</h1>
	<form id="newsSettings" action="/asset/newsSettingsResult" method="post">
	<div>
		<input type="text" name="newsKeywords" value="${newsKeywords}" required placeholder="자산 저축 부동산 주식"><br><br>
		
		<input type="submit" value="수정"><br>
		<input type="button" value="취소" onClick="location.href='view'">
	</div>
</form>
</body>
</html>
