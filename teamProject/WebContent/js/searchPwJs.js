$(function(){
	$("form").on("submit", function(){
		var id = $("input[name=userId]").val();
		var name = $("input[name=name]").val();
		var email = $("input[name=email]").val();
//		console.log(id);
//		console.log(name);
//		console.log(email);
		$.ajax({
			url:"/member/searchPw",
			type:"post",
			data:{"userId":id, "name":name, "email":email},
			success:function(data){
				if(data=="없는 아이디"){
					document.getElementById("message").innerHTML="<hr>";
					document.getElementById("message").innerHTML+="해당하는 아이디의 정보가 없습니다.";
					document.getElementById("message").innerHTML+="<br><a class='link' href='/member/searchId'>아이디찾기</a>";
				} else {
					document.getElementById("message").innerHTML="<hr>";
					document.getElementById("message").innerHTML+=$("#name_input").val()+"님의 비밀번호는 ";
					document.getElementById("message").innerHTML+="<span id='data'>"+data+"</span>";
					document.getElementById("message").innerHTML+="입니다";
					document.getElementById("message").innerHTML+="<br><a class='link' href='/member/login'>로그인하러가기</a>";
				}
			}
		});
		return false;
	});
});