var commentmoreclick=0
function commentclick() {
	commentmoreclick++;
	$(".commentshow").slice(10*commentmoreclick,10*(commentmoreclick+1)).show();	
	//console.log($(".commentshow").length/(10*(commentmoreclick+1)));
	if(($(".commentshow").length/(10*(commentmoreclick+1)))<= 1){		
		$(".commentmorebutton").hide();
	};
}

$(function(){
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypageProc";
	}
	document.querySelector(".gologout").onclick = function(){
		location.href="/member/logout";
	}
	
$(".commentwriteinput").hide();
$(".commentshow").hide();
$(".commentshow").slice(0,10).show();

if($(".commentshow").length/10> 1){	
	$(".commentListshow").append("<button class='commentmorebutton' onclick='commentclick()'>더보기</button>");
}


$(".writeboardcontent").keyup(function(){		
	if($(this).val().length>=200){
		alert("댓글은 200자를 초과해서 쓸수 없습니다.");
	}
});

 $(".showboardList").click(function(){
	 location.href="/board/show?pNum="+pNum;
 });

 $(".commentwriteshow").click(function(){	 
	 $(".commentwriteinput").toggle();
	 $(".writeboardcontent").focus();
		//console.log( $(this).attr('class'));
		$(this).toggleClass("commentwriteshow");
 });
 $(".commentupdate").click(function(){
	 $(".commentUDbuttons").hide();
	 var commentId = $(this).attr('name');
	 var commentposition = $(this).parent().parent().children().eq(2);
	 var oricomment = commentposition.children().eq(0).html();
	 commentposition.html( "<form class='commentupdateinput' action='/board/commentupdate' method='post'>"
		 +"<input type='hidden' name='pNum' value="+pNum+">"
		 +"<input type='hidden' name='commentId' value="+commentId+">"
		 +"<input type='hidden' name='boardId' value="+boardId+">"
		 +"<TEXTAREA class='writeboardcontent' name='comment' COLS=35 ROWS=3>"+oricomment+"</TEXTAREA>"
		 +"<br><input class='updatecommentinput' type='submit' value='댓글수정'></form>");
 }); 
 $(".commentdelete").click(function(){
	 var flag =confirm("댓글 삭제하시겠습니까?");
	 var commentId = $(this).attr('name');
	 if(flag){
		 location.href="/board/deletecomment?boardId="+boardId+"&commentId="+commentId+"&pNum="+pNum;
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
//console.log("좋아요 했는지",likecheck);
if(likecheck){
	$("input:checkbox[class='likecheck']")[0].checked=true;
}
//수정버튼 누르면 수정페이지로 이동
$(".boardupdate").click(function(){
	location.href="/board/update?boardId="+boardId+"&pNum="+pNum;	
});
//삭제 버튼 누르면 재확인 버튼확인, 삭제결과 확인후 목록페이지로 이동
$(".boarddelete").click(function(){
	var flag=confirm("게시물을 삭제하시겠습니까?\n댓글도 함께 삭제됩니다");
	if(flag){
		$.ajax({
			 url: "/board/delboard",
	         type: "POST",
	         data: { "boardId":boardId},
	         success: function (d) {
	        	 if(d=="delsuccess"){
	        		 alert("성공적으로 삭제되었습니다\n목록으로 돌아갑니다");			    
	        		 location.href="/board/show";		        		 
	        	 }else{
	        		 alert("삭제가 실패하였습니다\n목록으로 돌아갑니다");
	        		 location.href="/board/show";	
	        	 }
	         },
			});		  
	}
});

 });