<%@ page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산</title>
<link rel="stylesheet" type="text/css" href="/css/myAsset.css">
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script type="text/javascript">

window.onload = function() {
	
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypage";
	}
	document.querySelector(".gologout").onclick = function(){
		location.href="/member/logout";
	}

	let newsList = ${newsArr};
	
	document.getElementById('news0.title').innerHTML=newsList[0].title;
	document.getElementById('news0.desc').innerHTML=newsList[0].description;
	document.getElementById('news0.date').innerHTML=newsList[0].pubDate;
	var newslink0 = String(newsList[0].link);
	document.getElementById('newsBox0').href=newslink0;

	
	document.getElementById('news1.title').innerHTML=newsList[1].title;
	document.getElementById('news1.desc').innerHTML=newsList[1].description;
	document.getElementById('news1.date').innerHTML=newsList[1].pubDate;
	var newslink1 = String(newsList[1].link);
	document.getElementById('newsBox1').href=newslink1;
	
	document.getElementById('news2.title').innerHTML=newsList[2].title;
	document.getElementById('news2.desc').innerHTML=newsList[2].description;
	document.getElementById('news2.date').innerHTML=newsList[2].pubDate;
	var newslink2 = String(newsList[2].link);
	document.getElementById('newsBox2').href=newslink2;
	
	document.getElementById('news3.title').innerHTML=newsList[3].title;
	document.getElementById('news3.desc').innerHTML=newsList[3].description;
	document.getElementById('news3.date').innerHTML=newsList[3].pubDate;
	var newslink3 = String(newsList[3].link);
	document.getElementById('newsBox3').href=newslink3;
	
	document.getElementById('news4.title').innerHTML=newsList[4].title;
	document.getElementById('news4.desc').innerHTML=newsList[4].description;
	document.getElementById('news4.date').innerHTML=newsList[4].pubDate;
	var newslink4 = String(newsList[4].link);
	document.getElementById('newsBox4').href=newslink4;
	
	document.getElementById('news5.title').innerHTML=newsList[5].title;
	document.getElementById('news5.desc').innerHTML=newsList[5].description;
	document.getElementById('news5.date').innerHTML=newsList[5].pubDate;
	var newslink5 = String(newsList[5].link);
	document.getElementById('newsBox5').href=newslink5;
	
	document.getElementById('news6.title').innerHTML=newsList[6].title;
	document.getElementById('news6.desc').innerHTML=newsList[6].description;
	document.getElementById('news6.date').innerHTML=newsList[6].pubDate;
	var newslink6 = String(newsList[6].link);
	document.getElementById('newsBox6').href=newslink6;
	
	document.getElementById('news7.title').innerHTML=newsList[7].title;
	document.getElementById('news7.desc').innerHTML=newsList[7].description;
	document.getElementById('news7.date').innerHTML=newsList[7].pubDate;
	var newslink7 = String(newsList[7].link);
	document.getElementById('newsBox7').href=newslink7;
	
	document.getElementById('news8.title').innerHTML=newsList[8].title;
	document.getElementById('news8.desc').innerHTML=newsList[8].description;
	document.getElementById('news8.date').innerHTML=newsList[8].pubDate;
	var newslink8 = String(newsList[8].link);
	document.getElementById('newsBox8').href=newslink8;
	
	document.getElementById('news9.title').innerHTML=newsList[9].title;
	document.getElementById('news9.desc').innerHTML=newsList[9].description;
	document.getElementById('news9.date').innerHTML=newsList[9].pubDate;
	var newslink9 = String(newsList[9].link);
	document.getElementById('newsBox9').href=newslink9;
	
	document.getElementById('news10.title').innerHTML=newsList[10].title;
	document.getElementById('news10.desc').innerHTML=newsList[10].description;
	document.getElementById('news10.date').innerHTML=newsList[10].pubDate;
	var newslink10 = String(newsList[10].link);
	document.getElementById('newsBox10').href=newslink10;
	
	document.getElementById('news11.title').innerHTML=newsList[11].title;
	document.getElementById('news11.desc').innerHTML=newsList[11].description;
	document.getElementById('news11.date').innerHTML=newsList[11].pubDate;
	var newslink11 = String(newsList[11].link);
	document.getElementById('newsBox11').href=newslink11;
	
	let assetChtData = parseInt(document.getElementById('sumAsset').innerText);
	let debtChtData = parseInt(document.getElementById('sumDebt').innerText)*-1;
	let aRatio = (assetChtData/(assetChtData+debtChtData)*100).toFixed(1);
	let bRatio = (debtChtData/(assetChtData+debtChtData)*100).toFixed(1);
	
	var chart = new CanvasJS.Chart("chartContainer", {
	backgroundColor: "#353535",
	theme: "dark2", // "light1", "light2", "dark1", "dark2"
	exportEnabled: false,
	animationEnabled: true,
	data: [{
		type: "pie",
		startAngle: 270,
		toolTipContent: "<b>{label}</b>: {z}%",
		showInLegend: "true",
		legendText: "{label}",
		indexLabelFontSize: 15,
		indexLabel: "{label} {y}원 ({z}%)",
		dataPoints: [
			{ y: assetChtData, z: aRatio, label: "자산"},
			{ y: debtChtData, z: bRatio, label: "부채", exploded : true},
		]
	}]
});
	chart.render();
	
}

</script>
</head>

<body>



<header>
<div id=topmenu>
<div class="basic"><a id=mainlink href="/main/getCal">가계부</a></div>
<div class="statistics"><a id=staticlink href="/statistics/show">통계</a></div>
<div class="assest"><a id=assetlink href="/asset/view">자산</a></div>
<div class="board"><a id=boardlink href="/board/show">게시판</a></div>
<div class="memu"><button class="gomypage">마이페이지</button>
<button class="gologout">로그아웃</button></div>
</div>
</header>

<section>
  <sum>
  <div id="myTotalAsset">
		
		<h1 style="margin-top:30px;">내 자산 현황</h1>
		<h1>총 ${sumTotal}원</h1>
		<p class=leftSum>자산 합계</p><div id = sumAsset class=leftSum>${sumAsset} 원</div><br>
		<p class=leftSum>부채 합계</p><div id = sumDebt class=leftSum>${sumDebt} 원</div>
		<br><br>
	</div>
      <div id="chartContainer" style="height: 400px; width: 100%;"></div>
  </sum>

  <asset>
	
   <div id="eachAsset">
   		<h1 style="margin-top:30px; color:#ac3b61;">내 자산 내역</h1>
   		<p style="font-weight:bold; margin-bottom: 3px;">자산 ${cntAssets}건 / 부채 ${cntDebts}건<br><br>
		<c:forEach items="${aomList}" var="list">

			<div id="eachAssetText">
			
				${list.type}<br> ${list.assetsName}<br>
				${list.amount}원<br> ${list.memo}
			</div>
			

			<div id="eachAssetEdit">
				<a href="edit?memAssetId=${list.memAssetId}" style="text-decoration: none; color:black">수정 </a>
				<a href="delete?memAssetId=${list.memAssetId}"  style="text-decoration: none; color:black"
					onClick="return confirm('정말 삭제하시겠습니까?')">삭제</a>
			</div><br>
		</c:forEach>
	</div>
		<div id="addAsset">
	<div id="eachAssetText" onClick="location.href='add'" 
	style="cursor:pointer; text-align: center; opacity:0.5;">자산 / 부채 추가</div>
	</div>
  </asset>
  
  <news>
   <div>
	<h1 style="margin-top:30px; margin-left:10px;">추천 기사</h1>
	<p style="margin-left:10px;"><b>Tags > ${newsKeywords}</b>
	<a href="newsSettings" style="font-size: 12px; text-decoration:none">수정</a></p>
	
	<div style=font-size:12px;>
	<a id="newsBox0" href="" target="_blank" style="text-decoration:none">
		<div id="" class=newsBox style="text-decoration:none"> 
		<div id=news0.title class=newsTitle style="text-decoration:none"></div><br>
		<div id=news0.desc class=newsDesc></div><br>
		<div id=news0.date  class=newsDate></div>
	</div></a>
	
	<a id=newsBox1 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news1.title class=newsTitle></div><br>
	<div id=news1.desc class=newsDesc>></div><br>
	<div id=news1.date class=newsDate></div>
	</div></a>
	<br>
	
	<a id=newsBox2 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news2.title class=newsTitle></div><br>
	<div id=news2.desc class=newsDesc></div><br>
	<div id=news2.date class=newsDate></div>
	</div></a>
	<br>
	
	<a id=newsBox3 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news3.title class=newsTitle></div><br>
	<div id=news3.desc class=newsDesc></div><br>
	<div id=news3.date class=newsDate></div>
	</div></a>
	<br>
	
	<a id=newsBox4 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news4.title class=newsTitle></div><br>
	<div id=news4.desc class=newsDesc></div><br>
	<div id=news4.date class=newsDate></div>
	</div></a>
	<br>
	<a id=newsBox5 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news5.title class=newsTitle></div><br>
	<div id=news5.desc class=newsDesc></div><br>
	<div id=news5.date class=newsDate></div>
	</div></a>
	<br>
	<a id=newsBox6 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news6.title class=newsTitle></div><br>
	<div id=news6.desc class=newsDesc></div><br>
	<div id=news6.date class=newsDate></div>
	</div></a>
	<br>
	<a id=newsBox7 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news7.title class=newsTitle></div><br>
	<div id=news7.desc class=newsDesc></div><br>
	<div id=news7.date class=newsDate></div>
	</div></a>
	<br>
	<a id=newsBox8 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news8.title class=newsTitle></div><br>
	<div id=news8.desc class=newsDesc></div><br>
	<div id=news8.date class=newsDate></div>
	</div></a>
	<br>
	<a id=newsBox9 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news9.title class=newsTitle></div><br>
	<div id=news9.desc class=newsDesc></div><br>
	<div id=news9.date class=newsDate></div>
	</div></a>
	<br>
	<a id=newsBox10 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news10.title class=newsTitle></div><br>
	<div id=news10.desc class=newsDesc></div><br>
	<div id=news10.date class=newsDate></div>
	</div></a>
	<br>
	<a id=newsBox11 href="" target="_blank">
	<div id="" class=newsBox>
	<div id=news11.title class=newsTitle></div><br>
	<div id=news11.desc class=newsDesc></div><br>
	<div id=news11.date class=newsDate></div>
	</div></a>
	<br>
	</div>
</div>
  </news>
  
</section>

<footer>
  <p>Footer</p>
</footer>



</body>
</html>
