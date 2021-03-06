<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통계</title>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/chartJS.js?ver=2"></script>
<link rel="stylesheet" href="/css/statistics.css?ver=1">
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
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
<input id="date" type="date" name="date" max="9999-12-31">
<button id="formbutton"></button></form>
<div class="dayshow">
<div id="daymove"><button id="lastday">◀</button>${date}<button id="nextday">▶</button></div>
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
		<c:choose>
			<c:when test="${isum-esum<0}">
			<th style="color:red">
			<fmt:formatNumber value="${isum-esum}" pattern="###,###,###,###"/></th>		
			</c:when>
			<c:when test="${isum-esum>0}">
			<th style="color:blue">
			<fmt:formatNumber value="${isum-esum}" pattern="###,###,###,###"/></th>		
			</c:when>
			<c:otherwise>
			<th>
			<fmt:formatNumber value="${isum-esum}" pattern="###,###,###,###"/></th>		
			</c:otherwise>
		</c:choose>		
	</thead>
</table>
<div class="daydetailList">
<table>	
	<thead>
		<tr><th>항목</th><th>사용자산</th><th>수입</th><th>지출</th><th class="daymemohead">메모</th></tr>
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
				<td class="daymemo">${e.memo}</td></tr>
			</c:forEach>
	</tbody>
</table>
</div>

</div>
</div>

<div id="leftweek">
<div style="text-align:center"><button id="lastweek">◀</button>Weekly<button id="nextweek">▶</button></div>
<div class="weekdata">
<table class="weektable">
	<thead>
		<tr><th style='min-width:35px; width:35px;'></th><th class="weekwidth" style="color:red">일</th><th class="weekwidth">월</th>
		<th class="weekwidth">화</th><th class="weekwidth">수</th>
		<th class="weekwidth">목</th><th class="weekwidth">금</th><th class="weekwidth" style="color:blue">토</th></tr>
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
				<c:choose>
					<c:when test="${weekIncome.get(i-1).amount-weekExpense.get(i-1).amount <0}">
						<td style="color:red"><fmt:formatNumber value="${weekIncome.get(i-1).amount-weekExpense.get(i-1).amount}" pattern="###,###,###,###"/>
						</td>	
					</c:when>
					<c:when test="${weekIncome.get(i-1).amount-weekExpense.get(i-1).amount >0}">
						<td style="color:blue"><fmt:formatNumber value="${weekIncome.get(i-1).amount-weekExpense.get(i-1).amount}" pattern="###,###,###,###"/>
						</td>	
					</c:when>			
					<c:otherwise>
						<td><fmt:formatNumber value="${weekIncome.get(i-1).amount-weekExpense.get(i-1).amount}" pattern="###,###,###,###"/>
						</td>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
		</tr>		
	</tbody>
</table>
</div>
</div>
</div>


<div id="rightmonth">
<div style="text-align:center"><button id="lastmonth">◀</button>${date.toString().substring(0,7)}<button id="nextmonth">▶</button></div>
<div class="mtotaldiv">합계 :
	<c:set var="metotal" value="0"/>
	<c:forEach var="me" items="${monthExpense}">
		<c:set var="metotal" value="${me.amount+metotal}"/>		
	</c:forEach>
	<c:set var="mitotal" value="0"/>
	<c:forEach var="mi" items="${monthIncome}">
		<c:set var="mitotal" value="${mi.amount+mitotal}"/>		
	</c:forEach>   
	<c:choose>
		<c:when test="${mitotal-metotal>0}"><span style="color:blue"><fmt:formatNumber value="${mitotal-metotal}" pattern="###,###,###,###"/></span></c:when>
		<c:when test="${mitotal-metotal<0}"><span style="color:red"><fmt:formatNumber value="${mitotal-metotal}" pattern="###,###,###,###"/></span></c:when>
		<c:otherwise><fmt:formatNumber value="${mitotal-metotal}" pattern="###,###,###,###"/></c:otherwise>
	</c:choose>
	원
</div>

<div class="mpiegraph">

<div class="metitleandgraphdiv">
<div class="metotaldiv">월지출 : 
	<c:set var="metotal" value="0"/>
	<c:forEach var="me" items="${monthExpense}">
		<c:set var="metotal" value="${me.amount+metotal}"/>		
	</c:forEach>   
	<fmt:formatNumber value="${metotal}" pattern="###,###,###,###"/>원
</div>
<div id="ExpenseChart"></div>
</div>
<div class="mititleandgraphdiv">
<div class="mitotaldiv">월수입 : 
	<c:set var="mitotal" value="0"/>
	<c:forEach var="mi" items="${monthIncome}">
		<c:set var="mitotal" value="${mi.amount+mitotal}"/>		
	</c:forEach>   
	<fmt:formatNumber value="${mitotal}" pattern="###,###,###,###"/>원
</div>
<div id="profitChart"></div>
</div>

</div>

</div>

<div id="bottomyear">
<div id="yearselect"><button id="lastyearbutton">◀</button>${date.toString().substring(0,4)}<button id="nextyearbutton">▶</button></div>
<div class="yeardatadiv">
	<table class="yeartotalinfo">
		<tr>
			<td>수입:
				<c:set var="yitotal" value="0"/>
				<c:forEach var="ydata" begin="0" end="11">
					<c:set var="yitotal" value="${yearIncomeData[ydata]+mitotal}"/>		
				</c:forEach>   
				<fmt:formatNumber value="${yitotal}" pattern="###,###,###,###"/>원
			</td>
			<td>합계 :				
				<c:set var="ytotal" value="0"/>
				<c:forEach var="ydata" begin="0" end="11">
					<c:set var="ytotal" value="${ytotal+yearIncomeData[ydata]-yearExpenseData[ydata]}"/>		
				</c:forEach> 
				
				<c:choose>
					<c:when test="${ytotal>0}"><span style="color:blue"><fmt:formatNumber value="${ytotal}" pattern="###,###,###,###"/></span></c:when>
					<c:when test="${ytotal<0}"><span style="color:red"><fmt:formatNumber value="${ytotal}" pattern="###,###,###,###"/></span></c:when>
					<c:otherwise><fmt:formatNumber value="${ytotal}" pattern="###,###,###,###"/></c:otherwise>
				</c:choose>
				원
			</td>
			<td>지출:
				<c:set var="yetotal" value="0"/>
				<c:forEach var="yedata" begin="0" end="11">
					<c:set var="yetotal" value="${yearExpenseData[yedata]+yetotal}"/>		
				</c:forEach>   
				<fmt:formatNumber value="${yetotal}" pattern="###,###,###,###"/>원
			</td>
			
		</tr>
	</table>


</div>
<div id="yearChart"></div>
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


<a class="gotop" href="#leftdayandweek" title="맨 위로"></a> 
</div>
</body>
</html>