$(function(){
	$("button[name=delete]").on("click", function(){
		var flag = confirm("진짜 탈퇴할거야? 다시 선택할 기회준다");
		if(flag){
			location.href='/member/delete'		
		}
	});
});