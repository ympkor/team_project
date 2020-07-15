<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/updateMemberJs.js"></script>
<link rel="stylesheet" href="/css/updateMemberCss.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<div id="update_container">
		<div id="header">
			<a id="title" href="/main/getCal">더조은가계부</a>
		</div>
		<div id="update">
			<div>
				<h3 id="update_title">회원정보수정</h3>
				<form class="update_form">
					<div id="userkey"><input type="hidden" name="userKey" value="${member.userKey}"></div>
					<div id="id">회원아이디(아이디는 수정이 불가능합니다.)</div>
					<div><input id="id_val" type="text" name="userId" value="${member.userId}" readonly="readonly"></div>
					<div id="pass">회원비밀번호</div>
					<div><input id="pass_val" type="password" name="password" maxlength="16" placeholder="비밀번호(문자+숫자 또는 문자+특수문자로만 8~16자이내)" required></div>
					<div id="passtext"></div>
					<div id="passck">비밀번호 재확인</div>
					<div><input id="passck_val" type="password" name="password_check" maxlength="16" placeholder="비밀번호 확인"  required></div>
					<div id="passchtext"></div>
					<div id="name">이름</div>
					<div><input id="name_val" type="text" name="name" value="${member.name}" maxlength="20" placeholder="이름" required></div>
					<div id="nametext"></div>
					<div id="email">이메일(이메일은 수정이 불가능합니다.)</div>
					<div><input id="email_val" type="text" name="email" value="${member.email}" readonly="readonly"></div>
					<div class="button"><input id="submit" type="submit" value="수정하기"></div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>