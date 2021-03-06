//중복확인 버튼 활성화(유효성체크시 걸리면 버튼 비활성화)
var idck=false;
var emailck=false;
//유효성에서 걸려서 칸 아래뜨는 경고창과 alert창이 겹치지 않도록함
var check=true;
$(function(){
	//작성시 키보드눌렀다가 떼면 경고창이 뜸(아래있는 유효성과 연결됨)
	//아이디칸에 작성시 아래경고창
	$("input[name=userId]").on("keyup", function(){
		var id = $("input[name=userId]").val();
		var num = id.search(/[0-9]/g);
	 	var eng = id.search(/[a-z]/ig);
	 	//중복확인버튼이 안눌리도록 false
		if(id.trim()==""){
			//빈칸으로 남겼을 때 기본적인 문구
			document.getElementById("idtext").innerHTML="필수 정보입니다.";
			check = false;
		}else if(id.length < 5 || id.length > 20){
	 		document.getElementById("idtext").innerHTML="아이디는 5~20자리 이내로 입력해주세요.";
	 		check = false;
 		 }else if(id.search(/\s/) != -1){
 			document.getElementById("idtext").innerHTML="아이디는 공백 없이 입력해주세요.";
 			check = false;
 		 }else if(/(.)\1\1\1/.test(id)){
 			document.getElementById("idtext").innerHTML="같은 문자를 4번이상 사용하실 수 없습니다";
 			check = false;
 		 } else if(num < 0 || eng < 0){
 			document.getElementById("idtext").innerHTML="문자+숫자조합으로만 입력가능합니다";
 			check = false;
 			//유효성에서 걸리는게 없으니까 true
 		 } else {
			document.getElementById("idtext").innerHTML="";//제대로 작성시 아무런 문구가 뜨지 않도록 해줌
			check=true;
		 }
	});
	
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
	
	//이메일칸에 작성시 아래 경고창
	$("input[name=email]").on("keyup", function(){
		var email=$("input[name=email]").val();
		var emailRegExp = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+\.[a-zA-z]{2,3}$/;
		//중복확인버튼이 안눌리도록 false
		if(email.trim()==""){
			document.getElementById("emailtext").innerHTML="필수 정보입니다.";
			check = false;
		}else if(!emailRegExp.test(email)){
			document.getElementById("emailtext").innerHTML="이메일 형식이 잘못되었습니다.";
			check = false;
		}else if(email.search(/\s/) != -1){
			document.getElementById("emailtext").innerHTML="이메일은 공백 없이 입력해주세요.";
			check = false;
		//유효성에서 걸리는게 없으니까 true
		} else {
 			document.getElementById("emailtext").innerHTML="";
 			check = true;
 		}
	});	
	
	//아이디중복체크확인용
	$("button[name=equalsId]").on("click", function(){
		//아이디칸 아래있는 경고창에 아무것도 안떴을 때(유효성확인용)
		if(check){
			var equalId = $("input[name=userId]").eq(0).serialize();
			$.ajax({
				url:"/member/equalsId",
				type:"post",
				data:equalId,
				success:function(data){
					if(data=="중복되지 않음"){
						//중복되지 않으면 true
						idck=true;
						alert("사용가능한 아이디입니다.");
					}else if(data=="중복됨"){
						alert("중복된 아이디입니다.\n다른 아이디로 입력해주세요.");
					}
				}
			});
		}
		return false;
	});
		
	//이메일중복체크확인용	
	$("button[name=equalsEmail]").on("click", function(){
		//이메일칸 아래있는 경고창에 아무것도 안떴을 떼(유효성확인용)
		if(check){
			var equalEmail = $("input[name=email]").eq(0).serialize();
			$.ajax({
				url:"/member/equalsEmail",
				type:"post",
				data:equalEmail,
				success:function(data){
					if(data=="중복되지 않음"){
						//중복되지 않으면 true
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
	
	//회원가입버튼을 눌렀을 때 유효성검사
	$("form").submit(function(){
		//아이디 유효성체크(5~20자리, 공백없음, 같은문자 반복안됨)
		var id = $("input[name=userId]").val();
		//입력받은 아이디에 정규식에 해당되는 값들이 있는지 검사함
		var numId = id.search(/[0-9]/g);
		var engId = id.search(/[a-z]/ig);
		//아이디가 5자미만 또는 20초과일 때
		if(id.length < 5 || id.length > 20){
			$("input[name=userId]").focus();
			return false;
		//id입력시 공백이 들어갔을 때
		}else if(id.search(/\s/) != -1){
			$("input[name=userId]").focus();
			return false;
		//id에 들어가는 값이 똑같은 문자로 연속 4개이상 들어갈 때
		}else if(/(\w)\1\1\1/.test(id)){
			$("input[name=userId]").focus();
			return false;
		//띄어쓰기 포함 공백일 때
		}else if(id.trim() == ""){
			alert("아이디를 입력해주세요");
			$("input[name=userId]").focus();
			return false;
		//숫자와 문자 조합이 안되어있을 때
		} else if(engId < 0 || numId < 0){
			$("input[name=userId]").focus();
			return false;
		//아이디창이 공백일 때 (회원가입처음 들어갔을 때 가입방지용)
		} else if(id == null){
			alert("아이디를 입력해주세요");
			$("input[name=userId]").focus();
			return false;
		}
		
		//비밀번호 유효성체크(8~16자리, 공백없음, 같은문자 반복안됨, 문자+숫자 또는 문자+특수문자 조합으로만 가능, 아이디와 동일하면 안됨)
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
		
		//이메일 유효성체크(이메일형식으로 체크)
		var email=$("input[name=email]").val();
		//이메일 값에 비교할 정규식
		var emailRegExp = /^[0-9a-zA-Z]+@[0-9a-zA-Z]+\.[a-zA-z]{2,3}$/;
		//입력된 이메일 값에 정규식을 비교함
		if(!emailRegExp.test(email)){
			$("input[name=email]").focus();
			return false;
		//공백포함 빈칸일때
		} else if(email.trim() == ""){
			alert("이메일을 입력해주세요");
			return false;
		//이메일작성시 빈칸일 때
		} else if(email.search(/\s/) != -1){
			$("input[name=email]").focus();
			return false;
		//이메일 칸이 공백일 때(회원가입처음 들어갔을 때 가입방지용)
		} else if(name == null){
			alert("이메일을 입력해주세요");
			$("input[name=email]").focus();
			return false;
		}
		//서브밋유효성 마지막 줄입니다.
		
		//유효성검사후 아이디와 이메일중복체크시 중복이 아니면 DB에 값을 넣음
		if(idck==true&&emailck==true){
			var joinData = $("form").eq(0).serialize();
			$.ajax({
				url:"/member/asset/view",
				type:"post",
				data:joinData,
				success:function(data){
					//회원가입이 완료되면 처음에 작성해야되는 자산추가페이지로 이동한다고 알려줌	
					alert("회원가입이 완료되었습니다.\n자산추가페이지로 이동합니다.");
					location.href="/asset/view";
				},
				error:function(data){
					alert("회원가입에 오류가 났습니다.\n중복확인버튼을 한번더 확인해주세요");
					return false;
				}
			});
			return false;
		} else {
			if(idck==false){
				alert("아이디중복확인버튼을 눌러주세요");
			} else if (emailck==false){
				alert("이메일중복확인버튼을 눌러주세요");
			}
			return false;
		}
	});
});