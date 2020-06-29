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
