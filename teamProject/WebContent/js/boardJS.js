$(function() {
 document.querySelector("#writeBoard").onclick = function(){
	 location.href="/board/write";
 };
 
 
});
function showContent(){
	 console.dir(document.querySelector("tbody").children); 
	 console.dir(this); 
	 /*= "<tr><td>"+name_text+"</td><td>"+age_text+"</td></tr>";*/
}