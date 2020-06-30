<%@ page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
 
map = new HashMap<Object,Object>(); map.put("label", "자산"); map.put("y", 35); map.put("exploded", true); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "부채"); map.put("y", 65); list.add(map);

String dataPoints = gsonObj.toJson(list);
%> <!-- 자산/부채 비율 그래프 나타내는 라이브러리 (아직 db연동은 되지 않았고 구현중 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산</title>
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2",
	animationEnabled: true,
	exportFileName: "assetRatio",
	exportEnabled: true,
	title:{
		text: "자산/부채 비율"
	},
	data: [{
		type: "pie",
		showInLegend: true,
		legendText: "{label}",
		toolTipContent: "{label}: <strong>{y}%</strong>",
		indexLabel: "{label} {y}%",
		dataPoints : <%out.print(dataPoints);%>
	}]
});
chart.render();
}
</script>
<style>
</style>
</head>

<body>
	<div>
		<div>
			<h1>내 자산 현황</h1>
			<h1>총 ${sumAsset}원</h1>
			<br>
		</div>
		<c:forEach items="${aomList}" var="list"> <!-- 멤버보유 자산 출력 -->
			<div>
				${list.name} 님의 ${list.type}<br> ${list.assetsName}<br>
				${list.amount}원<br> ${list.memo}
				<div>
					<a href="edit?memAssetId=${list.memAssetId}">
					수정</a>
					<a href="delete?memAssetId=${list.memAssetId}" onclick="return confirm('정말 삭제하시겠습니까?')">
					삭제</a>
					<!-- 수정, 삭제 클릭시 url에서 파라미터값 안보이게 하는법 아시는 분 도움 부탁드려요 -->
				</div>
			</div>
			<br>
		</c:forEach>

		<input type="button" name="addAsset" value="내 자산 추가" onClick="location.href='add'">
	</div>
	<div>
		
	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script><br><br>

		${newsList}
		
		<!-- json배열 꺼내서 출력하는 방법 찾는 중... -->
		
		<%-- <c:set var="items" value="${requestScope['newsList'].items}"/>
		
		<c:forEach var="item" items="${items}" varStatus="status">

		<c:out value="${item.title}"/>
		<c:out value="${item.description}"/>
		<c:out value="${item.pubDate}"/>

		</c:forEach> --%>
	
	</div>

</body>
</html>
