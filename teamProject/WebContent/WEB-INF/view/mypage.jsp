<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/mypageJs.js"></script>
<link rel="stylesheet" href="/css/mypageCss.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<div id="mypage_container">
		<div id="header">
			<a id="title" href="/main/getCal">더조은가계부</a>
		</div>
		<div id="mypage">
			<div class="mypage_form">
				<h3 id="mypage_title">마이페이지</h3>
				<div id="userkey"><input type="hidden" name="userKey"></div>
				<div id="id">회원아이디</div>
				<div id="id_val">${member.userId}</div>
				<div id="name">이름</div>
				<div id="name_val">${member.name}</div>	
				<div id="email">이메일</div>
				<div id="email_val">${member.email}</div>
				<div><input type="hidden" name="password"></div>
				<div class="button">
					<button id="update" type="button" onclick="location.href='/member/update'">수정하기</button>
					<button id="delete" type="button" name="delete">탈퇴하기</button>
					<button id="back" type="button" onclick="location.href='/main/getCal'">가계부로 돌아가기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>