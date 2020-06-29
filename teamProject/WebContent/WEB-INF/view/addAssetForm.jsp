<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산 추가</title>
</head>
<body style="background: #FFDF24">
	<div
		style="width =: 520px; height: 100%; text-align: center; font-size: 20px; float: left;">
		<div style="height: 150px;">
			<h1>내 자산 현황</h1>
			<h1>총 ${sumAsset}원</h1>
			<br>
		</div>
		<c:forEach items="${aomList}" var="list">
			<div
				style="width: 500px; height: 100px; border: 1px solid black; border-radius: 5px; margin: 5px; background: yellow; opacity: 0.8; padding: 10px;">
				${list.name} 님의 ${list.type}<br> ${list.assetsName}<br>
				${list.amount}원<br> ${list.memo}
				<div style="text-align: right; margin-top: -20px;">
					<input type="button" name="editAsset" value="수정"> <input
						type="button" name="deleteAsset" value="삭제">
				</div>
			</div>
			<br>
		</c:forEach>

	</div>
	<h1>자산 추가</h1>
	<form action="/asset/addAsset" method="post" style="padding-top:200px;">
	<div>
		
		금액<input type="number" name="amount" placeholder="금액"><br><br>
		
		구분
		자산<input type="radio" name="type" value="자산">
        부채<input type="radio" name="type" value="부채"><br><br>
		
		은행
		국민은행<input type="radio" name="assetsID" value="1">
        기업은행<input type="radio" name="assetsID" value="2">
        농협<input type="radio" name="assetsID" value="3">
        신한은행<input type="radio" name="assetsID" value="4">
        산업은행<input type="radio" name="assetsID" value="5">
        우리은행<input type="radio" name="assetsID" value="6">
        씨티은행<input type="radio" name="assetsID" value="7">
        하나은행<input type="radio" name="assetsID" value="8">
        SC제일은행<input type="radio" name="assetsID" value="9">
        기타<input type="radio" name="assetsID" value="10"><br><br>
        
        메모<input type="text" name="memo" placeholder="적금, 학자금대출 등"><br><br>
		
		<input type="submit" value="입력하기"><br>
		<input type="button" value="취소" onClick="location.href='view'">
	</div>
</form>
</body>
</html>