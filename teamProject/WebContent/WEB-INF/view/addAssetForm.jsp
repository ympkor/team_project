<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산 추가</title>
<link rel="stylesheet" href="/css/topMenu.css?ver=2">
<link rel="stylesheet" href="/css/addEditAsset.css?ver=4">
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
	<form action="/asset/addAsset" method="post">
	
	<div class="center_body" style="background:;">
	
	<div></div>
	<div></div>
	
	<div><input type="hidden" name="userKey" value="${userKey}"></div>
	<div style="text-align: center;"><h1 style="font-size:200%;">" 새로운 자산을 추가합니다. "</h1></div>
		
		<div class=addPageTitle>금액</div>
		<div class=addPageValue>
		<fmt:formatNumber type="number" value="" var="newNum" pattern="###,###,###,###"/>
		<input class="value" type="number" name="amount" value="${newNum}" placeholder="금액을 숫자로 입력하세요." required/>
		</div>
		
		<div class=addPageTitle>구분</div>
		<div class=addPageValue>
		<label>자산  <input type="radio" name="type" value="자산" checked></label>
        <label>부채  <input type="radio" name="type" value="부채"></label></div>
		
		<div class=addPageTitle>은행</div>
		<div class=addPageValue>
		<label>국민은행  <input type="radio" name="assetsId" value="1"></label>
        <label>기업은행  <input type="radio" name="assetsId" value="2"></label>
        <label>농협  <input type="radio" name="assetsId" value="3"></label>
        <label>신한은행  <input type="radio" name="assetsId" value="4"></label>
        <label>산업은행  <input type="radio" name="assetsId" value="5"></label></div>
        <div></div>
        <div class=addPageValue><label>우리은행  <input type="radio" name="assetsId" value="6"></label>
        <label>씨티은행  <input type="radio" name="assetsId" value="7"></label>
        <label>하나은행  <input type="radio" name="assetsId" value="8"></label>
        <label>SC제일은행  <input type="radio" name="assetsId" value="9"></label>
        <label>기타  <input type="radio" name="assetsId" value="10" checked></label>
        </div>
		
		<div class=addPageTitle>메모</div>
		<div class=addPageValue><input class="value" type="text" name="memo" maxlength="50" placeholder="계좌의 종류 또는 간단한 메모를 입력하세요. (최대 50자)"></div>
		
		<div class=addPageTitle>기록</div>
		<div class=addPageValue><input type="checkbox" name="sync" value="1" style="margin-right:10px;"> 옵션 선택시 입력한 전액이 수입/지출 내역에 기록됩니다.</div>
		
		<div></div>
		<div class="addButton"><input class="addConfirm" type="submit" value="입력하기" style="cursor:pointer;"> 
		<input class="addConfirm" type="button" value="취소" onClick="location.href='view'" style="cursor:pointer;"></div>
</div>
<div></div>

</form>
</middle>

<right>
</right>


</section>



</body>
</html>
