<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지비밀번호재확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/mypageProcCss.css">
<script type="text/javascript" src="/js/mypageProcJs.js"></script>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<div id="mypageProc_container">
		<div id="header">
			<a id="title" href="/main/getCal">더조은가계부</a>
		</div>
		<div id="mypageProc">
			<div>
				<h3 id="mypageProc_title">회원 비밀번호 확인</h3>
				<div id="text1">비밀번호를 한번 더 입력해주세요.</div>
				<div id="text2">회원님의 정보를 안전하게 보호하기 위해서 비밀번호를 한번 더 확인합니다.</div>
				<div>
					<form class="mypageProc_form">
						<div id="id">아이디</div>
						<div>
							<input id="id_input" type="text" name="userId" value="${member.userId}" readonly="readonly">
						</div>
						<div id="pass">비밀번호</div>
						<div>
							<input id="pass_input" type="password" name="password" placeholder="비밀번호" maxlength="16" required="required">
						</div>
						<div class="button">
							<input id="submit" type="submit" value="확인">
							<input id="cancel"type="button" value="취소" onclick="history.go(-1)">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>