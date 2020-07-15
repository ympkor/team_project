$(function() {
	if(userKey!=null){
		document.querySelector(".gomypage").onclick = function(){
			location.href="/member/mypageProc";
		}
		document.querySelector(".gologout").onclick = function(){
			location.href="/member/logoutProc";
		}		
	}
	if(userKey==null){
		document.querySelector(".gologin").onclick = function(){
			location.href="/member/login";
		}		
	}
 document.querySelector("#writeBoard").onclick = function(){
	 location.href="/board/write";
 };
 $(".boardtitlehead").mouseover(function(){
	 $(this).children().css("background","#f1e0de");	 
 });
 $(".boardtitlehead").mouseout(function(){
	 $(this).children().css("background","#f5f5f5");
 });

 //정렬어떤방식일지 저장
 $(".sortselect").val(sNum);
 
 $(".sortselect").change(function(){
	 if($(this).val()==1){
		 location.href="/board/show?sortNum=1"
	 }else if($(this).val()==2){
		 location.href="/board/show?sortNum=2"
	 }else if($(this).val()==3){
		 location.href="/board/show?sortNum=3"
	 }else if($(this).val()==4){
		 location.href="/board/show?sortNum=4"
	 }
 })
 
});
function showContent(el){
	var boardId = $(el).attr('class');		
	location.href="/board/contentOneShow?boardId="+boardId+"&pNum="+pNum+"&sortNum="+sNum;
}
