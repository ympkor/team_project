<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산 수정</title>

</head>
<body>

	<h1>자산 수정</h1>
	<form id="editAsset" action="/asset/editAsset?memAssetId=${aom.memAssetId}" method="post">
	<div>
	
		금액<input type="number" name="amount" value="${aom.amount}" required><br><br>
		
		
		구분<br>
		자산<input type="radio" id="asset" name="type" value="자산">
        부채<input type="radio" id="debt" name="type" value="부채"><br><br>
		
		
		은행
		
		국민은행<input id="an1" type="radio" name="assetsId" value="1">
        기업은행<input id="an2" type="radio" name="assetsId" value="2">
        농협<input id="an3" type="radio" name="assetsId" value="3">
        신한은행<input id="an4" type="radio" name="assetsId" value="4">
        산업은행<input id="an5" type="radio" name="assetsId" value="5">
        우리은행<input id="an6" type="radio" name="assetsId" value="6">
        씨티은행<input id="an7" type="radio" name="assetsId" value="7">
        하나은행<input id="an8" type="radio" name="assetsId" value="8">
        SC제일은행<input id="an9" type="radio" name="assetsId" value="9">
        기타은행<input id="an10" type="radio" name="assetsId" value="10"><br><br>
        
        메모<input type="text" name="memo" value="${aom.memo}"><br><br>
		
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
if("${aom.assetsName}"=="기타은행"){document.forms["editAsset"]["an10"].checked=true;}
	
</script>
		
		
		<input type="submit" value="수정하기"><br>
		<input type="button" value="취소" onClick="location.href='view'">
	</div>
</form>
</body>
</html>
