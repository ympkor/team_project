$(function(){
	$("button[name=pwCk]").on("click", function(){
		var id = $("input[name=userId]").eq(0).serialize();
		$.ajax({
			url:"/member/searchPw",
			type:"post",
			data:id,
			success:function(data){
				if(data=="없는 아이디"){
					document.getElementById("message").innerHTML="해당하는 아이디의 정보가 없습니다.";
					document.getElementById("message").innerHTML+="<br><a href='/member/searchId'>아이디찾기</a>";
				} else {
					document.getElementById("message").innerHTML=data;
					document.getElementById("message").innerHTML+="<br><a href='/member/login'>로그인하러가기</a>";
				}
			}
		})
	});
});