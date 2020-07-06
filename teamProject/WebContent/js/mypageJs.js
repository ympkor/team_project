$(function(){
	$("button[name=delete]").on("click", function(){
		var flag = confirm("탈퇴하시겠습니까?");
		if(flag){
			location.href='/member/delete'		
		}
	});
});