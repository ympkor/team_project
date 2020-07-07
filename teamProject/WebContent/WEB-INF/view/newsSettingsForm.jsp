<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 수신 설정</title>
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
</head>
<body>
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
