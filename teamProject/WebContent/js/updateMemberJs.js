$(function(){
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
	 		return false;
 		 } else if(pass.search(id) > -1){
 			document.getElementById("passtext").innerHTML="비밀번호에 아이디가 포함되었습니다.";
 			return false;
 		 } else {
 			document.getElementById("passtext").innerHTML="";
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
	
});

$(function(){
	$("form").submit(function(){
		//수정폼제출시 실행
		var updateData = $("form").eq(0).serialize();
		var name=$("input[name=name]").val().trim();
		var pass = $("input[name=password]").val().trim();
		var pass_check=$("input[name=password_check]").val().trim();
		$.ajax({
			url:"/member/update",
			type:"post",
			data:updateData,
			success:function(data){
				if(pass == ""){
					alert("비밀번호를 입력해주세요");
					return false;
				} else if(pass_check==""){
					alert("비밀번호확인을 입력해주세요");
					return false;
				} else if(name==""){
					alert("이름을 입력해주세요");
					return false;
				} else if(pass != pass_check){
					alert("비밀번호와 비밀번호 확인창이 일치하지 않습니다.");
					return false;	
				} else {
					alert("수정되었습니다.");
					location.href="/member/mypage";
				}
			}
		});
		return false;
	})
})