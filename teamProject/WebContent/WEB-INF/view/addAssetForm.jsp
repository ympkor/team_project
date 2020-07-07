<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산 추가</title>
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
	<h1>자산 추가</h1>
	<form action="/asset/addAsset" method="post">
	<div>
		<input type="hidden" name="userKey" value="${userKey}">
		
		금액<br>
		<input type="number" name="amount" placeholder="금액" required><br><br>
		
		구분<br>
		자산<input type="radio" name="type" value="자산" checked>
        부채<input type="radio" name="type" value="부채"><br><br>
		
		은행<br>
		국민은행<input type="radio" name="assetsId" value="1">
        기업은행<input type="radio" name="assetsId" value="2">
        농협<input type="radio" name="assetsId" value="3">
        신한은행<input type="radio" name="assetsId" value="4">
        산업은행<input type="radio" name="assetsId" value="5">
        우리은행<input type="radio" name="assetsId" value="6">
        씨티은행<input type="radio" name="assetsId" value="7">
        하나은행<input type="radio" name="assetsId" value="8">
        SC제일은행<input type="radio" name="assetsId" value="9">
        기타<input type="radio" name="assetsId" value="10" checked><br><br>
        
        메모<br>
        <input type="text" name="memo" placeholder="적금, 학자금대출 등" required><br><br>
		
		이 자산을 수입/지출 내역에 기록
		<input type="checkbox" name="sync" value="1"><br>
		(옵션 선택시 입력한 전액이 수입/지출 내역에 기록됩니다.)
		<br><br>
		
		<input type="submit" value="입력하기"> 
		<input type="button" value="취소" onClick="location.href='view'">
	</div>
</form>
</body>
</html>
