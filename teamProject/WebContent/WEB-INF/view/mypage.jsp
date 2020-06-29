<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div>회원번호</div>
	<div>${member.userKey}</div>
	<div>회원아이디</div>
	<div>${member.userId}</div>
	<div>이름</div>
	<div>${member.name}</div>
	<div>이메일</div>
	<div>${member.email}</div>
	<div>
		<button type="button" onclick="location.href='/member/update'">수정하기</button>
		<button type="button" name="location.href='/member/delete'">탈퇴하기</button>
	</div>
</body>
</html>