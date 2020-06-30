$(function(){
	$("button[name=delete]").on("click", function(){
		var result = confirm("탈퇴하시겠습니까?");
		if(result){
			alert("탈퇴되었습니다.\n다음에 또 만나요~");
			location.href='/member/delete'
		} else{
			alert("탈퇴가 취소되었습니다.");
			return false;
		}
	})
})