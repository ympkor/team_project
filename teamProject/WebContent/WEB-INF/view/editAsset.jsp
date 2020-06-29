<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산 입력</title>

<style>
</style>
</head>

<body style="background: yellow">
	<form action="/asset/edit">
	<div style="font-size: 40px;">
		<h1>내 자산 입력</h1>
		자산 <input type="radio" name="assetType" value="자산">
		부채 <input type="radio" name="assetType" value="부채"><br><br>
		<input type="number" name="assetAmount" placeholder="금액을 숫자로 입력하세요"><br><br>
		
		자산<br>
		국민은행<input type="radio" name="asset" value="1"><!--이것도 네임 묶어줘야-->
        기업은행<input type="radio" name="asset" value="2">
        농협<input type="radio" name="asset" value="3">
        신한은행<input type="radio" name="asset" value="4">
        산업은행<input type="radio" name="asset" value="5">
        우리은행<input type="radio" name="asset" value="6">
        씨티은행<input type="radio" name="asset" value="7">
        하나은행<input type="radio" name="asset" value="8">
        SC제일은행<input type="radio" name="asset" value="9">
        기타은행<input type="radio" name="asset" value="10"><br><br>
		
		부채<br>
		<input type="text" name="memo" placeholder="부채 유형을 입력하세요"><br><br>
		
		<input type="submit" value="입력완료">

	</div>
	</form>
</body>
</html>