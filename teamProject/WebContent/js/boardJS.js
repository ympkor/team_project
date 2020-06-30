$(function() {
 document.querySelector("#writeBoard").onclick = function(){
	 location.href="/board/write";
 };
 $(".boardID").hide();
});
function showContent(el){
//		$(".boardcontent").hide();
//		var tbody= $(el).parent();
//		$(el).next().toggle();
//		//console.dir($(el).next());
//		//console.dir(tbody.children());
	//console.dir($(el).attr('class'));
	//console.log($(el).children().eq(2).innerText);
	var boardId = $(el).attr('class');
	//console.log(boardId);
	location.href="/board/contentOneShow?boardId="+boardId;
}
