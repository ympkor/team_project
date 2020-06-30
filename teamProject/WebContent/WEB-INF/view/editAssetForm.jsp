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
	<form action="/asset/editAsset?memAssetId=${aom.memAssetId}" method="post">
	<div>
	
		금액<input type="number" name="amount" placeholder="${aom.amount}"><br><br>
		
		구분
		자산<input type="radio" name="type" value="자산">
        부채<input type="radio" name="type" value="부채"><br><br>
		
		은행
		
		국민은행<input type="radio" name="assetsId" value="1">
        기업은행<input type="radio" name="assetsId" value="2">
        농협<input type="radio" name="assetsId" value="3">
        신한은행<input type="radio" name="assetsId" value="4">
        산업은행<input type="radio" name="assetsId" value="5">
        우리은행<input type="radio" name="assetsId" value="6">
        씨티은행<input type="radio" name="assetsId" value="7">
        하나은행<input type="radio" name="assetsId" value="8">
        SC제일은행<input type="radio" name="assetsId" value="9">
        기타<input type="radio" name="assetsId" value="10"><br><br>
        
        메모<input type="text" name="memo" placeholder="${aom.memo}"><br><br>
		
		<input type="submit" value="수정하기"><br>
		<input type="button" value="취소" onClick="location.href='view'">
	</div>
</form>
</body>
</html>