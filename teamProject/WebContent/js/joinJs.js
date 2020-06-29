$(function(){
	//회원가입버튼을 눌렀을 때
	$("form").submit(function(){
		//아이디 유효성체크(5~20자리, 공백없음, 같은문자 반복안됨)
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
		
	 	//비밀번호 유효성체크(8~16자리, 공백없음, 같은문자 반복안됨, 문자+숫자 또는 문자+특수문자 조합으로만 가능, 아이디와 동일하면 안됨)
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
 		 } else if((num < 0 || eng < 0) && (eng < 0 || spe < 0)){
 			 alert("문자+숫자 또는 문자+특수문자로만 입력해주세요.");
 			$("input[name=password]").focus();
	 		return false;
 		 } else if(pass.search(id) > -1){
 			 alert("비밀번호에 아이디가 포함되었습니다.");
 			  return false;
 		 }
		
	 	//비밀번호확인 유효성체크
		var pass_check=$("input[name=password_check]").val().trim();
		if(pass_check != pass){
			alert("비밀번호와 다릅니다.");
			$("input[name=password_check]").focus();
			return false;
		}
		
		//이름 유효성체크
		var name=$("input[name=name]").val().trim();
		if(name == ""){
			alert("이름을 입력하세요.");
			$("input[name=name]").focus();
			return false;
		}
		
		//이메일 유효성체크(이메일형식으로 체크)
		var email=$("input[name=email]").val().trim();
		var emailRegExp = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+\.[a-zA-z]{2,3}$/;
		if(!emailRegExp.test(email)){
			alert("이메일 형식이 잘못되었습니다.");
			$("input[name=email]").focus();
			return false;
		}
		
		//아이디가 중복되는지 확인해줌(아이디가 중복이면 계속 idck의 값이 0으로 되어있어서 알람이 뜸)
		if(idck == 0){
			 alert("아이디 중복확인버튼을 눌러주세요");
			 return false;
		 }
		
		if(emailck == 0){
			alert("이메일 중복확인버튼을 눌러주세요");
			return false;
		}
	//회원가입이 완료되면 처음에 작성해야되는 자산추가페이지로 이동한다고 알려줌	
	alert("회원가입완료!!\n자산추가페이지로 이동합니다.");
	});
});
var idck=0;	//초기화가 0임
$(function(){
	//아이디 중복체크 버튼을 눌렀을 때
	$("button[name=equalsId]").on("click", function(){
		var equalId = $("input[name=userId]").eq(0).serialize();
		//console.log(equalId);
		$.ajax({
	 		url:"/member/equalsId",
	 		type:"post",
	 		data:equalId,
	 		success:function(data){
	 			console.log(data);
	 			if(data=="중복되지 않음"){
	 				//사용가능한 아이디일 때 idck를 1로 변경
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
var emailck=0;
$(function(){
	//이메일 중복체크 버튼을 눌렀을 때
	$("button[name=equalsEmail]").on("click", function(){
		var equalEmail = $("input[name=email]").eq(0).serialize();
		$.ajax({
			url:"/member/equalsEmail",
			type:"post",
			data:equalEmail,
			success:function(data){
				if(data=="중복되지 않음"){
					emailck=1;
					alert("사용가능한 이메일입니다.");
				} else {
					alert("중복된 이메일입니다.");
				}
			}
		});
		return false;
	});
});
