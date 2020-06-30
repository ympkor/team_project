<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/chartJS.js"></script>
<link rel="stylesheet" href="/css/statistics.css">
</head>
<body>
<div id="topmenu">
<div class="basic"><a href="/main/getCal">가계부</a></div>
<div class="statistics"><a href="/statistics/show">통계</a></div>
<div class="assest"><a href="/asset/view">자산</a></div>
<div class="board"><a href="/board/show">게시판</a></div>
</div>

<div id="content">
<form action="/statistics/show"><input id="date" type="date" name="date"><button id="formbutton" >일자선택</button></form>
<div id="leftdayandweek">
<div>
<div id="daymove"><button id="lastday">&lt;</button>${date}<button id="nextday">&gt;</button></div>
<span> 수지 합계 </span>
<table>
	<c:set var = "esum" value = "0" />
	<c:set var = "isum" value = "0" />
	<thead>
		<tr><th>수입</th><th>지출</th><th>합계(원)</th></tr>
		<tr id="daytotal"><th><c:forEach var="e" items="${dailyIncome}">
			<c:set var= "isum" value="${isum + e.amount}"/>
		</c:forEach><c:out value="${isum}"/></th><th><c:forEach var="e" items="${dailyExpense}">
			<c:set var= "esum" value="${esum + e.amount}"/>
		</c:forEach><c:out value="${esum}"/></th><th>${isum-esum}</th></tr>
	</thead>
</table>
일별 수입지출 내역<br>
<table>
	<thead>
		<tr><th>항목</th><th>사용자산</th><th>수입</th><th>지출</th><th>메모</th></tr>
	</thead>
	<tbody id="dayList">
			<c:forEach var="i" items="${dailyIncome}">
				<tr id="dayincome"><td>${i.icName}</td><td>${i.assetsName}</td><td>${i.amount}</td><td></td><td>${i.memo}</td></tr>
			</c:forEach>
			<c:forEach var="e" items="${dailyExpense}">
				<tr id="dayexpense"><td>${e.ecName}</td><td>${e.assetsName}</td><td></td><td>${e.amount}</td><td>${e.memo}</td></tr>
			</c:forEach>
	</tbody>
</table>
</div>

<div style="width:500px">
<div style="text-align:center"><button id="lastweek">&lt;</button>주별 수입지출 현황<button id="nextweek">&gt;</button></div>
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
				<td>${wi.amount}</td>
			</c:forEach>
		</tr>		
		<tr id="weekexpense"><td>지출합계</td>
			<c:forEach var="we" items="${weekExpense}">
				<td>${we.amount}</td>
			</c:forEach>
		</tr>		
		<tr id="weektotal"><td>총합계 </td>
			<c:forEach var="i" begin="1" end="7">
				<td>${weekIncome.get(i-1).amount-weekExpense.get(i-1).amount}</td>
			</c:forEach>
		</tr>		
	</tbody>
</table>
</div>
</div>

<div id="rightmonth">
<div style="text-align:center"><button id="lastmonth">&lt;</button>${date.toString().substring(0,7)} <button id="nextmonth">&gt;</button></div>
<div id="ExpenseChart"></div>
<div id="profitChart"></div>
</div>
<div id="bottomyear">
<div style="text-align:center"><button id="lastyearbutton">&lt;</button>${date.toString().substring(0,4)} <button id="nextyearbutton">&gt;</button></div>
<div id="yearChart">
</div>
</div>
<div id="chartData">
	<div id="mExpense"><c:forEach var="me" items="${monthExpense}">
<span>${me.ecName}</span><span>${me.amount}</span>
</c:forEach></div>
	<div id="mIncome"><c:forEach var="mi" items="${monthIncome}">
<span>${mi.icName}</span><span>${mi.amount}</span>
</c:forEach></div>
<div id="yearE">
<c:forEach var="ye" items="${yearExpenseData}"><span>${ye}</span></c:forEach>
</div>
<div id="yearI">
<c:forEach var="yi" items="${yearIncomeData}"><span>${yi}</span></c:forEach>
<div id="lastmonthdate">${lastmonth}</div>
<div id="nextmonthdate">${nextmonth}</div>
<div id="lastyeardate">${lastyear}</div>
<div id="nextyeardate">${nextyear}</div>
</div>

</div>
</div>
<a class="gotop" href="#topmenu" title="맨 위로">맨 위로</a> 
</body>
</html>