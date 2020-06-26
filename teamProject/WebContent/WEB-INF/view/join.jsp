<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").submit(function(){
			var id = $("input[name=userId]").val();
		 	if(id.length < 5 || id.length > 20){
	 			alert("아이디는 5~20자리 이내로 입력해주세요.");
		 		$("input[name=userId]").focus();
		 		return false;
	 		 }else if(id.search(/\s/) != -1){
		 		alert("아이디는 공백 없이 입력해주세요.");
		 		$("input[name=userId]").focus();
		 		return false;
	 		 }else if(/(\w)\1\1\1/.test(id)){
		 		alert("같은 문자를 4번이상 사용하실 수 없습니다");
		 		$("input[name=userId]").focus();
		 		return false;
	 		 }
			
		 	if(idck == 0){
	 			 alert("중복확인버튼을 눌러주세요");
	 			 return false;
	 		 }
		 	
			var pass = $("input[name=password]").val();
	 		var num = pass.search(/[0-9]/g);
		 	var eng = pass.search(/[a-z]/ig);
		 	var spe = pass.search(/[~!@#$%^&*()_]/gi);
		 	
		 	if(pass.length < 8 || pass.length > 16){
				alert("비밀번호는 8~16자리 이내로 입력해주세요.");
				$("input[name=password]").focus();
				return false;
		 	} else if(pass.search(/\s/) != -1){
		 		alert("비밀번호는 공백 없이 입력해주세요.");
		 		$("input[name=password]").focus();
		 		return false;
		 	}else if(/(\w)\1\1\1/.test(pass)){
		 		alert("같은 문자를 4번이상 사용하실 수 없습니다");
		 		$("input[name=password]").focus(); 
		 		return false;
	 		 } else if((num < 0 && eng < 0) || (eng < 0 && spe < 0)){
	 			 alert("문자+숫자 또는 문자+특수문자로만 입력해주세요.");
	 			$("input[name=password]").focus();
		 		return false;
	 		 } else if(pass.search(id) > -1){
	 			 alert("비밀번호에 아이디가 포함되었습니다.");
	 			  return false;
	 		 }
			
			var pass_check=$("input[name=password_check]").val().trim();
			if(pass_check != pass){
				alert("비밀번호와 다릅니다.");
				$("input[name=password_check]").focus();
				return false;
			}
			
			var name=$("input[name=name]").val().trim();
			if(name == ""){
				alert("이름을 입력하세요.");
				$("input[name=name]").focus();
				return false;
			}
			
			var email=$("input[name=email]").val().trim();
			var emailRegExp = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+\.[a-zA-z]{2,3}$/;
			if(!emailRegExp.test(email)){
				alert("이메일 형식이 잘못되었습니다.");
				$("input[name=email]").focus();
				return false;
			}
		alert("회원가입완료!!\n자산추가페이지로 이동합니다.");
		});
	});
	var idck=0;	
	$(function(){
		$("button[name=equalsId]").on("click", function(){
			var equalId = $("input[name=userId]").eq(0).serialize();
			console.log(equalId);
			$.ajax({
		 		url:"/member/equalsId",
		 		type:"post",
		 		data:equalId,
		 		success:function(data){
		 			console.log(data);
		 			if(data=="중복되지 않음"){
		 				idck=1;
	               		alert("사용가능한 아이디입니다.");
			        }else if(data=="중복됨"){
	                  	alert("중복된 아이디입니다.");
		 			}
		 		}
		 	});
			return false;
		});
	});

</script>
</head>
<body>
	<form method="post" action="/member/money">
		<div>
			아이디<br>
			<input type="text" name="userId">
			<button type="button" name="equalsId">중복확인</button>
		</div>
		<div>
			비밀번호<br>
			<input type="password" name="password">
		</div>
		<div>
			비밀번호 재확인<br>
			<input type="password" name="password_check">
		</div>
		<div>
			이름<br>
			<input type="text" name="name">
		</div>
		<div>
			이메일<br>
			<input type="text" name="email">
		</div>
		<div>
			<input type="submit" value="가입하기">
		</div>
	</form>
</body>
</html>