$(function(){
	//비밀번호칸에 작성시 아래경고창
	$("input[name=password]").on("keyup", function(){
		var pass = $("input[name=password]").val();
		var id = $("input[name=userId]").val();
 		var num = pass.search(/[0-9]/g);
	 	var eng = pass.search(/[a-z]/ig);
	 	var spe = pass.search(/[~!@#$%^&*()_]/gi);
 		if(pass.trim()==""){
			document.getElementById("passtext").innerHTML="필수 정보입니다.";
		}else if(pass.length < 8 || pass.length > 16){
			document.getElementById("passtext").innerHTML="비밀번호는 8~16자리 이내로 입력해주세요.";
	 	} else if(pass.search(/\s/) != -1){
	 		document.getElementById("passtext").innerHTML="비밀번호는 공백 없이 입력해주세요.";
	 	}else if(/(\w)\1\1\1/.test(pass)){
	 		document.getElementById("passtext").innerHTML="같은 문자를 4번이상 사용하실 수 없습니다";
 		 } else if((num < 0 || eng < 0) && (eng < 0 || spe < 0)){
 			document.getElementById("passtext").innerHTML="문자+숫자 또는 문자+특수문자로만 입력해주세요.";
 			document.getElementById("passtext").innerHTML+="<br>특수문자는 ~!@#$%^&*()_ 만 사용가능합니다.";
 		 } else if(pass.search(id) > -1){
 			document.getElementById("passtext").innerHTML="비밀번호에 아이디가 포함되었습니다.";
 		 } else {
 			document.getElementById("passtext").innerHTML=""
 		 }
	});
	
	//비밀번호체크칸에 작성시 아래경고창
	$("input[name=password_check]").on("keyup", function(){
		var pass = $("input[name=password]").val();
		var pass_check=$("input[name=password_check]").val();
		if(pass_check.trim()==""){
			document.getElementById("passchtext").innerHTML="필수 정보입니다.";
		}else if(pass_check.search(/\s/) != -1){
			document.getElementById("passchtext").innerHTML="비밀번호체크는 공백 없이 입력해주세요.";
		}else if(pass_check != pass){
			document.getElementById("passchtext").innerHTML="비밀번호가 일치하지 않습니다.";
		} else {
	 		document.getElementById("passchtext").innerHTML="";
		}
	});
	
	//이름칸에 작성시 아래 경고창
	$("input[name=name]").on("keyup", function(){
		var name=$("input[name=name]").val();
		if(name.trim()==""){
			document.getElementById("nametext").innerHTML="필수 정보입니다.";
 		} else if(name.search(/^\s/) != -1){
 			document.getElementById("nametext").innerHTML="이름은 앞자리 공백 없이 입력해주세요.";
		} else {
 			document.getElementById("nametext").innerHTML="";
		}
	})
	
	$("form").submit(function(){
		//비밀번호 유효성체크(8~16자리, 공백없음, 같은문자 반복안됨, 문자+숫자 또는 문자+특수문자 조합으로만 가능, 아이디와 동일하면 안됨)
		var id = $("input[name=userId]").val();
		var pass = $("input[name=password]").val();
		//입력받은 비밀번호에 정규식에 해당되는 값들이 있는지 검사함
		var numPass = pass.search(/[0-9]/g);
		var engPass = pass.search(/[a-z]/ig);
		var spePass = pass.search(/[~!@#$%^&*()_]/gi);
		//비밀번호가 8자 미만 또는 16자 초과일 때
		if(pass.length < 8 || pass.length > 16){
			$("input[name=password]").focus();
			return false;
		//비밀번호 입력시 공백이 있을 때
		} else if(pass.search(/\s/) != -1){
			$("input[name=password]").focus();
			return false;
		//똑같은 문자가 4개연속으로 들어왔을 때
		}else if(/(\w)\1\1\1/.test(pass)){
			$("input[name=password]").focus(); 
			return false;
		//숫자와 문자 또는 문자와 특수문자 조합이 안되어있을 때
		} else if((numPass < 0 || engPass < 0) && (engPass < 0 || spePass < 0)){
			$("input[name=password]").focus();
			return false;
		//입력한 비밀번호에 아이디값이 있을 때
		} else if(pass.search(id) > -1){
			$("input[name=password]").focus();
			return false;
		//공백포함 빈칸일 때
		} else if(pass.trim() == ""){
			alert("비밀번호를 입력해주세요");
			$("input[name=password]").focus();
			return false;
		//비밀번호창이 공백일 때(회원가입처음 들어갔을 때 가입방지용)
		} else if(pass == null){
			alert("비밀번호를 입력해주세요");
			$("input[name=pass]").focus();
			return false;
		}
		
		//비밀번호확인 유효성체크
		var pass_check=$("input[name=password_check]").val();
		//비밀번호의 값과 비밀번호확인값이 다를 때
		if(pass_check!=pass){
			alert("비밀번호와 비밀번호 확인창이 일치하지 않습니다.");
			$("input[name=password_check]").focus();
			return false;
		//공백포함 빈칸일 때
		} else if(pass_check.trim()==""){
			alert("비밀번호확인창을 입력해주세요");
			$("input[name=password_check]").focus();
			return false;
		//비밀번호확인창에서 빈칸이 들어갔을 때
		} else if(pass_check.search(/\s/) != -1){
			$("input[name=password_check]").focus();
			return false;
		//비밀번호확인창이 공백일 때(회원가입처음 들어갔을 때 가입방지용)
		} else if(pass_check == null){
			alert("비밀번호확인창을 입력해주세요");
			$("input[name=password_check]").focus();
			return false;
		}

		//이름 유효성체크
		var name=$("input[name=name]").val();
		//공백포함 빈칸일 때
		if(name.trim()==""){
			alert("이름을 입력해주세요");
			$("input[name=name]").focus();
			return false;
		//이름 처음부분에 빈칸 방지
		} else if(name.search(/^\s/) != -1){
			$("input[name=name]").focus();
			return false;
		//이름칸이 공백일 때(회원가입처음 들어갔을 때 가입방지용)
		} else if(name == null){
			alert("이름을 입력해주세요");
			$("input[name=name]").focus();
			return false;
		}
		
		var updateData=$("form").eq(0).serialize();
		$.ajax({
			url:"/member/update",
			type:"post",
			data:updateData,
			success:function(data){
				alert("수정완료!!");
				location.href="/member/mypage";
			}
		})
		return false;
	});	
});