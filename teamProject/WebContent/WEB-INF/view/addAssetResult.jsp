<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>데이터를 성공적으로 추가했습니다.</h2><br>
	금액 :  ${aom.amount}<br>
	구분 : ${aom.type}<br>
	은행 : ${aom.assetsId}<br>
	메모 : ${aom.memo}<br>
	<a href="/asset/view">내 자산 페이지로 가기</a>
</body>
</html>