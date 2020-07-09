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
 $(".boardID").hide();
});
function showContent(el){
	var boardId = $(el).attr('class');	
	/*var presiblng = $(el).prev();
	var nextsiblng = $(el).next();
	console.dir(presiblng);
	console.dir(presiblng.attr('class'));
	console.dir(nextsiblng.attr('class'));*/	
	location.href="/board/contentOneShow?boardId="+boardId;
}
