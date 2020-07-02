//월별 이체 데이터를 만들어진 달력에 뿌려주는 기능
function showThisMonthTransfer(tbmList) {
	let classStr;
	if(tbmList != null){
		for(let i = 0; i < tbmList.length; i++){
			if(tbmList[i].transferDate.dayOfMonth < 10){classStr = '.dt0'+tbmList[i].transferDate.dayOfMonth;}
			else{classStr = '.dt'+tbmList[i].transferDate.dayOfMonth;}
			document.querySelector(classStr).innerHTML += '<div class=transfer><span class=transferName>이체</span> <span class=trAmount>'+tbmList[i].amount+'원</span></div>';
		}
	}
}
//수입 상세 내역 온클릭 시, 업데이트 폼을 불러오고 그 안에 기존 데이터를 입력해준다.
function putIncomeDataToUpdateForm(iicaList) {
	event.stopPropagation();
	for(let i = 0; i < iicaList.length; i++){
		let classStr = 'div.di_income'+i;;
		document.querySelector(classStr).addEventListener('click', function() {
			event.stopPropagation();
			document.querySelector('#modal').classList.remove('hidden');
			document.querySelector('#update_income_category_income').checked = true;
			document.querySelector('#update_income_form').classList.remove('hidden');
			document.querySelector('#update_expense_form').classList.add('hidden');
			document.querySelector('#update_transfer_form').classList.add('hidden');
			let thisDate;
			if(iicaList[i].incomeDate.monthValue < 10) {
				if(iicaList[i].incomeDate.dayOfMonth < 10) {thisDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
				}else{thisDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
			}else{if(iicaList[i].incomeDate.dayOfMonth < 10){thisDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
			}else {thisDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
			}
			document.querySelector('#update_income_id').value = iicaList[i].incomeId;
			document.querySelector('#update_income_date').value = thisDate;
			document.querySelector('#update_assets_income_memAssetIdFrom_origin').value = iicaList[i].memAssetId;
			document.querySelector('#update_assets_income_memAssetIdFrom').value = iicaList[i].memAssetId;
			showUpdateTransferFromAomIncome(iicaList[i]);
			document.querySelector('#update_assets_income_assetsId').value = iicaList[i].assetsId;
			showUpdateTransferToAomIncome(iicaList[i]);
			makeIncomeCategoryOption(iicaList[i]);
			document.querySelector('#update_income_amount_origin').value = iicaList[i].amount;
			document.querySelector('#update_income_amount').value = iicaList[i].amount;
			document.querySelector('#update_income_memo').value = iicaList[i].memo;
		});
	}
}
//수입업데이트 폼에 이체 카테고리를 현재 값으로 선택시켜준다.
function makeIncomeCategoryOption(iica) {
	$.ajax({
		url:'/main/postICList',
		type:'post',
		success:function(icList) {
			let str = '<option value='+iica.icId+' selected id="update_income_expense_category_selected">'+iica.icName+'</option>';
			for(let i = 0; i < icList.length; i++) {
				if(icList[i].icId != iica.icId) {
					str += '<option value='+icList[i].icId+'>'+icList[i].icName+'</option>';
				}
			}
		}
	});
}
//지출 상세 내역 온클릭 시, 업데이트 폼을 불러오고 그 안에 기존 데이터를 입력해준다.
function putExpenseDataToUpdateForm(eecaList) {
	event.stopPropagation();
	for(let i = 0; i < eecaList.length; i++){
		let classStr = 'div.di_expense'+i;;
		document.querySelector(classStr).addEventListener('click', function() {
			event.stopPropagation();
			document.querySelector('#modal').classList.remove('hidden');
			document.querySelector('#update_expense_category_expense').checked = true;
			document.querySelector('#update_income_form').classList.add('hidden');
			document.querySelector('#update_expense_form').classList.remove('hidden');
			document.querySelector('#update_transfer_form').classList.add('hidden');
			let thisDate;
			if(eecaList[i].expenseDate.monthValue < 10) {
				if(eecaList[i].expenseDate.dayOfMonth < 10) {thisDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
				}else{thisDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
			}else{if(eecaList[i].expenseDate.dayOfMonth < 10){thisDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
			}else {thisDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
			}
			document.querySelector('#update_expense_id').value = eecaList[i].expenseId;
			document.querySelector('#update_expense_date').value = thisDate;
			document.querySelector('#update_assets_expense_memAssetIdFrom_origin').value = eecaList[i].memAssetId;
			document.querySelector('#update_assets_expense_memAssetIdFrom').value = eecaList[i].memAssetId;
			showUpdateTransferFromAomExpense(eecaList[i]);
			document.querySelector('#update_assets_expense_assetsId').value = eecaList[i].assetsId;
			showUpdateTransferToAomExpense(eecaList[i]);
			makeExpenseCategoryOption(eecaList[i]);
			document.querySelector('#update_expense_amount_origin').value = eecaList[i].amount;
			document.querySelector('#update_expense_amount').value = eecaList[i].amount;
			document.querySelector('#update_expense_memo').value = eecaList[i].memo;
		});
	}
}
//지출 업데이트 폼에 이체 카테고리를 현재 값으로 선택시켜준다.
function makeExpenseCategoryOption(eeca) {
	$.ajax({
		url:'/main/postECList',
		type:'post',
		success:function(ecList) {
			let str = '<option value='+eeca.ecId+' selected id="update_expense_expense_category_selected">'+eeca.ecName+'</option>';
			for(let i = 0; i < ecList; i++) {
				if(ecList[i].ecId != eeca.ecId) {
					str += '<option value='+ecList[i].ecId+'>'+ecList[i].ecName+'</option>';
				}
			}
		}
	});
}
//이체 상세 내역 온클릭 시, 업데이트 폼을 불러오고 그 안에 기존 데이터를 입력해준다.
function putTransferDataToUpdateForm(taomfaomtList) {
	event.stopPropagation();
	for(let i = 0; i < taomfaomtList.length; i++){
		let classStr = 'div.di_transfer'+i;;
		document.querySelector(classStr).addEventListener('click', function() {
			event.stopPropagation();
			document.querySelector('#modal').classList.remove('hidden');
			document.querySelector('#update_transfer_category_transfer').checked = true;
			document.querySelector('#update_income_form').classList.add('hidden');
			document.querySelector('#update_expense_form').classList.add('hidden');
			document.querySelector('#update_transfer_form').classList.remove('hidden');
			let thisDate;
			if(taomfaomtList[i].transferDate.monthValue < 10) {
				if(taomfaomtList[i].transferDate.dayOfMonth < 10) {thisDate = taomfaomtList[i].transferDate.year+'\-0'+taomfaomtList[i].transferDate.monthValue+'\-0'+taomfaomtList[i].transferDate.dayOfMonth;
				}else{thisDate = taomfaomtList[i].transferDate.year+'\-0'+taomfaomtList[i].transferDate.monthValue+'\-'+taomfaomtList[i].transferDate.dayOfMonth;}
			}else{if(taomfaomtList[i].transferDate.dayOfMonth < 10){thisDate = taomfaomtList[i].transferDate.year+'\-'+taomfaomtList[i].transferDate.monthValue+'\-0'+taomfaomtList[i].transferDate.dayOfMonth;
			}else {thisDate = taomfaomtList[i].transferDate.year+'\-'+taomfaomtList[i].transferDate.monthValue+'\-'+taomfaomtList[i].transferDate.dayOfMonth;}
			}
			document.querySelector('#update_transfer_id').value = taomfaomtList[i].transferId;
			document.querySelector('#update_transfer_date').value = thisDate;
			document.querySelector('#update_assets_transfer_memAssetIdFrom_origin').value = taomfaomtList[i].memAssetIdFrom;
			document.querySelector('#update_assets_transfer_memAssetIdFrom').value = taomfaomtList[i].memAssetIdFrom;
			showUpdateTransferFromAomTransfer(taomfaomtList[i]);
			document.querySelector('#update_assets_transfer_assetsId').value = taomfaomtList[i].assetsId;
			document.querySelector('#update_assets_transfer_memAssetIdTo_origin').value = taomfaomtList[i].memAssetIdTo;
			showUpdateTransferToAomTransfer(taomfaomtList[i]);
			document.querySelector('#update_transfer_amount_origin').value = taomfaomtList[i].amount;
			document.querySelector('#update_transfer_amount').value = taomfaomtList[i].amount;
			document.querySelector('#update_transfer_memo').value = taomfaomtList[i].memo;
		});
	}
}
//수입업데이트 선택창에서 자산 선택지가 변경되면 assets_id를 자동으로 변경해주는 기능
function getAssetsIdAndPutAssetsIdToHiddenInputIncome(){
	let memAssetId = document.querySelector('#update_assets_income_memAssetIdFrom').value;
	let jsonData = {"memAssetId":memAssetId};
	$.ajax({
		url:'/main/postGetAOM',
		type:'post',
		data:jsonData,
		success:function(aom){
			document.querySelector('#update_assets_income_assetsId').value = aom.assetsId;
		}
	});
}
//지출업데이트 선택창에서 자산 선택지가 변경되면 assets_id를 자동으로 변경해주는 기능
function getAssetsIdAndPutAssetsIdToHiddenInputExpense(){
	let memAssetId = document.querySelector('#update_assets_expense_memAssetIdFrom').value;
	let jsonData = {"memAssetId":memAssetId};
	$.ajax({
		url:'/main/postGetAOM',
		type:'post',
		data:jsonData,
		success:function(aom){
			document.querySelector('#update_assets_expense_assetsId').value = aom.assetsId;
		}
	});
}
//이체 업데이트 선택창에서 자산 선택지가 변경되면 assets_id를 자동으로 변경해주는 기능
function getAssetsIdAndPutAssetsIdToHiddenInputTransfer(){
	let memAssetId = document.querySelector('#update_assets_transfer_memAssetIdFrom').value;
	let jsonData = {"memAssetId":memAssetId};
	$.ajax({
		url:'/main/postGetAOM',
		type:'post',
		data:jsonData,
		success:function(aom){
			document.querySelector('#update_assets_transfer_assetsId').value = aom.assetsId;
		}
	});
}
//수입 업데이트 폼 출금란에 해당 유저의 자산목록을 뿌려주는 기능
function showUpdateTransferFromAomIncome(iica) {
	event.stopPropagation();
	let jsonData = {"userKey":iica.userKey};
	$.ajax({
		url:'/main/postAAOM',
		type:'post',
		data:jsonData,
		success:function(aaomList) {
			let updateAOMStr;
			if(aaomList == null) {updateAOMStr = '<option id="update_assets_income_memAssetIdFrom_selected" value="0" selected>선택하세요</option>';
			}else {
				updateAOMStr = '<option id="update_assets_income_memAssetIdFrom_selected" value='+iica.memAssetId+' selected>'+iica.assetsName+' '+iica.aomName+'</option>';
				for(let i = 0; i < aaomList.length; i++) {
					if(aaomList[i].memAssetId != iica.memAssetId){
						updateAOMStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+"</option>";
					}
				}
				document.querySelector('#update_assets_income_memAssetIdFrom').innerHTML = updateAOMStr;
			}
		}
	});
}
//수입 업테이트 폼 입금란에 해당 유저의 자산목록을 뿌려주는 기능
function showUpdateTransferToAomIncome(iica) {
	event.stopPropagation();
	let jsonData = {"userKey":iica.userKey};
	$.ajax({
		url:'/main/postAAOM',
		type:'post',
		data:jsonData,
		success:function(aaomList) {
			let updateAOMStr;
			if(aaomList == null) {updateAOMStr = '<option id="update_assets_transfer_memAssetIdto_selected" value="0" selected>선택하세요</option>';
			}else {
				updateAOMStr = ' ';
				for(let i = 0; i < aaomList.length; i++) {
						updateAOMStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+"</option>";
				}
				document.querySelector('#update_assets_income_memAssetIdTo').innerHTML = updateAOMStr;
			}
		}
	});
}
//지출 업데이트 폼 출금란에 해당 유저의 자산목록을 뿌려주는 기능
function showUpdateTransferFromAomExpense(eeca) {
	event.stopPropagation();
	let jsonData = {"userKey":eeca.userKey};
	$.ajax({
		url:'/main/postAAOM',
		type:'post',
		data:jsonData,
		success:function(aaomList) {
			let updateAOMStr;
			if(aaomList == null) {updateAOMStr = '<option id="update_assets_expense_memAssetIdFrom_selected" value="0" selected>선택하세요</option>';
			}else {
				updateAOMStr = '<option id="update_assets_expense_memAssetIdFrom_selected" value='+eeca.memAssetIdTo+' selected>'+eeca.assetsName+' '+eeca.aomName+'</option>';
				for(let i = 0; i < aaomList.length; i++) {
					if(aaomList[i].memAssetId != eeca.memAssetId){
						updateAOMStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+"</option>";
					}
				}
				document.querySelector('#update_assets_expense_memAssetIdFrom').innerHTML = updateAOMStr;
			}
		}
	});
}
//지출 업테이트 폼 입금란에 해당 유저의 자산목록을 뿌려주는 기능
function showUpdateTransferToAomExpense(eeca) {
	event.stopPropagation();
	let jsonData = {"userKey":eeca.userKey};
	$.ajax({
		url:'/main/postAAOM',
		type:'post',
		data:jsonData,
		success:function(aaomList) {
			let updateAOMStr;
			if(aaomList == null) {updateAOMStr = '<option id="update_assets_expense_memAssetIdto_selected" value="0" selected>선택하세요</option>';
			}else {
				updateAOMStr = ' ';
				for(let i = 0; i < aaomList.length; i++) {
					updateAOMStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+"</option>";
				}
				document.querySelector('#update_assets_expense_memAssetIdTo').innerHTML = updateAOMStr;
			}
		}
	});
}
//이체 업데이트 폼 출금란에 해당 유저의 자산목록을 뿌려주는 기능
function showUpdateTransferFromAomTransfer(taomfaomt) {
	event.stopPropagation();
	let jsonData = {"userKey":taomfaomt.userKey};
	$.ajax({
		url:'/main/postAAOM',
		type:'post',
		data:jsonData,
		success:function(aaomList) {
			let updateAOMStr;
			if(aaomList == null) {updateAOMStr = '<option id="update_assets_transfer_memAssetIdFrom_selected" value="0" selected>선택하세요</option>';
			}else {
				updateAOMStr = '<option id="update_assets_transfer_memAssetIdFrom_selected" value='+taomfaomt.memAssetIdFrom+' selected>'+taomfaomt.assetsNameFrom+' '+taomfaomt.aomNameFrom+'</option>';
				for(let i = 0; i < aaomList.length; i++) {
					if(aaomList[i].memAssetId != taomfaomt.memAssetIdFrom){
						updateAOMStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+"</option>";
					}
				}
				document.querySelector('#update_assets_transfer_memAssetIdFrom').innerHTML = updateAOMStr;
			}
		}
	});
}
//이체 업테이트 폼 입금란에 해당 유저의 자산목록을 뿌려주는 기능
function showUpdateTransferToAomTransfer(taomfaomt) {
	event.stopPropagation();
	let jsonData = {"userKey":taomfaomt.userKey};
	$.ajax({
		url:'/main/postAAOM',
		type:'post',
		data:jsonData,
		success:function(aaomList) {
			let updateAOMStr;
			if(aaomList == null) {updateAOMStr = '<option id="update_assets_transfer_memAssetIdto_selected" value="0" selected>선택하세요</option>';
			}else {
				updateAOMStr = '<option id="update_assets_transfer_memAssetIdto_selected" value='+taomfaomt.memAssetIdTo+' selected>'+taomfaomt.assetsNameTo+' '+taomfaomt.aomNameTo+'</option>';
				for(let i = 0; i < aaomList.length; i++) {
					if(aaomList[i].memAssetId != taomfaomt.memAssetIdTo){
						updateAOMStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+"</option>";
					}
				}
				document.querySelector('#update_assets_transfer_memAssetIdTo').innerHTML = updateAOMStr;
			}
		}
	});
}
function showInsertIncomeAomBF(assetsId) {
  let jsonData = {"assetsId":assetsId};
  let updateAOMIncome = document.querySelector('#insert_aom_income');
  $.ajax({
    url:'/main/postAOM',
    type:'post',
    data:jsonData,
    success:function(aomList) {
			let updateAOMStr = '<option id="insert_aom_income_selected" value="" selected disabled>선택하세요</option>';
      if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
      }else {
        for(let i = 0; i < aomList.length; i++) {
          updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
        }
        updateAOMIncome.innerHTML = updateAOMStr;
      }
    }
  })
}
function showInsertExpenseAomBF(assetsId) {
  let jsonData = {"assetsId":assetsId};
  let updateAOMIncome = document.querySelector('#insert_aom_expense');
  $.ajax({
    url:'/main/postAOM',
    type:'post',
    data:jsonData,
    success:function(aomList) {
			let updateAOMStr = '<option id="insert_aom_expense_selected" value="" selected disabled>선택하세요</option>';
      if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
      }else {
        for(let i = 0; i < aomList.length; i++) {
          updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
        }
        updateAOMIncome.innerHTML = updateAOMStr;
      }
    }
  })
}
//이체 상세내역을 뿌려주는 기능. 내부에 온클릭시 이체 업데이트 내역을 뿌려주는 기능 포함
function makeDetailTransferDIV(taomfaomtList) {
	document.querySelector('div.detail_context_transfer').innerHTML = null;
	for(let i = 0; i < taomfaomtList.length; i++) {
		let str = '<div class="di_transfer'+i+' detailItem"><div class=detail_category><span class=detail_category>이체</span></div>';
		str += '<div class=detailEntry><span class=detailTMemo>'+taomfaomtList[i].memo+'</span><br><span class=detailAName>';
		str += taomfaomtList[i].assetsNameFrom+' '+taomfaomtList[i].aomNameFrom+'→'+taomfaomtList[i].assetsNameTo+' '+taomfaomtList[i].aomNameTo+'</span></div>';
		str += '<div class=detailTAmount><span class=detailTAmount>'+taomfaomtList[i].amount+'</span></div<</div>';
		document.querySelector('div.detail_context_transfer').innerHTML += str;
	}
}

/*################# 페이지가 모두 로드 된 이후 사용될 기능들 ################*/
window.addEventListener('DOMContentLoaded', function () {
  //필요한 태그들 변수 선언해두었다.
	const modal = document.getElementById('modal');
  const detailContextIncome = document.querySelector('div.detail_context_income');
  const detailContextExpense = document.querySelector('div.detail_context_expense');
  const selecDate = document.querySelector('#selecDate');
  const prevMonth = document.querySelector("#prevMonth");
  const nextMonth = document.querySelector('#nextMonth');
  const tbody = document.querySelector("#tbody");

  //캘린더 객체를 전달받아 해당 월에 맞는 달력을 만들어주는 기능
  function makecalendar(cal) {
		let currentMonth = cal.year;
		if(cal.month < 10){currentMonth += '\-0'+cal.month}else{currentMonth += '\-'+cal.month};
		document.querySelector('#selecDate').value = currentMonth;
    tbody.innerHTML = null;
    let calendarStr = '<tr>'
    for (let i = 2; i <= cal.firstDay; i++) {
      calendarStr += '<td class=dateTd><div class=dateDiv><span class=dateSpan></span><div class=entry><div class=entry_income></div><div class=entry_expense></div><div class=entry_transfer></div></div></div></td>';
      cal.cntDay++;
    }
    for (let i = 1; i <= cal.daysOfMonth[cal.month - 1]; i++) {
      calendarStr += '<td class=dateTd><div class=dateDiv><span class=dateSpan>' + i + '</span>';
      if (i < 10) { calendarStr += '<div class="entry d0'+i+'"><div class="entry_income di0' + i + '"></div><div class="entry_expense de0'+ i +'"></div><div class="entry_transfer dt0'+i+'"></div></div>';}
      else { calendarStr += '<div class="entry d'+i+'"><div class="entry_income di' + i + '"></div><div class="entry_expense de'+i+'"></div><div class="entry_transfer dt'+i+'"></div></div>'; }
      calendarStr += '</div></td>';
      cal.cntDay++;
      if (cal.cntDay % 7 == 0) { calendarStr += '</tr><tr>' }
    }
    calendarStr += '</tr>';
    tbody.innerHTML = calendarStr;
  }
  //해당월에 맞는 income data들을 전달받아 만들어진 달력에 뿌려주는 기능
  function showThisMonthIncome(miicList){
		for (let i = 0; i < miicList.length; i++) {
			let icName = miicList[i].icName;
      let icAmount = miicList[i].amount;
      let entryClassName;
      if (miicList[i].incomeDate.dayOfMonth < 10) { entryClassName = '.di0' + miicList[i].incomeDate.dayOfMonth; }
      else { entryClassName = '.di' + miicList[i].incomeDate.dayOfMonth; }
			let entry = document.querySelector(entryClassName);
			if(i == 0){entry.innerHTML = null}
			entry.innerHTML += '<div class=income><span class=icName>' + icName + '</span><span class=icAmount>' + icAmount + '원</span></div>';
    }
  }
  //해당월에 맞는 expense date들을 전달받아 만들어진 달력에 뿌려주는 기능
  function showThisMonthExpense(meecList) {
    for (let i = 0; i < meecList.length; i++) {
      let ecName = meecList[i].ecName;
      let ecAmount = meecList[i].amount;
      let entryClassName;
      if (meecList[i].expenseDate.dayOfMonth < 10) { entryClassName = '.de0' + meecList[i].expenseDate.dayOfMonth; }
      else { entryClassName = '.de' + meecList[i].expenseDate.dayOfMonth; }
			let entry = document.querySelector(entryClassName);
			if(i == 0){entry.innerHTML = null}
      entry.innerHTML += '<div class=expense><span class=ecName>' + ecName + '</span><span class=ecAmount>' + ecAmount + '원</span></div>';
    }
	}
  //상세란에 상세 수입을 뿌려주는 기능
  function makeDetailIncomeDIV(iicaList) {
		let sumI = 0;
		let str = '';
		if(iicaList != null) {
			for (let i = 0; i < iicaList.length; i++) {
				sumI += iicaList[i].amount;
				str += '<div class="di_income' + i + ' detailItem"><div class=detailIcName><span class=detailIcName>' + iicaList[i].icName + '</span></div>';
				str += '<div class=detailEntry><span class=detailIMemo>' + iicaList[i].memo + '</span><br><span class=detailAName>' + iicaList[i].assetsName + '</span></div>';
				str += '<div class=detailIAmount><span class=detailIAmount>' + iicaList[i].amount + '</span></div></div>';
			}
			detailContextIncome.innerHTML = str;
		}
		document.querySelector('span.detailSumI').innerText = sumI;

  }
  //상세란에 상세 지출을 뿌려주는 기능
  function makeDetailExpenseDIV(eecaList) {
		let sumE = 0;
		let str = '';
		if(eecaList != null){
			for (let i = 0; i < eecaList.length; i++) {
				sumE += eecaList[i].amount;
				str += '<div class="di_expense' + i + ' detailItem"><div class=detailEcName><span class=detailEcName>' + eecaList[i].ecName + '</span></div>';
				str += '<div class=detailEntry><span class=detailEMemo>' + eecaList[i].memo + '</span><br><span class=detailAName>' + eecaList[i].assetsName + '</span></div>';
				str += '<div class=detailEAmount><span class=detailEAmount>' + eecaList[i].amount + '</span></div></div>';
			}
			detailContextExpense.innerHTML = str;
		}
    document.querySelector('span.detailSumE').innerText = sumE;
  }
  //만들어진 달력에 클릭한 날짜에 해당하는 상세데이터를 출력해주는 이벤트를 걸어주는 기능
  function put_MakeDetailDiv_to_dateTd(dateTd) {
    for (let i = 0; i < dateTd.length; i++) {
      //상세데이터를 뿌려야되는 div.detailContext, div.detailDate미리 설정
      let detailDateSpan = document.querySelector('span.detailDate');
      //날짜의 정보를 담고 있는 dateSpan객체들도 선택해놓기(null값인지 확인)
      let dateSpan = document.querySelectorAll('.dateSpan');
      //div.dateSpan의 innerText의 값이 null이라면 날짜정보가 없는 td들이다. 따라서 null이 아닌 애들한테 onclick function을 걸어주자
      if (dateSpan[i].innerText != null) {
        let currentDate = document.querySelector('#selecDate').value; //년-월만 갖고 있는 #selecDate
        //ajax로 데이터를 줘야 하는데, 그 데이터는 해당 날짜여야 한다. 그 날짜의 값을 구해놓자.
        if (dateSpan[i].innerText < 10) { currentDate += '\-0' + dateSpan[i].innerText; }
        else { currentDate += '\-' + dateSpan[i].innerText; }
        //td를 선택했을 때, 선택한 날짜에 해당되는 정보를 보내서 그 값을 json객체로 받아오는 기능을 추가해주자.
        //ajax로 전송할 json data로 셋팅하자.
        let detailData = { 'currentDate': currentDate }
        dateTd[i].onclick = function () {
					event.stopPropagation();
          //일단 상세내역 데이터들 초기화 해줘야함.
          detailContextIncome.innerHTML = null;
          detailContextExpense.innerHTML = null;
					detailDateSpan.innerHTML = currentDate;
					document.querySelector('#insert_income_date').value = currentDate;
					document.querySelector('#insert_expense_date').value = currentDate;
          //이건 날짜별 수입 데이터들
          $.ajax({
            url: '/main/postIICA',
            type: 'post',
            data: detailData,
            success: function (data) {
              let iicaList = data;
              makeDetailIncomeDIV(iicaList);
              if(iicaList.length>0){putIncomeDataToUpdateForm(iicaList);}
              //이건 날짜별 지출 데이터들
              $.ajax({
                url: '/main/postEECA',
                type: 'post',
                data: detailData,
                success: function (data) {
                  let eecaList = data;
                  makeDetailExpenseDIV(eecaList);
                  if(eecaList.length>0){putExpenseDataToUpdateForm(eecaList);}
									//이건 날짜별 이체 데이터들
									$.ajax({
										url:'/main/postTAOMFAOMT',
										type:'post',
										data: detailData,
										success: function (data) {
											let taomfaomtList = data;
											makeDetailTransferDIV(taomfaomtList);
											if(taomfaomtList.length>0){putTransferDataToUpdateForm(taomfaomtList)}
										}
									});
								}
							});
            }
          });
        }
      }
    }
	}
	//업데이트 성공하면 화면에 뿌려주는 데이타를 갱신해주는 기능
	function refreshData(UpdateAndRefreshData){
		modal.classList.add('hidden');
		tbody.innerHTML=null;
		document.querySelector('#update_income_form').classList.add('hidden');
		document.querySelector('#update_expense_form').classList.add('hidden');
		document.querySelector('#update_transfer_form').classList.add('hidden');
		document.querySelector('#insert_income_form').reset();
		document.querySelector('#insert_expense_form').reset();
		// document.querySelector('#insert_transfer_form').reset();
		let cal = UpdateAndRefreshData.cal;
		let meecList = UpdateAndRefreshData.meecList;
		let miicList = UpdateAndRefreshData.miicList;
		let tbmList = UpdateAndRefreshData.tbmList;
		makecalendar(cal);
		if(miicList != null){showThisMonthIncome(miicList);}else{for(let i = 0; i < dateTd.length; i ++){
			let classStr;
			if(i < 10){classStr = '.di0'+i;}else{classStr = '.di'+i;}
			document.querySelector(classStr).innerHTML = null;
		}}
		if(meecList != null){showThisMonthExpense(meecList);}else{for(let i = 0; i < dateTd.length; i ++){
			let classStr;
			if(i < 10){classStr = '.de0'+i;}else{classStr = '.de'+i;}
			document.querySelector(classStr).innerHTML = null;
		}}
		showThisMonthTransfer(tbmList);
		let updateDate = document.querySelector('span.detailDate').innerText;
		document.querySelector('#insert_income_date').value = updateDate;
		document.querySelector('#insert_expense_date').value = updateDate;
		// document.querySelector('#insert_transfer_form').value = updateDate;
		let detailData = {"currentDate":updateDate};
		//이건 날짜별 수입 데이터들
		$.ajax({
			url: '/main/postIICA',
			type: 'post',
			data: detailData,
			success: function (data) {
				let iicaList = data;
				if(iicaList != null){makeDetailIncomeDIV(iicaList);putIncomeDataToUpdateForm(iicaList);}
				else{document.querySelector('div.detail_context_income').innerHTML = null;document.querySelector('span.detailSumI').innerText=0;}
				//이건 날짜별 지출 데이터들
				$.ajax({
					url: '/main/postEECA',
					type: 'post',
					data: detailData,
					success: function (data) {
						let eecaList = data;
						if(eecaList != null){makeDetailExpenseDIV(eecaList);putExpenseDataToUpdateForm(eecaList);}
						else{document.querySelector('div.detail_context_expense').innerHTML = null;document.querySelector('span.detailSumE').innerText=0}
						//이건 날짜별 이체 데이터들
						$.ajax({
							url:'/main/postTAOMFAOMT',
							type:'post',
							data: detailData,
							success: function (data) {
								let taomfaomtList = data;
								if(taomfaomtList!=null){makeDetailTransferDIV(taomfaomtList);putTransferDataToUpdateForm(taomfaomtList)}
								else{document.querySelector('div.detail_context_transfer').innerHTML = null;}
							}
						});
					}
				});
			}
		});
		let dateTd = document.querySelectorAll('.dateTd');
		put_MakeDetailDiv_to_dateTd(dateTd);
	}
	//인서트시, 딜리트 시 업데이트 해주는 기능
	function refreshDataWhenInsert(UpdateAndRefreshData){
		modal.classList.add('hidden');
		document.querySelector('#update_income_form').classList.add('hidden');
		document.querySelector('#update_expense_form').classList.add('hidden');
		document.querySelector('#insert_income_form').reset();
		document.querySelector('#insert_expense_form').reset();
		document.querySelector('#insert_income_date').value = document.querySelector('span.detailDate').innerText;
		document.querySelector('#insert_expense_date').value = document.querySelector('span.detailDate').innerText;
		tbody.innerHTML = null;
		let cal = UpdateAndRefreshData.cal;
		let meecList = UpdateAndRefreshData.meecList;
		let miicList = UpdateAndRefreshData.miicList;
		let iicaList = UpdateAndRefreshData.iicaList;
		let eecaList = UpdateAndRefreshData.eecaList;
		makecalendar(cal);
		let dateTd = document.querySelectorAll('.dateTd');
		if(miicList != null){showThisMonthIncome(miicList);}else{for(let i = 0; i < dateTd.length; i ++){
				let classStr;
				if(i < 10){classStr = '.di0'+i;}else{classStr = '.di'+i;}
				document.querySelector(classStr).innerHTML = null;
		}}
		if(meecList != null){showThisMonthExpense(meecList);}else{for(let i = 0; i < dateTd.length; i ++){
				let classStr;
				if(i < 10){classStr = '.de0'+i;}else{classStr = '.de'+i;}
				document.querySelector(classStr).innerHTML = null;
		}}
		if(iicaList != null){makeDetailIncomeDIV(iicaList); putIncomeDataToUpdateForm(iicaList);}else{document.querySelector('div.detail_context_income').innerHTML = null;document.querySelector('span.detailSumI').innerText=0;}
		if(eecaList != null){makeDetailExpenseDIV(eecaList); putExpenseDataToUpdateForm(eecaList);}else{document.querySelector('div.detail_context_expense').innerHTML = null;document.querySelector('span.detailSumE').innerText=0}
		put_MakeDetailDiv_to_dateTd(dateTd);
	}

	//################## DateLicker ####################
	let currentMonth = new Date().getUTCMonth()+1;
  currentMonth +='월로...';
  $("#selecDate").datepicker({
    changeYear: true,
    changeMonth: true,
    showButtonPanel: true,
    dateFormat: "yy-mm",
    showMonthAfterYear:true, 
    monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
    minDate: '-20Y',
    maxDate: '+100Y',
    closeText: "닫기",
    currentText: currentMonth,
    onChangeMonthYear: function(year, month) {
      if(month < 10) {document.querySelector('#selecDate').value = String(year)+'-0'+String(month);}
			else{document.querySelector('#selecDate').value = String(year)+'-'+String(month);}
			//날짜가 변경되었을 때, 날짜에 맞는 캘린더를 뿌려주는 기능
			let formData = $('#selecDate').eq(0).serialize();
			$.ajax({
				url: '/main/postCal',
				type: 'post',
				data: formData,
				success: function (cal) {
					makecalendar(cal);
					$.ajax({
						url: '/main/postMIIC',
						type: 'post',
						data: formData,
						success: function (miicList) {
							showThisMonthIncome(miicList);
						}
					});
					$.ajax({
						url: '/main/postMEEC',
						type: 'post',
						data: formData,
						success: function (meecList) {
							showThisMonthExpense(meecList);
						}
					});
					$.ajax({
						url: '/main/postTBM',
						type: 'post',
						data: formData,
						success: function (tbmList) {
							showThisMonthTransfer(tbmList);
						}
					});
					let dateTd = document.querySelectorAll('.dateTd');
					put_MakeDetailDiv_to_dateTd(dateTd);
				}
			});
    },
  });

	// 페이지 처음 load시, td에 이벤트 걸어준다.
	let dateTd = document.querySelectorAll('.dateTd');
	put_MakeDetailDiv_to_dateTd(dateTd);

  //이전 월 버튼을 선택하면, 이전 월에 해당하는 캘린더를 뿌려주는 기능
  prevMonth.addEventListener('click', function () {
    let curDate = new Date(selecDate.value);
    let curYear = curDate.getFullYear();
    let curMonth = curDate.getMonth() + 1;
    if (curMonth == 1) {
      curMonth = 13;
      curYear -= 1;
    }
    let prevDate = new Date(curYear + "-" + (curMonth - 1) + "-" + "10").toISOString().substring(0, 7);
    selecDate.value = prevDate;
    let formData = $('#selecDate').eq(0).serialize();
    $.ajax({
      url: '/main/postCal',
      type: 'post',
      data: formData,
      success: function (cal) {
        makecalendar(cal);
        $.ajax({
          url: '/main/postMIIC',
          type: 'post',
          data: formData,
          success: function (miicList) {
            showThisMonthIncome(miicList);
          }
        });
        //캘린더가 만들어진 후, 해당 월에 맞는 expense 자료를 가져와서 뿌려주는 기능
        $.ajax({
          url: '/main/postMEEC',
          type: 'post',
          data: formData,
          success: function (meecList) {
            showThisMonthExpense(meecList);
          }
				});
				$.ajax({
					url: '/main/postTBM',
					type: 'post',
					data: formData,
					success: function (tbmList) {
						showThisMonthTransfer(tbmList);
					}
				});
        //날짜별 걸어줄 이벤트 실행
        //테이블이 생성되고 나면 각 테이블마다 onclick function을 걸어주자
        let dateTd = document.querySelectorAll('.dateTd');
        put_MakeDetailDiv_to_dateTd(dateTd);
      }
    });
  });

  //다음 월 버튼을 선택하면, 다음 월에 해당하는 캘린더를 뿌려주는 기능
  nextMonth.addEventListener('click', function () {
    let curDate = new Date(selecDate.value);
    let curYear = curDate.getFullYear();
    let curMonth = curDate.getMonth() + 1;
    if (curMonth == 12) {
      curMonth = 0;
      curYear += 1;
    }
    let prevDate = new Date(curYear + "-" + (curMonth + 1) + "-" + "10").toISOString().substring(0, 7);
    selecDate.value = prevDate;
    let formData = $('#selecDate').eq(0).serialize();
    $.ajax({
      url: '/main/postCal',
      type: 'post',
      data: formData,
      success: function (cal) {
        makecalendar(cal);
        $.ajax({
          url: '/main/postMIIC',
          type: 'post',
          data: formData,
          success: function (miicList) {
            showThisMonthIncome(miicList);
          }
        });
        //캘린더가 만들어진 후, 해당 월에 맞는 expense 자료를 가져와서 뿌려주는 기능
        $.ajax({
          url: '/main/postMEEC',
          type: 'post',
          data: formData,
          success: function (meecList) {
            showThisMonthExpense(meecList);
          }
				});
				$.ajax({
					url: '/main/postTBM',
					type: 'post',
					data: formData,
					success: function (tbmList) {
						showThisMonthTransfer(tbmList);
					}
				});
        //날짜별 걸어줄 이벤트 실행
        //테이블이 생성되고 나면 각 테이블마다 onclick function을 걸어주자
        let dateTd = document.querySelectorAll('.dateTd');
        put_MakeDetailDiv_to_dateTd(dateTd);
      }
    });
	});
	/*############## 상단바 기능 ###############*/
	document.querySelector(".gomypage").onclick = function(){
		location.href="/member/mypage";
	}
	document.querySelector(".gologout").onclick = function(){
		location.href="/member/logout";
	}
	
/*########## grid_detail 관련 기능 ##########*/
	//업데이트 버튼 눌렀을 때 실행할 메서드 정의
	//수입 업데이트
	$('#update_income_form').on('submit', function() {
		let formData = $('#update_income_form').eq(0).serialize();
		$.ajax({
			url:'/main/postUpdateIncome',
			type:'post',
			data:formData,
			error:function() {
				alert('업데이트 하는 과정에서 오류가 발생했습니다.');
			},
			success:function(UR) {
				refreshData(UR);
			},error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
		});
		return false;
	});
	//지출 업데이트
	$('#update_expense_form').on('submit', function() {
		let formData = $('#update_expense_form').eq(0).serialize();
		$.ajax({
			url:'/main/postUpdateExpense',
			type:'post',
			data:formData,
			error:function() {
				alert('업데이트 하는 과정에서 오류가 발생했습니다.');
			},
			success:function(UR) {
				refreshData(UR);
			},error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
		});
		return false;
	});
	//이체 업데이트
	$('#update_transfer_form').on('submit', function() {
		let formData = $('#update_transfer_form').eq(0).serialize();
		$.ajax({
			url:'/main/postUpdateTransfer',
			type:'post',
			data:formData,
			success:function(UR) {
				refreshData(UR);
			},error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
		});
		return false;
	});
	// //수입 삭제버튼
	$('#delete_income_button').on('click', function(){
		let really = confirm('삭제하시겠습니까?');
		if(really == true) {
			let formData = $('#update_income_form').eq(0).serialize();
			$.ajax({
				url:'/main/postDeleteIncome',
				type:'post',
				data:formData,
				success:function(UR) {
					refreshData(UR);
				},error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
			});
		}
	});
	// //지출 삭제버튼
	$('#delete_expense_button').on('click', function(){
		let really = confirm('삭제하시겠습니까?');
		if(really == true) {
			let formData = $('#update_expense_form').eq(0).serialize();
			$.ajax({
				url:'/main/postDeleteExpense',
				type:'post',
				data:formData,
				success:function(UR) {
					refreshData(UR);
				},error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
			});
		}
	});
	$('#delete_transfer_button').on('click', function(){
		let really = confirm('삭제하시겠습니까?');
		if(really == true) {
			let formData = $('#update_transfer_form').eq(0).serialize();
			$.ajax({
				url:'/main/postDeleteTransfer',
				type:'post',
				data:formData,
				success:function(UR) {
					refreshData(UR);
				},error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
			});
		}
	});

/*########## grid_aside 관련 기능 ##########*/
  //오늘 날짜를 상수로 선언해놓기. yyyy-MM-dd 형식
  const todayString = new Date().toISOString().substring(0, 10);
  document.querySelector('#insert_income_date').value = todayString;
	document.querySelector('#insert_expense_date').value = todayString;

	//put_MakeDetailDiv_to_dateTd에 날짜를 클릭 시 insert창의 value를 해당 날짜로 바꿔주는 작업을 해놓았다.

	//insert income 버튼 클릭시 insert 이벤트
	$('#insert_income_form').on('submit', function() {
		let formData = $('#insert_income_form').eq(0).serialize();
		$.ajax({
			url:'/main/postInsertIncome',
			type:'post',
			data:formData,
			error:function() {
				alert('수입 정보 입력 도중 에러가 발생했습니다.');
			},
			success:function(UR){
				let insertDate = document.querySelector('#insert_income_date').value;
				document.querySelector('span.detailDate').innerText = insertDate;
				refreshDataWhenInsert(UR);
				document.querySelector('#insert_expense_date').value = insertDate;
				document.querySelector('#insert_income_date').value = insertDate;
			}
		});
		return false;
	});

	//insert expense 버튼 클릭시 insert 이벤트
	$('#insert_expense_form').on('submit', function() {
		let formData = $('#insert_expense_form').eq(0).serialize();
		$.ajax({
			url:'/main/postInsertExpense',
			type:'post',
			data:formData,
			error:function() {
				alert('지출 정보 입력 도중 에러가 발생했습니다.');
			},
			success:function(UR) {
				let insertDate = document.querySelector('#insert_expense_date').value;
				document.querySelector('span.detailDate').innerText = insertDate;
				refreshDataWhenInsert(UR);
				document.querySelector('#insert_expense_date').value = insertDate;
				document.querySelector('#insert_income_date').value = insertDate;
			}
		});
		return false;
	});
});