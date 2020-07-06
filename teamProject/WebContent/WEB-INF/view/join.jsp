<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" src="/scripts/amcharts/serial.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/js/joinJs.js?1"></script>
</head>
<body>
	<form >
		<div>
			아이디<br>
			<input type="text" name="userId" maxlength="20" placeholder="아이디는 공백없이 문자+숫자로만 5~20자이내로 입력해주세요." required>
			<button type="button" name="equalsId">중복확인</button>
			<div id="idtext"></div>
		</div>
		<div>
			비밀번호<br>
			<input type="password" name="password" maxlength="16" placeholder="비밀번호는 공백없이  문자+숫자 또는 문자+특수문자로만 8~16자이내로 입력해주세요." required>
			<div id="passtext"></div>
		</div>
		<div>
			비밀번호 재확인<br>
			<input type="password" name="password_check" maxlength="16" placeholder="비밀번호와 동일하게 입력해주세요." required>
			<div id="passchtext"></div>
		</div>
		<div>
			이름<br>
			<input type="text" name="name" maxlength="20" placeholder="이름은 앞자리 공백없이 입력해주세요." required>
			<div id="nametext"></div>
		</div>
		<div>
			이메일<br>
			<input type="text" name="email" maxlength="50" placeholder="이메일은 공백없이 @를 포함해서 입력해주세요." required>
			<button type="button" name="equalsEmail">중복확인</button>
			<div id="emailtext"></div>
		</div>
		<div>
			<input type="hidden" name="cash">
		</div>
		<div>
			<input type="submit" value="가입하기">
		</div>
	</form>
</body>
</html>