$(function(){
	$("form").submit(function(){
		var pw = $("form").eq(0).serialize();
		$.ajax({
			url:"/member/mypageProc",
			type:"post",
			data:pw,
			success:function(data){
				if(data=="비밀번호틀림"){
					alert("비밀번호가 일치하지 않습니다");
					return false;
				} else {
					location.href="/member/mypage"
				}
			}
		})
	});
});