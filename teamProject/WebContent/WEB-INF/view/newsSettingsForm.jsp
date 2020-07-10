<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 수신 설정</title>
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
<link rel="stylesheet" href="/css/editNews.css?ver=3">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<header class="topmenu">
		<div class="grid_header">
			<div class="basic"><a id=mainlink href="/main/getCal">MAIN</a></div>
			<div class="statistics"><a id=staticlink href="/statistics/show">GRAPH</a></div>
			<div class="assest"><a id=assetlink href="/asset/view"
			style="text-decoration: underline; text-shadow: 2px 1px 0 #f5f5f5;">ASSETS</a></div>
			<div class="board"><a id=boardlink href="/board/show">BOARD</a></div>
			<div class="gomypage"><button class="gomypage" onClick="location.href='/member/mypageProc'">MYPAGE</button></div>
			<div class="gologout"><button class="gologout" onClick="location.href='/member/logout'">LOGOUT</button></div>
		</div>
	</header>
	
	<section>
	
	<left></left>
	
	<middle>
	<form id="newsSettings" action="/asset/newsSettingsResult" method="post">

	<div class="center_body" >

	<div></div>

	<div style="font-size:150%; color:#999999; text-align: center;">" 입력하신 키워드를 기반으로 추천 기사를 제공합니다. "</div>
	<div class="value">
	<input style="text-align: center;"type="text" class="value" name="newsKeywords" value="${newsKeywords}" required placeholder="자산 금리 저축 적금"></div>
	<div></div>
	<div><input class="addConfirm" type="submit" value="수정">
	<input class="addConfirm" type="button" value="취소" onClick="location.href='view'"></div>

	<div></div>

	</div>
</form>
</middle>
	
<right>
</right>

</section>
	
</body>
</html>
