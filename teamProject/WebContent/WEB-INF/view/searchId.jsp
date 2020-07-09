<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/searchIdJs.js"></script>
<link rel="stylesheet" href="/css/searchIdCss.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<div id="searchId_container">
		<div id="header">
			<a id="title" href="/member/login">더조은가계부</a>
		</div>
		<div id="searchId">
			<div>
				<h3 id="id_title">아이디 찾기</h3>
				<div>
					<form class="searchId_form">
						<div id="email">이메일 </div>
						<div><input  id="email_input" type="text" name="email" maxlength="50" placeholder="가입시 등록하신 이메일 주소를 입력해주세요" required></div>
						<div id="name">이름 </div>
						<div><input  id="name_input" type="text" name="name" maxlength="20" placeholder="가입시 등록하신 이름을 입력해주세요" required></div>
						<div class="button"><input id="search" type="submit" value="아이디찾기"></div>
					</form>
				</div>
				<div id="message"></div>
			</div>
		</div>
	</div>
</body>
</html>