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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/chartJS.js?ver=1"></script>
<link rel="stylesheet" href="/css/statistics.css?ver=1">
</head>
<body class="wrapper">
<div id="topmenu">
<div class="basic"><a href="/main/getCal">가계부</a></div>
<div class="statistics"><a href="/statistics/show">통계</a></div>
<div class="assest"><a href="/asset/view">자산</a></div>
<div class="board"><a href="/board/show">게시판</a></div>
<button class="gomypage">마이페이지</button>
<button class="gologout">로그아웃</button>
</div>

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

<div id="leftweek" style="width:500px">
<div style="text-align:center"><button id="lastweek">◀</button>주별 수입지출 현황<button id="nextweek">▶</button></div>
<table>
	<thead>
		<tr><th style='width:35px'></th><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>
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
		<tr id="weekincome"><td>수입합계 </td>
			<c:forEach var="wi" items="${weekIncome}">
				<td><fmt:formatNumber value="${wi.amount}" pattern="###,###,###,###"/></td>
			</c:forEach>
		</tr>		
		<tr id="weekexpense"><td>지출합계</td>
			<c:forEach var="we" items="${weekExpense}">
				<td><fmt:formatNumber value="${we.amount}" pattern="###,###,###,###"/></td>
			</c:forEach>
		</tr>		
		<tr id="weektotal"><td>총합계 </td>
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

</div>
<a class="gotop" href="#topmenu" title="맨 위로">맨 위로</a> 
</body>
</html>