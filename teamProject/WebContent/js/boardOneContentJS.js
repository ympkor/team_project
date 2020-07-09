$(function(){
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypageProc";
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
 $(".commentwriteshow").click(function(){
	 $(".commentwriteinput").toggle();
	 $(".writeboardcontent").focus();
	 
 });
 $(".commentupdate").click(function(){
	 //console.dir(document.querySelector(".commentcontent"));
	 $(".commentUDbuttons").hide();
	 //console.log(this);
	 var commentId = $(this).attr('name');
	 //console.log($(this).parent().parent().children().eq(0));	 
	 var commentposition = $(this).parent().parent().children().eq(0);
	 var oricomment = commentposition.html();
	 //console.log("ok? "+commentposition.html());
	 commentposition.html( "<form class='commentupdateinput' action='/board/commentupdate' method='post'>"
		 +"<input type='hidden' name='commentId' value="+commentId+">"
		 +"<input type='hidden' name='boardId' value="+boardId+">"
		 +"<TEXTAREA class='writeboardcontent' name='comment' COLS=35 ROWS=3>"+oricomment+"</TEXTAREA>"
		 +"<br><input type='submit' value='댓글수정'></form>");
 }); 
 $(".commentdelete").click(function(){
	 var flag =confirm("댓글 진짜 삭제해요???!!!");
	 var commentId = $(this).attr('name');
	 if(flag){
		 location.href="/board/deletecomment?boardId="+boardId+"&commentId="+commentId;
	 }
 }); 
 
 $("input:checkbox[class='likecheck']").click(function(el){
	  var check =$(this)[0].checked;
	 console.log(check); 
	 if(check==true){
		 $.ajax({
		 url: "/board/likeupdate",
         type: "POST",
         data: { "boardId":boardId ,"userKey": userKey},
         success: function (d) {
        	// console.log("좋아요 수",d);
		      // console.dir($(".likesNum"));
		       $(".likesNum").text(d);
         },
		});		  
	 }else if(check==false){
		 $.ajax({
			 url: "/board/cancellikeupdate",
	         type: "POST",
	         data: { "boardId":boardId ,"userKey": userKey},
	         success: function (d) {
	        	// console.log("좋아요 수",d);
			      // console.dir($(".likesNum"));
			       $(".likesNum").text(d);
	         },
			});		
	 }
 }); 
console.log("좋아요 했는지",likecheck);
if(likecheck){
	$("input:checkbox[class='likecheck']")[0].checked=true;
}
 });