<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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
table{border-spacing: none; }
th,td{border: 1px solid black;}
</style>
</head>
<body>
<div>
일별 수입지출 내역
<table>
	<thead>
		<tr><th>수입</th><th>지출</th><th>합계(원)</th></tr>
		<tr><th>100</th><th>200</th><th>-100</th></tr>
	</thead>
</table>
<table>
	<thead>
		<tr><th>항목</th><th>사용자산</th><th>수입</th><th>지출</th></tr>
	</thead>
	<tbody>
		<tr>
			<td>숙소비 </td><td>현대카드 </td><td></td><td>200</td>
		</tr>
		<tr>
			<td>월급 </td><td>국민은행 </td><td>100</td><td></td>
		</tr>
	</tbody>
</table>
</div>

<div>
주별 수입지출 내역
<table>
	<thead>
		<tr><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th><th>일</th></tr>
	</thead>
	<tbody>
		<tr>
			<td>수입 </td><td>수입 </td><td>수입</td><td>수입</td><td>수입 </td><td>수입 </td><td>수입</td>
		</tr>		
		<tr>
			<td>지출 </td><td>지출 </td><td>지출</td><td>지출</td><td>지출 </td><td>지출 </td><td>지출</td>
		</tr>		
		<tr>
			<td>합계 </td><td>합계 </td><td>합계</td><td>합계</td><td>합계 </td><td>합계 </td><td>합계</td>
		</tr>		
	</tbody>
</table>
</div>
<div id="ExpenseChart" style="width: 500px; height: 500px;"></div>
<div id="profitChart" style="width: 500px; height: 500px;"></div>
<div id="yearChart" style="width: 900px; height: 500px;"></div>
</body>
</html>