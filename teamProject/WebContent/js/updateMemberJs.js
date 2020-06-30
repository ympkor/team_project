$(function(){
	$("form").submit(function(){
		//바뀐 비밀번호 유효성체크(8~16자리, 공백없음, 같은문자 반복안됨, 문자+숫자 또는 문자+특수문자 조합으로만 가능, 아이디와 동일하면 안됨)
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
		
	 	//바뀐 비밀번호확인 유효성체크
		var pass_check=$("input[name=password_check]").val().trim();
		if(pass_check != pass){
			alert("비밀번호와 다릅니다.");
			$("input[name=password_check]").focus();
			return false;
		}
		
		//바뀐 이름 유효성체크
		var name=$("input[name=name]").val().trim();
		if(name == ""){
			alert("이름을 입력하세요.");
			$("input[name=name]").focus();
			return false;
		}
	});
});

$(function(){
	$("form").submit(function(){
		//수정폼제출시 실행
		var updateData = $("form").eq(0).serialize();
		$.ajax({
			url:"/member/update",
			type:"post",
			data:updateData,
			success:function(data){
				alert("수정되었습니다.");
			}
		});
		return false;
	})
})