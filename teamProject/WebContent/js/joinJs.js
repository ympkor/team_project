//중복확인 버튼 활성화(유효성체크시 걸리면 버튼 비활성화)
var check = false;
$(function(){
	//아이디칸에 포커스가 나왔을 때
	$("input[name=userId]").on("focusout", function(){
		var id = $("input[name=userId]").val().trim();
		var num = id.search(/[0-9]/g);
	 	var eng = id.search(/[a-z]/ig);
		//아이디 유효성체크(5~20자리, 공백없음, 같은문자 반복안됨)
		if(id==""){
			//빈칸으로 남겼을 때 기본적인 문구
			document.getElementById("idtext").innerHTML="필수 정보입니다.";
			check = false;
			console.log(""+check);
	 		return false;
		}else if(id.length < 5 || id.length > 20){
	 		document.getElementById("idtext").innerHTML="아이디는 5~20자리 이내로 입력해주세요.";
	 		check = false;
			console.log(""+check);
	 		return false;
 		 }else if(id.search(/\s/) != -1){
 			document.getElementById("idtext").innerHTML="아이디는 공백 없이 입력해주세요.";
 			check = false;
			console.log(""+check);
	 		return false;
 		 }else if(/(.)\1\1\1/.test(id)){
 			document.getElementById("idtext").innerHTML="같은 문자를 4번이상 사용하실 수 없습니다";
 			check = false;
			console.log(""+check);
	 		return false;
 		 } else if(num < 0 || eng < 0){
 			 document.getElementById("idtext").innerHTML="문자+숫자조합으로만 입력가능합니다";
 			check = false;
			console.log(""+check);
	 		return false;
 		 } else {
			document.getElementById("idtext").innerHTML="";//제대로 작성시 아무런 문구가 뜨지 않도록 해줌
			check=true;
			console.log(""+check);
			return false;
		 }
	});
	
	//비밀번호칸에 포커스가 나왔을 때
	$("input[name=password]").on("focusout", function(){
		var pass = $("input[name=password]").val().trim();
		var id = $("input[name=userId]").val().trim();
 		var num = pass.search(/[0-9]/g);
	 	var eng = pass.search(/[a-z]/ig);
	 	var spe = pass.search(/[~!@#$%^&*()_]/gi);
	 	//비밀번호 유효성체크(8~16자리, 공백없음, 같은문자 반복안됨, 문자+숫자 또는 문자+특수문자 조합으로만 가능, 아이디와 동일하면 안됨)
 		if(pass==""){
			document.getElementById("passtext").innerHTML="필수 정보입니다.";
	 		return false;
		}else if(pass.length < 8 || pass.length > 16){
			document.getElementById("passtext").innerHTML="비밀번호는 8~16자리 이내로 입력해주세요.";
			return false;
	 	} else if(pass.search(/\s/) != -1){
	 		document.getElementById("passtext").innerHTML="비밀번호는 공백 없이 입력해주세요.";
	 		return false;
	 	}else if(/(\w)\1\1\1/.test(pass)){
	 		document.getElementById("passtext").innerHTML="같은 문자를 4번이상 사용하실 수 없습니다";
	 		return false;
 		 } else if((num < 0 || eng < 0) && (eng < 0 || spe < 0)){
 			document.getElementById("passtext").innerHTML="문자+숫자 또는 문자+특수문자로만 입력해주세요.";
 			document.getElementById("passtext").innerHTML+="<br>특수문자는 ~!@#$%^&*()_ 만 사용가능합니다.";
	 		return false;
 		 } else if(pass.search(id) > -1){
 			document.getElementById("passtext").innerHTML="비밀번호에 아이디가 포함되었습니다.";
 			return false;
 		 } else {
 			document.getElementById("passtext").innerHTML=""
 			return false;
 		 }
	});
	
	//비밀번호체크칸에 포커스가 나왔을 때
	$("input[name=password_check]").on("focusout", function(){
		var pass = $("input[name=password]").val().trim();
		var pass_check=$("input[name=password_check]").val().trim();
		if(pass_check==""){
			document.getElementById("passchtext").innerHTML="필수 정보입니다.";
	 		return false;
		}else if(pass_check){
			if(pass_check != pass){
				document.getElementById("passchtext").innerHTML="비밀번호가 일치하지 않습니다.";
				return false;
			} else {
	 			document.getElementById("passchtext").innerHTML="";
	 			return false;
	 		 }
		}
	});
	
	//이름칸에 포커스가 나왔을 때
	$("input[name=name]").on("focusout", function(){
		var name=$("input[name=name]").val().trim();
		if(name==""){
			document.getElementById("nametext").innerHTML="필수 정보입니다.";
			return false;
		} else {
 			document.getElementById("nametext").innerHTML="";
 			return false;
 		}
	})
	
	//이메일칸에 포커스가 나왔을 때
	$("input[name=email]").on("focusout", function(){
		var email=$("input[name=email]").val().trim();
		var emailRegExp = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+\.[a-zA-z]{2,3}$/;
		if(email==""){
			document.getElementById("emailtext").innerHTML="필수 정보입니다.";
			check = false;
			console.log(""+check);
	 		return false;
		}else if(!emailRegExp.test(email)){
			document.getElementById("emailtext").innerHTML="이메일 형식이 잘못되었습니다.";
			check = false;
			console.log(""+check);
			return false;
		} else {
 			document.getElementById("emailtext").innerHTML="";
 			check = true;
			console.log(""+check);
 			return false;
 		}
	});
});

$(function(){
	//회원가입버튼을 눌렀을 때
	$("form").submit(function(){
		var joinData = $("form").eq(0).serialize();
		$.ajax({
			url:"/member/asset/view",
			type:"post",
			data:joinData,
			success:function(data){
				var id = $("input[name=userId]").val().trim();
				var numId = id.search(/[0-9]/g);
				var engId = id.search(/[a-z]/ig);
				var pass = $("input[name=password]").val().trim();
				var numPass = pass.search(/[0-9]/g);
				var engPass = pass.search(/[a-z]/ig);
				var spePass = pass.search(/[~!@#$%^&*()_]/gi);
				var pass_check=$("input[name=password_check]").val().trim();
				var name=$("input[name=name]").val().trim();
				var email=$("input[name=email]").val().trim();
				var emailRegExp = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+\.[a-zA-z]{2,3}$/;
				//아이디 유효성체크(5~20자리, 공백없음, 같은문자 반복안됨)
				if(id.length < 5 || id.length > 20){
					$("input[name=userId]").focus();
					return false;
				}else if(id.search(/\s/) != -1){
					$("input[name=userId]").focus();
					return false;
				}else if(/(\w)\1\1\1/.test(id)){
					$("input[name=userId]").focus();
					return false;
				}else if(id == ""){
					alert("아이디를 입력해주세요");
					$("input[name=userId]").focus();
					return false;
				} else if(engId < 0 || numId < 0){
					$("input[name=userId]").focus();
					return false;
					//비밀번호 유효성체크(8~16자리, 공백없음, 같은문자 반복안됨, 문자+숫자 또는 문자+특수문자 조합으로만 가능, 아이디와 동일하면 안됨)
				} else if(pass.length < 8 || pass.length > 16){
					$("input[name=password]").focus();
					return false;
				} else if(pass.search(/\s/) != -1){
					$("input[name=password]").focus();
					return false;
				}else if(/(\w)\1\1\1/.test(pass)){
					$("input[name=password]").focus(); 
					return false;
				} else if((numPass < 0 || engPass < 0) && (engPass < 0 || spePass < 0)){
					$("input[name=password]").focus();
					return false;
				} else if(pass.search(id) > -1){
					$("input[name=password]").focus();
					return false;
				} else if(pass == ""){
					alert("비밀번호를 입력해주세요");
					$("input[name=password]").focus();
					return false;
					//비밀번호확인 유효성체크
				} else if(pass_check != pass){
					alert("비밀번호와 비밀번호 확인창이 일치하지 않습니다.");
					$("input[name=password_check]").focus();
					return false;
				} else if(pass_check==""){
					alert("비밀번호확인을 입력해주세요");
					$("input[name=password_check]").focus();
					return false;
					//이름 유효성체크
				} else if(name==""){
					alert("이름을 입력해주세요");
					$("input[name=name]").focus();
					return false;
					//이메일 유효성체크(이메일형식으로 체크)
				} else if(!emailRegExp.test(email)){
					alert("이메일 형식이 잘못되었습니다.");
					$("input[name=email]").focus();
					return false;
				} else if(email == ""){
					alert("이메일을 입력해주세요");
					return false;
				} else {
					//회원가입이 완료되면 처음에 작성해야되는 자산추가페이지로 이동한다고 알려줌	
					alert("회원가입완료!!\n자산추가페이지로 이동합니다.");
					location.href="/asset/view";
				}
			}
		});
		return false;
	});
});

////아이디가 중복되는지 확인해줌(아이디가 중복이면 계속 idck의 값이 0으로 되어있어서 알람이 뜸)
//if(idck == false){
//	alert("아이디 중복확인버튼을 눌러주세요");
//	return false;
//}
//
////이메일이 중복되는지 확인해줌(이메일이 중복이면 계속 emailck의 값이 0으로 되어있어서 알람이 뜸)
//if(emailck == false){
//	alert("이메일 중복확인버튼을 눌러주세요");
//	return false;
//}

//아이디 중복체크 버튼 누른 후 사용가능한 아이디에서 다시 바꿨을때 걸러주기 위함
var idck=false;
$(function(){
	//아이디 중복체크 버튼을 눌렀을 때
	$("button[name=equalsId]").on("click", function(){
		if(check){
			var equalId = $("input[name=userId]").eq(0).serialize();
			$.ajax({
				url:"/member/equalsId",
				type:"post",
				data:equalId,
				success:function(data){
					if(data=="중복되지 않음"){
						//사용가능한 아이디일 때 idck를 1로 변경
						idck=true;
						alert("사용가능한 아이디입니다.");
					}else if(data=="중복됨"){
						alert("중복된 아이디입니다.\n다른 아이디로 입력해주세요.");
						return false;
					}
				}
			});
		}
		return false;
	});
});

var emailck=false;
$(function(){
	//이메일 중복체크 버튼을 눌렀을 때
	$("button[name=equalsEmail]").on("click", function(){
		if(check){
			var equalEmail = $("input[name=email]").eq(0).serialize();
			$.ajax({
				url:"/member/equalsEmail",
				type:"post",
				data:equalEmail,
				success:function(data){
					if(data=="중복되지 않음"){
						emailck=true;
						alert("사용가능한 이메일입니다.");
					} else {
						alert("중복된 이메일입니다.\n다른 이메일로 입력해주세요.");
						return false;
					}
				}
			});
		}
		return false;
	});
});
