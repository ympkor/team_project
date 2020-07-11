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
<link rel="stylesheet" href="/css/addEditAsset.css">
<link rel="stylesheet" href="/css/topMenu.css">
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
	<form id="editAsset"
				action="/asset/editAsset?memAssetId=${aom.memAssetId}" method="post">

	<div class="center_body" style="background:;">
	
	<div></div>
	<div></div>

	<div><input type="hidden" name="userKey" value="${userKey}"></div>
	<div style="text-align: center;"><h1 style="font-size:200%; margin-bottom: 50px;">
	" ${aom.assetsName} 자산 ${aom.amount}원을 수정합니다. "</h1></div>

		<div class=addPageTitle>금액</div>
		<div class=addPageValue>
		<input class="value" type="number" name="amount" max="2147483647" min="-2147483647" value="${aom.amount}" required>
		<input type="hidden" name="amountBefore" max="2147483647" min="-2147483647" value="${aom.amountBefore}">
		</div>
		
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
		<div class=addPageValue><input class="value" type="text" name="memo" maxlength="50" value="${aom.memo}"></div>
		
		<div class=addPageTitle>기록</div>
		<div class=addPageValue><input type="checkbox" name="sync" value="1" style="margin-right:10px;"> 옵션 선택시 변경사항이 수입/지출 내역에도 기록됩니다.</div>
		
		<div></div>
		<div class="addButton">
		<input class="addConfirm" type="submit" value="수정하기"> 
		<input class="addConfirm" type="button" value="취소" onClick="location.href='view'">
		<a id=delText href="delete?memAssetId=${aom.memAssetId}"  style="text-decoration: none; color:black"
					onClick="return confirm('이 자산을 정말 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.')">
					<span id=delBox><p>삭제</p></span></a></div>
					
		<div></div>
		<div></div>
</div>
<div></div>

</form>
</middle>
	
<right>
</right>

</section>

<script type="text/javascript">

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
if("${aom.assetsName}"=="현금"){document.forms["editAsset"]["an24"].checked=true;};

</script>

	<input type="hidden" name="amountBefore" value="${originalAmount}">
	<input type="hidden" name="typeBefore" value="${originalType}">
	

</body>
</html>
