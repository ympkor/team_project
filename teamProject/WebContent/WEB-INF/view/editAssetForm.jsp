<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산 수정</title>
<link rel="stylesheet" type="text/css" href="/css/myAssetEdit.css">
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
<script type="text/javascript">
window.onload = function() {
	
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypage";
	}
	document.querySelector(".gologout").onclick = function(){
		location.href="/member/logout";
	}
</script>
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

	<form id="editAsset"
				action="/asset/editAsset?memAssetId=${aom.memAssetId}" method="post">

	<div class="edit">
	
		<div class="editRow"></div>
		<div class="editRow"></div>
		<div class="editRow"></div>
		
		<div class="editRow"></div>
		<div class="editRow"><h1>자산 수정</h1></div>
		<div class="editRow"></div>
		
		<div class="editRow"></div>
		<div class="editRow">
		금액<input type="number" name="amount" value="${aom.amount}" required>
		</div>
		<div class="editRow"></div>
		
		<div class="editRow"></div>
		<div class="editRow">
		구분 : 
		자산<input type="radio" id="asset" name="type" value="자산">
		부채<input type="radio" id="debt" name="type" value="부채">
		</div>
		<div class="editRow"></div>
		
		<div class="editRow"></div>
		<div class="editRow">
		은행 : 
		국민은행<input id="an1" type="radio" name="assetsId" value="1">
		기업은행<input id="an2" type="radio" name="assetsId" value="2">
		농협<input id="an3" type="radio" name="assetsId" value="3">
		신한은행<input id="an4" type="radio" name="assetsId" value="4">
		산업은행<input id="an5" type="radio" name="assetsId" value="5">
		우리은행<input id="an6" type="radio" name="assetsId" value="6">
		씨티은행<input id="an7" type="radio" name="assetsId" value="7">
		하나은행<input id="an8" type="radio" name="assetsId" value="8">
		SC제일은행<input id="an9" type="radio" name="assetsId" value="9">
		기타은행<input id="an10" type="radio" name="assetsId" value="10">
		</div>
		<div class="editRow"></div>
		
		<div class="editRow"></div>
		<div class="editRow">
		메모<input type="text" name="memo" value="${aom.memo}">
		</div>
		<div class="editRow"></div>
		
		<div class="editRow"></div>
		<div class="editRow">
		이 자산을 수입/지출 내역에 기록
		<input id="syncCheck" type="checkbox" name="sync" value="1"><br>
		(옵션 선택시 변동사항이 수입/지출 내역에 기록됩니다.)
		</div>
		<div class="editRow"></div>
		
		
		
		<div class="editRow"></div>
		<div class="editRow">
		<input type="submit" value="수정">
		<input type="button" value="취소" onClick="location.href='view'">
		<a href="delete?memAssetId=${aom.memAssetId}"  style="text-decoration: none; color:black"
					onClick="return confirm('이 자산을 정말 삭제하시겠습니까?')">삭제</a>
		</div>
		<div class="editRow"></div>
		
		<div class="editRow"></div>
		<div class="editRow"></div>
		<div class="editRow"></div>
		
	
	</div>

<script type="text/javascript">

if("${aom.type}"=="자산"){document.forms["editAsset"]["asset"].checked=true;}
else{document.forms["editAsset"]["debt"].checked=true;}

if("${aom.assetsName}"=="국민은행"){document.forms["editAsset"]["an1"].checked=true;}
if("${aom.assetsName}"=="기업은행"){document.forms["editAsset"]["an2"].checked=true;}
if("${aom.assetsName}"=="농협"){document.forms["editAsset"]["an3"].checked=true;}
if("${aom.assetsName}"=="신한은행"){document.forms["editAsset"]["an4"].checked=true;}
if("${aom.assetsName}"=="산업은행"){document.forms["editAsset"]["an5"].checked=true;}
if("${aom.assetsName}"=="우리은행"){document.forms["editAsset"]["an6"].checked=true;}
if("${aom.assetsName}"=="씨티은행"){document.forms["editAsset"]["an7"].checked=true;}
if("${aom.assetsName}"=="하나은행"){document.forms["editAsset"]["an8"].checked=true;}
if("${aom.assetsName}"=="SC제일은행"){document.forms["editAsset"]["an9"].checked=true;}
if("${aom.assetsName}"=="기타은행"){document.forms["editAsset"]["an10"].checked=true;};

</script>

	<input type="hidden" name="amountBefore" value="${originalAmount}">
	<input type="hidden" name="typeBefore" value="${originalType}">
	
	</form>

</body>
</html>
