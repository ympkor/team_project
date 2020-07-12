$(function() {
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypageProc";
	}
	document.querySelector(".gologout").onclick = function(){
		location.href="/member/logout";
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
 
});
function showContent(el){
	var boardId = $(el).attr('class');		
	location.href="/board/contentOneShow?boardId="+boardId+"&pNum="+pNum;
}
