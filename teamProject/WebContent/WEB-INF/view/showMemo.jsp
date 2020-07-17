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
<link rel="stylesheet" type="text/css" href="/css/myAsset.css?ver=1">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="https://kit.fontawesome.com/b8612abdbb.js" crossorigin="anonymous"></script>
<script type="text/javascript">


window.onload = function() {
	
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypageProc";
	}
	document.querySelector(".gologout").onclick = function(){
		location.href="/member/logoutProc";
	}
	
	let assetChtData = ${assetRatioValue};
	let debtChtData = ${debtRatioValue}*(-1);
	let aRatio = (assetChtData/(assetChtData+debtChtData)*100).toFixed(1);
	let bRatio = (debtChtData/(assetChtData+debtChtData)*100).toFixed(1);
	if (aRatio > 0 && bRatio > 0){
	document.getElementById('assetRatioInfo').innerHTML="("+aRatio+"%)";
	document.getElementById('debtRatioInfo').innerHTML="("+bRatio+"%)";
	}
	
	let assetList = JSON.parse('${assetsListJ}');
	let dataPointsText = '[';
	for(let i = 0; i < assetList.length; i++) {
		let sumAmount = 0;
		let ratio = 0;
		for(let j = 0; j < assetList.length; j++) {
			if(assetList[j] != null && i != j) {
				sumAmount += assetList[j].amount;
			}
		}
		ratio = (((${sumTotal}-sumAmount)/${sumTotal})*100).toFixed(1);
		if(i+1 != assetList.length){dataPointsText += '{"y":"'+(${sumTotal}-sumAmount)+'", "z":"'+ratio+'", "label":"'+assetList[i].assetsName+' '+assetList[i].memo+'"},';}
		else{dataPointsText += '{"y":"'+(${sumTotal}-sumAmount) +'","z":"'+ratio+'","label":"'+assetList[i].assetsName+' '+assetList[i].memo+'"}';}
	}
	dataPointsText += ']';
	var chart = new CanvasJS.Chart("chartContainer", {
	backgroundColor: "#464646",
	theme: "dark1", // "light1", "light2", "dark1", "dark2"
	exportEnabled: false,
	animationEnabled: true,
	data: [{
		type: "pie",
		startAngle: 270,
		toolTipContent: "<b>{label}</b> {y}원 ({z}%)",
		showInLegend: "true",
		legendText: "{label}",
		indexLabelFontSize: 10,
		indexLabel: "{label} {z}%",
		dataPoints: JSON.parse(dataPointsText)
	}]
});
	chart.render();
	
}

</script>
</head>

<body>
	



<!-- 상단 메뉴 부분 -->
	<header class="topmenu">
		<div class="grid_header">
			<div class="basic"><a id=mainlink href="/main/getCal">MAIN</a></div>
			<div class="statistics"><a id=mainlink href="/statistics/show">GRAPH</a></div>
			<div class="assest"><a id=mainlink style="text-decoration: underline; text-shadow: 2px 1px 0 #f5f5f5;" 
				href="/asset/view">ASSETS</a></div>
			<div class="board"><a id=mainlink href="/board/show">BOARD</a></div>
			<div class="gomypage"><button class="gomypage" style="font-size: 16px;font-weight: 500;">MYPAGE</button></div>
			<div class="gologout"><button class="gologout" style="font-size: 16px;font-weight: 500;">LOGOUT</button></div>
		</div>
	</header>

<section>
  <sum>
  <div id="myTotalAsset">
		<h1 style="text-align:; margin-top:30px; margin-bottom: 50px;">내 자산</h1>
		<h1 style="font-size:36px; text-align: right; color:;">
		<fmt:formatNumber value="${sumTotal}" pattern="###,###,###,###"/>원</h1>
		<div style="margin-top: 50px; line-height: 30%; color:grey">
		<span class=leftSum>자산</span> (<span id="assetRatioInfo"></span>%)<div id = sumAsset class=leftSum>
		<h2 style="text-align: right"><fmt:formatNumber value="${sumAsset}" pattern="###,###,###,###"/>원</h2></div><br>
		<span class=leftSum>마이너스 계좌</span> (<span id="debtRatioInfo"></span>%)<div id = sumDebt class=leftSum>
		<h2 style="text-align: right"><fmt:formatNumber value="${sumDebt}" pattern="###,###,###,###"/>원</h2></div>
		</div><br><br>
	</div>
	
	
	<!-- 그래프 출력 -->
      <div id="chartContainer" style="height: 320px; width: 100%; margin-top:20px;"></div>
 
 
 
 	<!-- 검색창 부분 -->
 	<form action="showMemo" method="post">
 		<div style="text-align: center; margin-top:50px; margin-bottom:150px;">
 		<input style="text-align:center; width:80%;" type="text" name="memo" placeholder="수입/지출내역 메모를 검색합니다.">
 		<input type="submit" value="검색">
 		</div>
 	</form>
  
  
  
  
  </sum>

  <asset>
	
   <div id="eachAsset">
   		<h1 style="margin-top:27px; margin-bottom:30px; color:#grey;">내 자산 내역</h1>
   		<div style="text-align: right; font-weight: bold; margin-bottom:-11px;"><span class=cntAsset style="margin-right: 0px;">총 ${cntAssets} 건</span>
		</div><br><br>
		
		<c:forEach items="${assetList}" var="aom">
			<div id="eachAssetText" style="background:#6e92bc;">
				<div></div>
				<div>${aom.assetsName}</div>
				<div id="eachAssetAmount" class="eAmount" style="text-align: right; font-size:30px; font-weight: 900;">
				<fmt:formatNumber value="${aom.amount}" pattern="###,###,###,###"/>원</div>
				<div style="font-size: 13px; font-weight: 300;">${aom.memo}</div>
			<div id="eachAssetEdit">
				<a href="edit?memAssetId=${aom.memAssetId}" style="text-decoration: none; color:#cccccc"><i class="fas fa-edit"></i></a>
			</div>
			</div>
			<br>
		</c:forEach>
		
		<c:forEach items="${debtList}" var="aom">
			<div id="eachAssetText" style="background:#c07675;">
				<div></div>
				<div>${aom.assetsName}</div>
				<div id="eachAssetAmount" class="eAmount" style="text-align: right; font-size:30px; font-weight: 900;">
				<fmt:formatNumber value="${aom.amount}" pattern="###,###,###,###"/>원</div>
				<div style="font-size: 13px; font-weight: 300;">${aom.memo}</div>
			<div id="eachAssetEdit">
				<a href="edit?memAssetId=${aom.memAssetId}" style="text-decoration: none; color:#cccccc"><i class="fas fa-edit"></i></a>
			</div>
			</div>
			<br>
		</c:forEach>

		<c:forEach items="${zeroList}" var="aom">
			<div id="eachAssetText" style="background:#999999;">
				<div></div>
				<div>${aom.assetsName}</div>
				<div id="eachAssetAmount" class="eAmount" style="text-align: right; font-size:30px; font-weight: 900;">
				<fmt:formatNumber value="${aom.amount}" pattern="###,###,###,###"/>원</div>
				<div style="font-size: 13px; font-weight: 300;">${aom.memo}</div>
			<div id="eachAssetEdit">
				<a href="edit?memAssetId=${aom.memAssetId}" style="text-decoration: none; color:#cccccc"><i class="fas fa-edit"></i></a>
			</div>
			</div>
			<br>
		</c:forEach>
		
	</div>

		<div id="addAsset">
	<div id="eachAssetText" onClick="location.href='add'" 
	style="cursor:pointer; text-align: center; opacity:1; font-weight: 500; font-size: 12px; color:#666666;">내 자산 추가</div>
	</div>
	
	
  </asset>
  
  <news>
  <div style="margin-left:10px;">
   <h1 style="margin-top:30px; margin-left:0px; margin-bottom: 30px;">메모 검색 결과</h1>
   
   <div id="searchResultBox" style="margin-top:77px;">
   <c:forEach items="${result}" var="rst">
			<div style="background:#dddddd; margin:0px; padding:15px; border-radius: 0px;">
				<span style="text-align:left; display:inline-block;width:15%;">${rst.date}</span>
				<span style="text-align:center; display:inline-block;width:15%;">${rst.category}</span>
				<span style="text-align:left; display:inline-block;width:15%;"><fmt:formatNumber value="${rst.amount}" pattern="###,###,###,###"/>원</span>
				<span style="text-align:left; display:inline-block;width:50%;">${rst.memo}</span>
			</div>
			<br>
		</c:forEach>
 	</div><br>
		<input type="button" value="뉴스 탭으로 돌아가기" onClick="location.href='view'" 
			style="cursor:pointer; padding:15px; background:#333333; color:white; border:0px;">
 	</div>
  </news>
  
  <script type="text/javascript">
  if (${result}.length==0){
	  document.getElementById("searchResultBox").innerText = "해당하는 데이터가 없습니다.";
  }
  console.log(${result});
  </script>
  
</section>


</body>
</html>
