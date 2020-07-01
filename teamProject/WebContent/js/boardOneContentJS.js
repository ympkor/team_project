$(function(){
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypage";
	}
	document.querySelector(".gologout").onclick = function(){
		location.href="/member/logout";
	}
	//console.dir($(".commentwriteinput"));
$(".commentwriteinput").hide();
 $(".showboardList").click(function(){
	 location.href="/board/show";
 });
 $(".likes").click(function(){
	 
 });
 $(".contentwriteshow").click(function(){
	 $(".commentwriteinput").toggle();
 });
 });