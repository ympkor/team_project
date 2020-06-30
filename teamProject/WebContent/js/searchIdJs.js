
$(function(){
	$("button[name=idCk]").on("click", function(){
		var email = $("input[name=email]").eq(0).serialize();//입력받은 이메일을 키와 벨류로 나눔
		$.ajax({
			url:"/member/searchId",
			type:"post",
			data:email,
			success:function(data){
				console.log(data)
				if(data=="없는 이메일"){
					document.getElementById("message").innerHTML="해당하는 이메일의 정보가 없습니다.";
					document.getElementById("message").innerHTML+="<br><a href='/member/searchId'>아이디 다시 찾기</a>";
				} else {
					document.getElementById("message").innerHTML=data;
					document.getElementById("message").innerHTML+="<br><a href='/member/searchPw'>비밀번호찾기</a>";
				}
			}
		})
	});
});
