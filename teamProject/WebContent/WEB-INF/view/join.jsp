<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/joinJs.js"></script>
</head>
<body>
	<form method="post" action="/member/money">
		<div>
			아이디<br>
			<input type="text" name="userId">
			<button type="button" name="equalsId">중복확인</button>
			<div id="idtext"></div>
		</div>
		<div>
			비밀번호<br>
			<input type="password" name="password">
			<div id="passtext"></div>
		</div>
		<div>
			비밀번호 재확인<br>
			<input type="password" name="password_check">
			<div id="passchtext"></div>
		</div>
		<div>
			이름<br>
			<input type="text" name="name">
			<div id="nametext"></div>
		</div>
		<div>
			이메일<br>
			<input type="text" name="email">
			<button type="button" name="equalsEmail">중복확인</button>
			<div id="emailtext"></div>
		</div>
		<div>
			<input type="submit" value="가입하기">
		</div>
	</form>
</body>
</html>