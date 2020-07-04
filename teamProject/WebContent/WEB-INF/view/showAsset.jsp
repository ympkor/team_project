<%@ page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산</title>
<link rel="stylesheet" type="text/css" href="/css/myAsset.css">
<link href="../../css/myAsset.css" rel="stylesheet" type="text/css">
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
	document.getElementById('news0.link').innerHTML=newsList[0].link;
	
	document.getElementById('news1.title').innerHTML=newsList[1].title;
	document.getElementById('news1.desc').innerHTML=newsList[1].description;
	document.getElementById('news1.date').innerHTML=newsList[1].pubDate;
	document.getElementById('news1.link').innerHTML=newsList[1].link;
	
	document.getElementById('news2.title').innerHTML=newsList[2].title;
	document.getElementById('news2.desc').innerHTML=newsList[2].description;
	document.getElementById('news2.date').innerHTML=newsList[2].pubDate;
	document.getElementById('news2.link').innerHTML=newsList[2].link;
	
	document.getElementById('news3.title').innerHTML=newsList[3].title;
	document.getElementById('news3.desc').innerHTML=newsList[3].description;
	document.getElementById('news3.date').innerHTML=newsList[3].pubDate;
	document.getElementById('news3.link').innerHTML=newsList[3].link;
	
	document.getElementById('news4.title').innerHTML=newsList[4].title;
	document.getElementById('news4.desc').innerHTML=newsList[4].description;
	document.getElementById('news4.date').innerHTML=newsList[4].pubDate;
	document.getElementById('news4.link').innerHTML=newsList[4].link;
	
	document.getElementById('news5.title').innerHTML=newsList[5].title;
	document.getElementById('news5.desc').innerHTML=newsList[5].description;
	document.getElementById('news5.date').innerHTML=newsList[5].pubDate;
	document.getElementById('news5.link').innerHTML=newsList[5].link;
	
	document.getElementById('news6.title').innerHTML=newsList[6].title;
	document.getElementById('news6.desc').innerHTML=newsList[6].description;
	document.getElementById('news6.date').innerHTML=newsList[6].pubDate;
	document.getElementById('news6.link').innerHTML=newsList[6].link;
	
	document.getElementById('news7.title').innerHTML=newsList[7].title;
	document.getElementById('news7.desc').innerHTML=newsList[7].description;
	document.getElementById('news7.date').innerHTML=newsList[7].pubDate;
	document.getElementById('news7.link').innerHTML=newsList[7].link;
	
	document.getElementById('news8.title').innerHTML=newsList[8].title;
	document.getElementById('news8.desc').innerHTML=newsList[8].description;
	document.getElementById('news8.date').innerHTML=newsList[8].pubDate;
	document.getElementById('news8.link').innerHTML=newsList[8].link;
	
	document.getElementById('news9.title').innerHTML=newsList[9].title;
	document.getElementById('news9.desc').innerHTML=newsList[9].description;
	document.getElementById('news9.date').innerHTML=newsList[9].pubDate;
	document.getElementById('news9.link').innerHTML=newsList[9].link;
	
	document.getElementById('news10.title').innerHTML=newsList[10].title;
	document.getElementById('news10.desc').innerHTML=newsList[10].description;
	document.getElementById('news10.date').innerHTML=newsList[10].pubDate;
	document.getElementById('news10.link').innerHTML=newsList[10].link;
	
	document.getElementById('news11.title').innerHTML=newsList[11].title;
	document.getElementById('news11.desc').innerHTML=newsList[11].description;
	document.getElementById('news11.date').innerHTML=newsList[11].pubDate;
	document.getElementById('news11.link').innerHTML=newsList[11].link;
	
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
		indexLabelFontSize: 16,
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
		<h1>내 자산 현황</h1>
		<h1>총 ${sumTotal}원</h1>
		자산 합 : <div id = sumAsset>${sumAsset}</div><br>
		부채 합 : <div id = sumDebt>${sumDebt}</div>
		<br><br><br>
	</div>
      <div id="chartContainer" style="height: 300px; width: 100%;"></div>
  </sum>
  
  <asset>
	<div id="addAsset">
	<input type="button" name="addAsset" value="자산 / 부채 추가"
		onClick="location.href='add'">
	</div>
	<br>
   <div id="eachAsset">
		<c:forEach items="${aomList}" var="list">

			<div id="eachAssetText">
				${list.name} 님의 ${list.type}<br> ${list.assetsName}<br>
				${list.amount}원<br> ${list.memo} <br> ${list.memAssetId }
			</div>

			<div id="eachAssetEdit">
				<a href="edit?memAssetId=${list.memAssetId}">수정</a>
				<a href="delete?memAssetId=${list.memAssetId}"
					onClick="return confirm('정말 삭제하시겠습니까?')">삭제</a>
			</div><br><br>
		</c:forEach>
	</div>
		
  </asset>
  
  <news>
   <div>
	<h1>자산 관련 뉴스</h1>
	Tags ${newsKeywords}<br><a href="newsSettings">수정</a>
	<br><br>
	<div style=font-size:12px;>

	<div id=newsBox>
	<div id=news0.title></div>
	<div id=news0.desc></div>
	<div id=news0.date></div>
	<div id=news0.link></div>
	</div>
	
	<div id=newsBox>
	<div id=news1.title></div>
	<div id=news1.desc></div>
	<div id=news1.date></div>
	<div id=news1.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news2.title></div>
	<div id=news2.desc></div>
	<div id=news2.date></div>
	<div id=news2.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news3.title></div>
	<div id=news3.desc></div>
	<div id=news3.date></div>
	<div id=news3.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news4.title></div>
	<div id=news4.desc></div>
	<div id=news4.date></div>
	<div id=news4.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news5.title></div>
	<div id=news5.desc></div>
	<div id=news5.date></div>
	<div id=news5.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news6.title></div>
	<div id=news6.desc></div>
	<div id=news6.date></div>
	<div id=news6.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news7.title></div>
	<div id=news7.desc></div>
	<div id=news7.date></div>
	<div id=news7.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news8.title></div>
	<div id=news8.desc></div>
	<div id=news8.date></div>
	<div id=news8.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news9.title></div>
	<div id=news9.desc></div>
	<div id=news9.date></div>
	<div id=news9.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news10.title></div>
	<div id=news10.desc></div>
	<div id=news10.date></div>
	<div id=news10.link></div><br>
	</div>
	<br>
	<div id=newsBox>
	<div id=news11.title></div>
	<div id=news11.desc></div>
	<div id=news11.date></div>
	<div id=news11.link></div><br>
	</div>
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
