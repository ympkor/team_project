<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" src="/scripts/amcharts/serial.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/js/joinJs.js"></script>
<link rel="stylesheet" href="/css/joinCss.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
</head>
<body>
	<div id="join_container">
		<div id="header"></div>
		<div id="join">
			<div>
				<h3 id="join_title">회원가입</h3>
				<div>
					<form class="join_form"> 
						<div id="id">아이디</div>
						<div>
							<input id="id_input" type="text" name="userId" maxlength="20" placeholder="아이디(문자+숫자로만 5~20자이내)" required>
							<button id="id_button" type="button" name="equalsId">중복확인</button>
						</div>
						<div id="idtext"></div>
						<div id="pass">비밀번호</div>
						<div>
							<input id="pass_input" type="password" name="password" maxlength="16" placeholder="비밀번호(문자+숫자 또는 문자+특수문자로만 8~16자이내)" required>
						</div>
						<div id="passtext"></div>
						<div id="passck">비밀번호 재확인</div>
						<div>
							<input id="passck_input" type="password" name="password_check" maxlength="16" placeholder="비밀번호 확인" required>
						</div>
						<div id="passchtext"></div>
						<div id="name">이름</div>
						<div>
							<input id="name_input" type="text" name="name" maxlength="20" placeholder="이름" required>
						</div>
						<div id="nametext"></div>
						<div id="email">이메일</div>
						<div>
							<input id="email_input" type="text" name="email" maxlength="50" placeholder="이메일(@포함)" required>
							<button id="email_button" type="button" name="equalsEmail">중복확인</button>
						</div>
						<div id="emailtext"></div>
						<div>
							<input type="hidden" name="cash">
						</div>
						<div class="button"><input id="search" type="submit" value="가입하기">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>