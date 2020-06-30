<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/updateMemberJs.js"></script>
</head>
<body>
	<form method="post" action="/member/mypage">
		<div>회원번호</div>
		<div><input type="text" name="userKey" value="${member.userKey}" readonly="readonly"></div>
		<div>회원아이디</div>
		<div><input type="text" name="userId" value="${member.userId}" readonly="readonly"></div>
		<div>회원비밀번호</div>
		<div><input type="password" name="password"></div>
		<div id="passtext"></div>
		<div>비밀번호 재확인</div>
		<div><input type="password" name="password_check"></div>
		<div id="passchtext"></div>
		<div>이름</div>
		<div><input type="text" name="name" value="${member.name}"></div>
		<div id="nametext"></div>
		<div>이메일</div>
		<div><input type="text" name="email" value="${member.email}" readonly="readonly"></div>
		<div><input type="submit" value="수정하기"></div>
	</form>
</body>
</html>