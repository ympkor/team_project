<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지비밀번호재확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").submit(function(){
			var pw = $("form").eq(0).serialize();
			//console.log(pw)
			$.ajax({
				url:"/member/mypageProc",
				type:"post",
				data:pw,
				success:function(data){
					//console.log(pw)
					if(data=="비밀번호틀림"){
						alert("비밀번호가 일치하지 않습니다");
					} else {
						location.href="/member/mypage"
					}
				}
			})
		});
	});
</script>
</head>
<body>
	<form>
		<div><input type="text" name="userId" value="${member.userId}" readonly="readonly"></div>
		<div><input type="password" name="password" placeholder="비밀번호"></div>
		<div><input type="submit" value="확인"></div>
	</form>
</body>
</html>