<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/updateMemberJs.js?1"></script>
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<header class="topmenu">
		<div class="grid_header">
			<div class="basic"><a id=mainlink href="/main/getCal">MAIN</a></div>
			<div class="statistics"><a id=staticlink href="/statistics/show">GRAPH</a></div>
			<div class="assest"><a id=assetlink href="/asset/view">ASSETS</a></div>
			<div class="board"><a id=boardlink href="/board/show">BOARD</a></div>
			<div class="gomypage"><button class="gomypage">MYPAGE</button></div>
			<div class="gologout"><button class="gologout">LOGOUT</button></div>
		</div>
	</header>
	<form>
		<div><input type="hidden" name="userKey" value="${member.userKey}"></div>
		<div>회원아이디</div>
		<div><input type="text" name="userId" value="${member.userId}" readonly="readonly"></div>
		<div>회원비밀번호</div>
		<div><input type="password" name="password" maxlength="16" placeholder="비밀번호는 공백없이  문자+숫자 또는 문자+특수문자로만 8~16자이내로 입력해주세요." required></div>
		<div id="passtext"></div>
		<div>비밀번호 재확인</div>
		<div><input type="password" name="password_check" maxlength="16" placeholder="비밀번호와 동일하게 입력해주세요."  required></div>
		<div id="passchtext"></div>
		<div>이름</div>
		<div><input type="text" name="name" value="${member.name}" maxlength="20" placeholder="이름은 앞자리 공백없이 입력해주세요." required></div>
		<div id="nametext"></div>
		<div>이메일</div>
		<div><input type="text" name="email" value="${member.email}" readonly="readonly"></div>
		<div><input type="submit" value="수정하기"></div>
	</form>
</body>
</html>