<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<title>로그인창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/loginJs.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.1/css/all.css" integrity="sha384-xxzQGERXS00kBmZW/6qxqJPyxW3UR0BPsL4c8ILaIWXva5kFi7TxkIIaMiKtqV1Q" crossorigin="anonymous">
</head>
<body>
	<div>
		<form class="login">
			<div><input type="text"  name="userId" placeholder="ID" maxlength="20"></div>
			<div><input type="password" name="password" placeholder="password" maxlength="16"></div>
			<i class="far fa-eye"></i>
			<div id="error"></div>
			<div><input type="submit" value="로그인"></div>
		</form>
	</div>
	<div>
		<div><a href="/member/searchId" onclick="location.href='/member/searchId'">아이디찾기</a></div>
		<div><a href="/member/searchPw" onclick="location.href='/member/searchPw'">비밀번호찾기</a></div>
		<div><a href="/member/join">회원가입</a></div>
	</div>
	<div>
		<img alt="" src="">
	</div>
</body>