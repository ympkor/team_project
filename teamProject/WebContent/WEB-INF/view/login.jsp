<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").on("submit", function(){
			var formData = $("form").eq(0).serialize();
			console.log(formData);
			$.ajax({
				url:"/member/login",
				type:"post",
				data:formData,
				success:function(data){
					console.log(data);
					if(data=="성공"){
						alert("로그인 성공!!");
						//sessionStorage.setItem("member", userKey);
						document.location.href="/member/money";
					} else if(data=="패스워드 틀림"){
						alert("잘못된 비밀번호입니다.");
						$("input[name=password]").focus();
					} else {
						alert("없는 아이디이거나, 아이디가 틀렸습니다.");
						$("input[name=userId]").focus();
					}
				}
			});
			return false;
		});
	});
</script>
</head>
<body>
	<div>
		<form>
			<div><input type="text"  name="userId" placeholder="ID"></div>
			<div><input type="password" name="password" placeholder="password"></div>
			<div><input type="submit" value="로그인"></div>
		</form>
	</div>
	<div>
		<div><a href="/searchId.jsp">아이디찾기</a></div>
		<div><a href="">비밀번호찾기</a></div>
		<div><a href="/member/join">회원가입</a></div>
	</div>
	<div>
		<img alt="" src="">
	</div>
</body>
</html>