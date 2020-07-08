<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/chartJS.js?ver=1"></script>
<link rel="stylesheet" href="/css/statistics.css?ver=1">
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
</head>
<body>
<div class="wrapper">
	<!-- 상단 메뉴 부분 -->
	<header class="topmenu">
		<div class="grid_header">
			<div class="basic"><a id=mainlink href="/main/getCal">MAIN</a></div>
			<div class="statistics"><a id=staticlink href="/statistics/show">GRAPH</a></div>
			<div class="assest"><a id=assetlink href="/asset/view">ASSETS</a></div>
			<div class="board"><a id=boardlink href="/board/show">BOARD</a></div>
			<div class="gomypage"><button class="gomypage">MYPAGE</button></div>
			<div class="gologout"><button class="gologout">LOGOUT</button></div>
		</div>
	</header>

<!-- <div id="content"> -->
<div id="leftdayandweek">
<div id="leftday">
<form action="/statistics/show">
날짜 선택 <input id="date" type="date" name="date" max="9999-12-31">
<button id="formbutton"></button></form>
<div class="dayshow">
<div id="daymove"><button id="lastday">◀</button>${date}<button id="nextday">▶</button></div>
<span> 수지 합계 </span>
<table>
	<colgroup>
		<col width="33.3%">
		<col width="33.3%">
		<col width="33.3%">
	</colgroup>
	<c:set var = "esum" value = "0" />
	<c:set var = "isum" value = "0" />
	<thead>
		<tr><th>수입</th><th>지출</th><th>합계(원)</th></tr>
		<tr id="daytotal"><th><c:forEach var="e" items="${dailyIncome}">
			<c:set var= "isum" value="${isum + e.amount}"/>
		</c:forEach><fmt:formatNumber value="${isum}" pattern="###,###,###,###"/>
		</th>
		
		<th><c:forEach var="e" items="${dailyExpense}">
			<c:set var= "esum" value="${esum + e.amount}"/>
		</c:forEach><fmt:formatNumber value="${esum}" pattern="###,###,###,###"/></th>
		<th><fmt:formatNumber value="${isum-esum}" pattern="###,###,###,###"/></th>		
		</tr>
	</thead>
</table>
<!-- 일별 수입지출 내역<br> -->
<p>&nbsp;</p>
<table>	
	<thead>
		<tr><th>항목</th><th>사용자산</th><th>수입</th><th>지출</th><th>메모</th></tr>
	</thead>
	<tbody id="dayList">
		<c:if test="${dailyIncome.size()==0 && dailyExpense.size()==0}">
			<tr><td class="daynodata" colspan="5">No data</td></tr>
		</c:if>
			<c:forEach var="i" items="${dailyIncome}">
				<tr id="dayincome"><td>${i.icName}</td><td>${i.assetsName}</td>
				<td><fmt:formatNumber value="${i.amount}" pattern="###,###,###,###"/></td>
				<td></td><td>${i.memo}</td></tr>
			</c:forEach>
			<c:forEach var="e" items="${dailyExpense}">
				<tr id="dayexpense"><td>${e.ecName}</td><td>${e.assetsName}</td><td></td>
				<td><fmt:formatNumber value="${e.amount}" pattern="###,###,###,###"/></td>
				<td>${e.memo}</td></tr>
			</c:forEach>
	</tbody>
</table>
</div>
</div>

<div id="leftweek">
<div style="text-align:center"><button id="lastweek">◀</button>주별 수입지출 현황<button id="nextweek">▶</button></div>
<table>
	<thead>
		<tr><th style='min-width:35px; width:35px;'></th><th class="weekwidth">일</th><th class="weekwidth">월</th>
		<th class="weekwidth">화</th><th class="weekwidth">수</th>
		<th class="weekwidth">목</th><th class="weekwidth">금</th><th class="weekwidth">토</th></tr>
		<tr id="weekday"><th></th><c:forEach var="we" items="${weekExpense}">
				<th><c:set var="weekdate" value="${we.expenseDate}"/>
									
					<c:if test="${fn:substring(weekdate,5,6).equals('0')}">${fn:substring(weekdate,6,7)}</c:if>
					<c:if test="${fn:substring(weekdate,5,6).equals('1')}">${fn:substring(weekdate,5,7)}</c:if>
					/					  
					<c:choose>
						<c:when test="${fn:substring(weekdate,8,9).equals('0')}">${fn:substring(weekdate,9,10)}</c:when>
						<c:otherwise>${fn:substring(weekdate,8,10)}</c:otherwise>
					</c:choose>
				</th>
			</c:forEach></tr>
	</thead>
	<tbody>
		<tr id="weekincome"><td>수입 </td>
			<c:forEach var="wi" items="${weekIncome}">
				<td><fmt:formatNumber value="${wi.amount}" pattern="###,###,###,###"/></td>
			</c:forEach>
		</tr>		
		<tr id="weekexpense"><td>지출</td>
			<c:forEach var="we" items="${weekExpense}">
				<td><fmt:formatNumber value="${we.amount}" pattern="###,###,###,###"/></td>
			</c:forEach>
		</tr>		
		<tr id="weektotal"><td>합계 </td>
			<c:forEach var="i" begin="1" end="7">
				<td><fmt:formatNumber value="${weekIncome.get(i-1).amount-weekExpense.get(i-1).amount}" pattern="###,###,###,###"/>
				</td>
			</c:forEach>
		</tr>		
	</tbody>
</table>
</div>
</div>


<div id="rightmonth">
<div style="text-align:center"><button id="lastmonth">◀</button>${date.toString().substring(0,7)} <button id="nextmonth">▶</button></div>
<div id="ExpenseChart"></div>
<div id="profitChart"></div>
</div>

<div id="bottomyear">
<div style="text-align:center"><button id="lastyearbutton">◀</button>${date.toString().substring(0,4)} <button id="nextyearbutton">▶</button></div>
<div id="yearChart">
</div>
</div>

<script type="text/javascript">
var monthExpenseAmountData =new Array();
var monthExpenseNameData =new Array();
<c:forEach var="me" items="${monthExpense}">
monthExpenseAmountData.push(${me.amount});
monthExpenseNameData.push("${me.ecName}");
</c:forEach>

var monthIncomeAmountData =new Array();
var monthIncomeNameData =new Array();
<c:forEach var="mi" items="${monthIncome}">
monthIncomeAmountData.push(${mi.amount});
monthIncomeNameData.push("${mi.icName}");
</c:forEach>

var yearIncomeAmountData =new Array();
var yearExpenseAmountData =new Array();
<c:forEach var="ydata" begin="0" end="11">
yearIncomeAmountData.push(${yearIncomeData[ydata]});
yearExpenseAmountData.push(${yearExpenseData[ydata]});
</c:forEach>

var lastmonth ="${lastmonth}";
var nextmonth ="${nextmonth}";
var lastyear ="${lastyear}";
var nextyear ="${nextyear}";

</script>


<a class="gotop" href="#leftdayandweek" title="맨 위로">맨 위로</a> 
</div>
</body>
</html>