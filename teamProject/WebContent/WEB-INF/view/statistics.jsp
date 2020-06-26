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
<script type="text/javascript">
	  window.addEventListener("DOMContentLoaded",function(){		
	//전달받은 날짜가 없으면 현재날짜로(기본), 전달받은 날짜가 있으면 전달받은 날자가 찍히게 
	//document.querySelector('input').value=new Date().toISOString().slice(0, 10);
	
	for (var i = 1; i < 8; i++) {
		document.querySelectorAll("#weektotal>td")[i].innerText=
			document.querySelectorAll("#weincome>td")[i].innerText-
			document.querySelectorAll("#weexpense>td")[i].innerText
	}
	
	 });

</script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
    	  var data = new google.visualization.DataTable();
          data.addColumn('string', 'classification');
          data.addColumn('number', 'price');
          var name= ["숙소비","관광","선물"];
          var price=[300,20,50];          
        	for (var i = 0; i < 3; i++) {
          		data.addRows([
        		   [name[i], price[i] ]
        		 ]);
			}        	  
            
        var options = {
          title: '지출 '
        };
        var chart = new google.visualization.PieChart(document.getElementById('ExpenseChart'));
        chart.draw(data, options);
        
        
        var dataincome = new google.visualization.DataTable();
        dataincome.addColumn('string', 'classification');
        dataincome.addColumn('number', 'money');
        dataincome.addRows([
          ['월급', 500],
          ['용돈', 10]      
        ]);
      var options = {
        title: '수익 '
      };
      var chart = new google.visualization.PieChart(document.getElementById('profitChart'));
      chart.draw(dataincome, options);
        
      }
      
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['월', '수입', '지출', '합계'],
          ['2월',  500,      10,         490],
          ['3월',  165,      938,         522],
          ['4월',  165,      938,         522],
          ['5월',  165,      938,         522],
          ['6월',  165,      938,         522],
          ['7월',  165,      938,         522],
          ['8월',  165,      938,         522],
          ['9월',  165,      938,         522],
          ['10월',  165,      938,         522],
          ['11월',  165,      938,         522],
          ['12월',  136,      691,         629]
        ]);

        var options = {
          title : '2020 월별 그래프 수지현황',
          vAxis: {title: '금액'},
          hAxis: {title: '월'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}        };

        var chart = new google.visualization.ComboChart(document.getElementById('yearChart'));
        chart.draw(data, options);
      }
</script>
<style type="text/css">
table{border-collapse: collapse; width:500px;}
th,td{border: 1px solid black;}
div{border: 1px dotted blue;}
#ExpenseChart{width:900px;}
</style>
</head>
<body>
<form action="/statistics/show"><input id="date" type="date" name="day"><button>일자선택</button></form>

<div>
하루 수지 총합  
<table>
	<c:set var = "esum" value = "0" />
	<c:set var = "isum" value = "0" />
	<thead>
		<tr><th>수입</th><th>지출</th><th>합계(원)</th></tr>
		<tr><th><c:forEach var="e" items="${dailyIncome}">
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
	<tbody>
			<c:forEach var="i" items="${dailyIncome}">
				<tr><td>${i.icName}</td><td>${i.assetsName}</td><td>${i.amount}</td><td></td><td>${i.memo}</td></tr>
			</c:forEach>
			<c:forEach var="e" items="${dailyExpense}">
				<tr><td>${e.ecName}</td><td>${e.assetsName}</td><td></td><td>${e.amount}</td><td>${e.memo}</td></tr>
			</c:forEach>
	</tbody>
</table>
</div>

<div>
주별 수입지출 내역
<table>
	<thead>
		<tr><th style='width:35px'></th><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>
		<tr><th></th><c:forEach var="we" items="${weekexpense}">
				<th><c:set var="day" value="${we.expenseDate.toString()}"/>${fn:substring(day,5,10) }
				</th>
			</c:forEach></tr>
	</thead>
	<tbody>
		<tr id="weincome"><td>수입합계 </td>
			<c:forEach var="wi" items="${weekincome}">
				<td>${wi.amount}</td>
			</c:forEach>
		</tr>		
		<tr id="weexpense"><td>지출합계</td>
			<c:forEach var="we" items="${weekexpense}">
				<td>${we.amount}</td>
			</c:forEach>
		</tr>		
		<tr id="weektotal"><td>총합계 </td>
			<c:forEach var="i" begin="1" end="7">
				<td></td>
			</c:forEach>
		</tr>		
	</tbody>
</table>
</div>

<div id="ExpenseChart" style="width: 500px; height: 500px;"></div>
<div id="profitChart" style="width: 500px; height: 500px;"></div>
<div id="yearChart" style="width: 900px; height: 500px;"></div>


</body>
</html>