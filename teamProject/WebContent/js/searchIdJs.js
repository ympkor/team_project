$(function(){
	$("form").on("submit", function(){
		var email = $("input[name=email]").val();//입력받은 이메일을 키와 벨류로 나눔
		var name = $("input[name=name]").val();
		
		$.ajax({
			url:"/member/searchId",
			type:"post",
			data:{"email":email, "name":name},
			success:function(data){
				if(data=="없는 이메일"){
					document.getElementById("message").innerHTML="<hr>";
					document.getElementById("message").innerHTML+="해당하는 이메일의 정보가 없습니다.";
					document.getElementById("message").innerHTML+="<br><a class='link' href='/member/searchId'>아이디 다시 찾기</a>";
				} else {
					document.getElementById("message").innerHTML="<hr>";
					document.getElementById("message").innerHTML+=$("#name_input").val()+"님의 아이디는 ";
					document.getElementById("message").innerHTML+="<span id='data'>"+data+"</span>";
					document.getElementById("message").innerHTML+="입니다";
					document.getElementById("message").innerHTML+="<br><a class='link' href='/member/searchPw'>비밀번호찾기</a>";
					document.getElementById("message").innerHTML+="<a class='link2' href='/member/login'>로그인하러가기</a>";
				}
			}
		});
		return false;
	});
});
