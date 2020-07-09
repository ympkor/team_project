<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/searchPwJs.js"></script>
<link rel="stylesheet" href="/css/searchPwCss.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<div id="searchPw_container">
		<div id="header">
			<a id="title" href="/member/login">더조은가계부</a>
		</div>
		<div id="searchPw">
			<div>
				<h3 id="pw_title">비밀번호 찾기</h3>
				<div>
					<form class="searchPw_form">
						<div id="name">이름 </div>
						<div><input  id="name_input" type="text" name="name" maxlength="20" placeholder="가입시 등록하신 이름을 입력해주세요" required></div>
						<div id="id">아이디 </div>
						<div><input id="id_input" type="text" name="userId" maxlength="20" placeholder="가입시 등록하신 아이디를 입력해주세요" required></div>
						<div id="email">이메일 </div>
						<div><input  id="email_input" type="text" name="email" maxlength="50" placeholder="가입시 등록하신 이메일 주소를 입력해주세요" required></div>
						<div class="button"><input id="search" type="submit" value="비밀번호찾기"></div>
					</form>
				</div>
				<div id="message"></div>
			</div>
		</div>
	</div>
</body>
</html>