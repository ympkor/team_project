<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산 추가</title>
<link rel="stylesheet" href="/css/topMenu.css">
<link rel="stylesheet" href="/css/addEditAsset.css">
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
		<input class="value" type="number" name="amount" max="2147483647" min="-2147483647" required placeholder="금액을 숫자로 입력하세요.">
		</div>
		
		<input type="hidden" name="typeBefore" value="${aom.typeBefore}">
        <input type="hidden" name="type" value="${aom.type}">
		
		<div class=addPageTitle style="padding-top:10px;">은행</div>
		<div class=addPageValue>
		<input type="radio" name="assetsId" value="1" id="kb"><label for="kb">국민은행</label>
        <input type="radio" name="assetsId" value="2" id="ibk"><label for="ibk">기업은행</label>
        <input type="radio" name="assetsId" value="3" id="nh"><label for="nh">농협</label>
        <input type="radio" name="assetsId" value="4" id="shinhan"><label for="shinhan">신한은행</label>
        <input type="radio" name="assetsId" value="5" id="keb"><label for="keb">산업은행</label>
        <input type="radio" name="assetsId" value="6" id="woori"><label for="woori">우리은행</label></div>
        <div></div>
        <div class=addPageValue>
        <input type="radio" name="assetsId" value="7" id="city"><label for="city">씨티은행</label>
        <input type="radio" name="assetsId" value="8" id="hana"><label for="hana">하나은행</label>
        <input type="radio" name="assetsId" value="9" id="sc"><label for="sc">SC제일은행</label>
        <input type="radio" name="assetsId" value="10"  id="etc" checked><label for="etc">기타은행</label>
        <input type="radio" name="assetsId" value="24" id="cash"><label for="cash">현금 </label>
        </div>
		
		<div class=addPageTitle>메모</div>
		<div class=addPageValue><input class="value" type="text" name="memo" maxlength="50" placeholder="계좌의 종류 또는 간단한 메모를 입력하세요. (최대 50자)"></div>
		
		<div class=addPageTitle>기록</div>
		<div class=addPageValue><input type="checkbox" name="sync" value="1" style="margin-right:10px;"> 옵션 선택시, 입력한 전액이 수입/지출 내역에 기록됩니다.</div>
		
		<div></div>
		<div class="addButton"><input class="addConfirm" type="submit" value="입력하기" style="cursor:pointer;"> 
		<input class="addConfirm" type="button" value="취소" onClick="location.href='view'" style="cursor:pointer;"></div>

		<div></div>
		<div></div>

</div>
<div></div>

</form>
</middle>

<right>
</right>


</section>



</body>
</html>
