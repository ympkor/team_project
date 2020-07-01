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
					document.location.href="/main/getCal";
				} else if(data=="패스워드 틀림"){
					document.getElementById("error").innerHTML="잘못된 비밀번호입니다.";
					$("input[name=password]").focus();
				} else if(data=="아이디틀림") {
					document.getElementById("error").innerHTML="없는 아이디이거나, 아이디가 틀렸습니다.";
					$("input[name=userId]").focus();
				} else {
					document.getElementById("error").innerHTML="";
				}
			}
		});
		return false;
	});
});

$(function(){
	$(".login i").on("click", function(){
		$("input[name=password]").toggleClass("active");
		if($("input[name=password]").hasClass("active")){
			$(this).attr("class", "far fa-eye-slash")
			$("input[name=password]").attr("type", "text");
		} else {
			$(this).attr("class", "far fa-eye")
			$("input[name=password]").attr("type", "password");
		}
	})
})
