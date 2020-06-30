$(function(){
	console.dir($(".commentwriteinput"));
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