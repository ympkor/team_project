$(function() {
	//인풋창에 오늘 날짜 표시해주기
	var date=document.getElementById('daymove').innerText.slice(1,11);
	document.getElementById("date").value=date;
var me = document.getElementById("mExpense").children;
var mi = document.getElementById("mIncome").children;

document.getElementById('lastday').onclick = function(){	
	var d = new Date(date);
	d.setDate(d.getDate()-1 );
	var lastday= d.getFullYear()+"-"+
		(d.getMonth()>10 ? d.getMonth()+1 : '0'+(d.getMonth()+1))
		+"-"+(d.getDate()>9 ? d.getDate() : '0'+ d.getDate());
	console.log(lastday);
	document.getElementById("date").value=lastday;		    
    document.getElementById("formbutton").click();
};
document.getElementById('nextday').onclick = function(){
	var d = new Date(date);
	d.setDate(d.getDate()+1 );
	var nextday= d.getFullYear()+"-"+
		(d.getMonth()>10 ? d.getMonth()+1 : '0'+(d.getMonth()+1))
		+"-"+(d.getDate()>9 ? d.getDate() : '0'+ d.getDate());
	console.log(nextday);
	document.getElementById("date").value=nextday;	 
	document.getElementById("formbutton").click();
};
document.getElementById('lastweek').onclick = function(){	
	var d = new Date(date);
	var dayOfMonth = d.getDate();
	d.setDate(dayOfMonth - 7);
	var lastweek= d.getFullYear()+"-"+
	(d.getMonth()>10 ? d.getMonth()+1 : '0'+(d.getMonth()+1))
	+"-"+(d.getDate()>9 ? d.getDate() : '0'+ d.getDate());
	//console.log(lastweek);
	document.getElementById("date").value=lastweek;		    
	document.getElementById("formbutton").click();
};
document.getElementById('nextweek').onclick = function(){
	var d = new Date(date);
	var dayOfMonth = d.getDate();
	d.setDate(dayOfMonth + 7);
	var nextweek= d.getFullYear()+"-"+
	(d.getMonth()>10 ? d.getMonth()+1 : '0'+(d.getMonth()+1))
	+"-"+(d.getDate()>9 ? d.getDate() : '0'+ d.getDate());
	document.getElementById("date").value=nextweek;		    
	document.getElementById("formbutton").click();
};
//전달
document.getElementById('lastmonth').onclick = function(){	
	var lastmonth= document.getElementById("lastmonthdate").innerHTML;
	document.getElementById("date").value=lastmonth;		    
	document.getElementById("formbutton").click();
};
//다음달
document.getElementById('nextmonth').onclick = function(){	
	var nextmonth= document.getElementById("nextmonthdate").innerHTML;
	document.getElementById("date").value=nextmonth;		    
	document.getElementById("formbutton").click();
};
//전년
document.getElementById('lastyearbutton').onclick = function(){	
	var lastyear= document.getElementById("lastyeardate").innerHTML;
	document.getElementById("date").value=lastyear;		    
	document.getElementById("formbutton").click();
};
//내년
document.getElementById('nextyearbutton').onclick = function(){	
	var nextyear= document.getElementById("nextyeardate").innerHTML;
	document.getElementById("date").value=nextyear;		    
	document.getElementById("formbutton").click();
};

	//표그리기
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
		  var data = new google.visualization.DataTable();
		  data.addColumn('string', 'classification');
		  data.addColumn('number', 'price');     	                 
	  	for (var i = 0; i < me.length/2 ; i++) {
	  		data.addRows([
	  			[ me[i*2].innerHTML , Number(me[i*2+1].innerHTML) ]
 		   	]);
		}       	  
		  var options = {
		    title: date.slice(0,7)+'월 지출 현황 '
		  };
		  var chart = new google.visualization.PieChart(document.getElementById('ExpenseChart'));
		  chart.draw(data, options);
				  
				  
		  var dataincome = new google.visualization.DataTable();
		  dataincome.addColumn('string', 'classification');
		  dataincome.addColumn('number', 'money'); 
		  for (var i = 0; i < mi.length/2 ; i++) {
			  dataincome.addRows([
	  		   [ mi[i*2].innerHTML , Number(mi[i*2+1].innerHTML) ]
	  		 ]);
			}      
		var options = {
		  title: date.slice(0,7)+'월수익 현황 '
		};
		var chart = new google.visualization.PieChart(document.getElementById('profitChart'));
		chart.draw(dataincome, options);
		}
			var ye = document.getElementById("yearE").children;
			var yi = document.getElementById("yearI").children;
		
		google.charts.setOnLoadCallback(drawVisualization);
		function drawVisualization() {
		   var data =new google.visualization.DataTable();
		   		data.addColumn('string', '월');
			    data.addColumn('number', '수입');
		       data.addColumn('number', '지출');
		       data.addColumn('number', '합계');		    	
			    for (var i = 0; i < 12; i++) {					    	
			    	data.addRow([ i+1+'월', Number(yi[i].innerHTML)
			    		, Number(ye[i].innerHTML), Number(yi[i].innerHTML)-Number(ye[i].innerHTML) ]);
				} 
			  var options = {			    
			    vAxis: {title: '금액(원)'},			    
			    seriesType: 'bars',
			    series: {5: {type: 'line'}}        };

			  var chart = new google.visualization.ComboChart(document.getElementById('yearChart'));
			  chart.draw(data, options);
			}
});