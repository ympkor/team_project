function iFSwitchIFtoEF() {
	document.querySelector('#update_income_category_switch_expense').classList.add('hidden');
	document.querySelector('#update_income_category').classList.remove('hidden');
}
function iFSwitchEFtoIF() {
	document.querySelector('#update_income_category_switch_expense').classList.remove('hidden');
	document.querySelector('#update_income_category').classList.add('hidden');
}
function eFSwitchIFtoEF() {
	document.querySelector('#update_expense_category_switch_income').classList.remove('hidden');
	document.querySelector('#update_expense_category').classList.add('hidden');
}
function eFSwitchEFtoIF() {
	document.querySelector('#update_expense_category_switch_income').classList.add('hidden');
	document.querySelector('#update_expense_category').classList.remove('hidden');
}
//수입이 체크되면 수입 폼을 보여주고 지출 폼은 hidden
function showIncomeForm() {
  document.querySelector('#insert_income_form').classList.remove('hidden');
  document.querySelector('#insert_expense_form').classList.add('hidden');
}
//지출이 체크되면 지출 폼을 보여주고 수입 폼은 hidden
function showExpenseForm() {
  document.querySelector('#insert_expense_form').classList.remove('hidden');
  document.querySelector('#insert_income_form').classList.add('hidden');
}
//상세데이터 수입 클릭시 업데이트 창을 보여주고 form안에 값을 넣어주는 기능
function putIncomeDataToUpdateFormBF(iicaList) {
	if(iicaList != null) {
		for(let i = 0; i < iicaList.length; i++){
			let classStr = 'div.di_income'+i;
			let detailItem = document.querySelector(classStr);
			detailItem.addEventListener('click', function() {
				event.stopPropagation();
				document.querySelector('#modal').classList.remove('hidden');
				document.querySelector('#update_income_category_income').checked = true;
				document.querySelector('#update_income_form').classList.remove('hidden');
				document.querySelector('#update_expense_form').classList.add('hidden');
				let thisDate;
				if(iicaList[i].incomeDate.monthValue < 10) {
					if(iicaList[i].incomeDate.dayOfMonth < 10) {thisDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else{thisDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
				}else{
					if(iicaList[i].incomeDate.dayOfMonth < 10){thisDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else {thisDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
				}

				document.querySelector('#update_income_id').value = iicaList[i].incomeId;
				document.querySelector('#update_income_date').value = thisDate;
				document.querySelector('#update_assets_income_selected').value = iicaList[i].assetsId;
				document.querySelector('#update_assets_income_selected').innerText = iicaList[i].assetsName;
				showUpdateIncomeAomBF(iicaList[i]);
				document.querySelector('#update_aom_income_origin').value = iicaList[i].memAssetId;
				// document.querySelector('#update_aom_income_selected').value = iicaList[i].memAssetId;
				// document.querySelector('#update_aom_income_selected').innerText = iicaList[i].aomName;
				document.querySelector('#update_income_category_selected').value = iicaList[i].icId;
				document.querySelector('#update_income_category_selected').innerText = iicaList[i].icName;
				document.querySelector('#update_income_amount_origin').value = iicaList[i].amount;
				document.querySelector('#update_income_amount').value = iicaList[i].amount;
				document.querySelector('#update_income_memo').value = iicaList[i].memo;
			});
		}
	}
}
//상세데이터 지출 클릭시 업데이트 창을 보여주고 form안에 값을 넣어주는 기능
function putExpenseDataToUpdateFormBF(eecaList) {
	if(eecaList != null){
			for(let i = 0; i < eecaList.length; i++) {
				let classStr = 'div.di_expense'+i;
				let detailItem = document.querySelector(classStr);
				detailItem.addEventListener('click', function() {
					event.stopPropagation();
					document.querySelector('#modal').classList.remove('hidden');
					document.querySelector('#update_expense_category_expense').checked = true;
				document.querySelector('#update_income_form').classList.add('hidden');
				document.querySelector('#update_expense_form').classList.remove('hidden');
				let thisDate;
				if(eecaList[i].expenseDate.monthValue < 10) {
					if(eecaList[i].expenseDate.dayOfMonth < 10) {thisDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
				}else{thisDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
			}else{
				if(eecaList[i].expenseDate.dayOfMonth < 10){thisDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
					}else {thisDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
				}
				document.querySelector('#update_expense_id').value = eecaList[i].expenseId;
				document.querySelector('#update_expense_date').value = thisDate;
				document.querySelector('#update_assets_expense_selected').value = eecaList[i].assetsId;
				document.querySelector('#update_assets_expense_selected').innerText = eecaList[i].assetsName;
				showUpdateExpenseAomBF(eecaList[i]);
				document.querySelector('#update_aom_expense_origin').value = eecaList[i].memAssetId;
				// document.querySelector('#update_aom_expense_selected').value = eecaList[i].memAssetId;
				// document.querySelector('#update_aom_expense_selected').innerText = eecaList[i].aomName;
				document.querySelector('#update_expense_category_selected').value = eecaList[i].ecId;
				document.querySelector('#update_expense_category_selected').innerText = eecaList[i].ecName;
				document.querySelector('#update_expense_amount_origin').value = eecaList[i].amount;
				document.querySelector('#update_expense_amount').value = eecaList[i].amount;
				document.querySelector('#update_expense_memo').value = eecaList[i].memo;
			});
		}
	}
}
//수입 업데이트 폼에서 자산카테고리 변경시 해당 카테고리중에서 보유중인 자산 보여주기
function showUpdateIncomeAomBF(iica) {
	if(typeof(iica) === 'number'){
		let jsonData = {"assetsId":iica};
		let updateAOMIncome = document.querySelector('#update_aom_income');
		$.ajax({
			url:'/main/postAOM',
			type:'post',
			data:jsonData,
			success:function(aomList) {
				let updateAOMStr = '<option id="update_aom_income_selected" value="" selected disabled>선택하세요</option>';
				if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
				}else {
					for(let i = 0; i < aomList.length; i++) {
						updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
					}
					updateAOMIncome.innerHTML = updateAOMStr;
				}
			}
		});
	}else{
		let jsonData = {"assetsId":iica.assetsId};
		let updateAOMIncome = document.querySelector('#update_aom_income');
		$.ajax({
			url:'/main/postAOM',
			type:'post',
			data:jsonData,
			success:function(aomList) {
				let updateAOMStr = '<option id="update_aom_income_selected" value='+iica.memAssetId+' selected>'+iica.aomName+'</option>';
				if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
				}else {
					for(let i = 0; i < aomList.length; i++) {
						if(iica.memAssetId != aomList[i].memAssetId){updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";}
					}
					updateAOMIncome.innerHTML = updateAOMStr;
				}
			}
		});
	}
}
//지출 업데이트 폼에서 자산카테고리 변경 시 해당 자산중에서 보유중인 자산명 보여주기
function showUpdateExpenseAomBF(eeca) {
	if(typeof(eeca) === 'number'){
		let jsonData = {"assetsId":eeca};
		let updateAOMIncome = document.querySelector('#update_aom_expense');
		$.ajax({
			url:'/main/postAOM',
			type:'post',
			data:jsonData,
			success:function(aomList) {
				let updateAOMStr = '<option id="update_aom_expense_selected" value="" selected disabled>선택하세요</option>';
				if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
				}else {
					for(let i = 0; i < aomList.length; i++) {
						updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
					}
					updateAOMIncome.innerHTML = updateAOMStr;
				}
			}
		});
	}else{
		let jsonData = {"assetsId":eeca.assetsId};
		let updateAOMIncome = document.querySelector('#update_aom_expense');
		$.ajax({
			url:'/main/postAOM',
			type:'post',
			data:jsonData,
			success:function(aomList) {
				let updateAOMStr = '<option id="update_aom_expense_selected" value='+eeca.memAssetId+' selected>'+eeca.aomName+'</option>';
				if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
				}else {
					for(let i = 0; i < aomList.length; i++) {
						if(eeca.memAssetId != aomList[i].memAssetId) {updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";}
					}
					updateAOMIncome.innerHTML = updateAOMStr;
				}
			}
		});
	}
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
//수입 상세란의 삭제버튼에 이벤트를 걸어주는 기능
function addEvnetToIncomeDeleteButtonBF(iicaList) {
	if(iicaList != null) {
			for(let i = 0; i < iicaList.length; i++) {
				let button = 'button.delete_income_button'+i;
				document.querySelector(button).addEventListener('click', function() {
					event.stopPropagation();
					document.querySelector('#modal').classList.remove('hidden');
					document.querySelector('#delete_income_form').classList.remove('hidden');
					document.querySelector('#delete_income_id').value = iicaList[i].incomeId;
					document.querySelector('#delete_income_memAssetId_selected').value = iicaList[i].memAssetId;
					document.querySelector('#delete_income_memAssetId_selected').innerText = iicaList[i].aomName;
					let incomeDate;
					if(iicaList[i].incomeDate.monthValue < 10) {
						if(iicaList[i].incomeDate.dayOfMonth < 10) {incomeDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else{incomeDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
				}else{
					if(iicaList[i].incomeDate.dayOfMonth < 10){incomeDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else {incomeDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
				}
				document.querySelector('#delete_income_date').value = incomeDate;
				document.querySelector('#delete_income_assets_id_selected').value = iicaList[i].assetsId;
				document.querySelector('#delete_income_assets_id_selected').innerText = iicaList[i].assetsName;
				document.querySelector('#delete_income_category_id_selected').value = iicaList[i].icId;
				document.querySelector('#delete_income_category_id_selected').innerText = iicaList[i].icName;
				document.querySelector('#delete_income_amount').value = iicaList[i].amount;
				document.querySelector('#delete_income_memo').value = iicaList[i].memo;
			});
		}
	}
}
//지출 상세란의 삭제버튼에 이벤트를 걸어주는 기능
function addEvnetToExpenseDeleteButtonBF(eecaList) {
	if(eecaList != null) {
		for(let i = 0; i < eecaList.length; i++) {
			let button = 'button.delete_expense_button'+i;
			document.querySelector(button).addEventListener('click', function() {
				event.stopPropagation();
				document.querySelector('#modal').classList.remove('hidden');
				document.querySelector('#delete_expense_form').classList.remove('hidden');
				document.querySelector('#delete_expense_id').value = eecaList[i].expenseId;
				document.querySelector('#delete_expense_memAssetId_selected').value = eecaList[i].memAssetId;
				document.querySelector('#delete_expense_memAssetId_selected').innerText = eecaList[i].aomName;
				let expenseDate;
				if(eecaList[i].expenseDate.monthValue < 10) {
						if(eecaList[i].expenseDate.dayOfMonth < 10) {expenseDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
					}else{expenseDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
					}else{
						if(eecaList[i].expenseDate.dayOfMonth < 10){expenseDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
					}else {expenseDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
				}
				console.log(expenseDate);
				document.querySelector('#delete_expense_date').value = expenseDate;
				document.querySelector('#delete_expense_assets_id_selected').value = eecaList[i].assetsId;
				document.querySelector('#delete_expense_assets_id_selected').innerText = eecaList[i].assetsName;
				document.querySelector('#delete_expense_category_id_selected').value = eecaList[i].ecId;
				document.querySelector('#delete_expense_category_id_selected').innerText = eecaList[i].ecName;
				document.querySelector('#delete_expense_amount').value = eecaList[i].amount;
				document.querySelector('#delete_expense_memo').value = eecaList[i].memo;
			});
		}
	}
}

/*################# 페이지가 모두 로드 된 이후 사용될 기능들 ################*/
window.addEventListener('DOMContentLoaded', function () {
  //필요한 태그들 변수 선언해두었다.
	const modal = document.getElementById('modal');
	const updateIncomeForm = document.querySelector('#update_income_form');
	const updateIncomeDate = document.querySelector('#update_income_date');
	const updateAssetsIncomeSelected = document.querySelector('#update_assets_income_selected');
	const updateIncomeCategorySelected = document.querySelector('#update_income_category_selected');
	const updateIncomeCategoryIncome = document.querySelector('#update_income_category_income');
	const updateExpenseCategoryExpense = document.querySelector('#update_expense_category_expense');
	const updateIncomeAmount = document.querySelector('#update_income_amount');
	const updateIncomeMemo = document.querySelector('#update_income_memo');
	const updateExpenseForm = document.querySelector('#update_expense_form');
	const updateExpenseDate = document.querySelector('#update_expense_date');
	const updateAssetsExpenseSelected = document.querySelector('#update_assets_expense_selected');
	const updateExpenseCategorySelected = document.querySelector('#update_expense_category_selected');
	const updateExpenseAmount = document.querySelector('#update_expense_amount');
  const updateExpenseMemo = document.querySelector('#update_expense_memo');
  const updateExpenseId = document.querySelector('#update_expense_id');
	const updateIncomeId = document.querySelector('#update_income_id');
  const detailContextIncome = document.querySelector('div.detail_context_income');
  const detailContextExpense = document.querySelector('div.detail_context_expense');
  const selecDate = document.querySelector('#selecDate');
  const prevMonth = document.querySelector("#prevMonth");
  const nextMonth = document.querySelector('#nextMonth');
  const tbody = document.querySelector("#tbody");
    
  //수입 업데이트 폼에서 자산카테고리 변경시 해당 카테고리중에서 보유중인 자산 보여주기
  function showUpdateIncomeAom(iica) {
		if(typeof(iica) === 'number'){
			let jsonData = {"assetsId":iica};
			let updateAOMIncome = document.querySelector('#update_aom_income');
			$.ajax({
				url:'/main/postAOM',
				type:'post',
				data:jsonData,
				success:function(aomList) {
					let updateAOMStr = '<option id="update_aom_income_selected" value="" selected disabled>선택하세요</option>';
					if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
					}else {
						for(let i = 0; i < aomList.length; i++) {
							updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
						}
						updateAOMIncome.innerHTML = updateAOMStr;
					}
				}
			});
		}else{
			let jsonData = {"assetsId":iica.assetsId};
			let updateAOMIncome = document.querySelector('#update_aom_income');
			$.ajax({
				url:'/main/postAOM',
				type:'post',
				data:jsonData,
				success:function(aomList) {
					let updateAOMStr = '<option id="update_aom_income_selected" value='+iica.memAssetId+' selected>'+iica.aomName+'</option>';
					if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
					}else {
						for(let i = 0; i < aomList.length; i++) {
							if(iica.memAssetId != aomList[i].memAssetId){updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";}
						}
						updateAOMIncome.innerHTML = updateAOMStr;
					}
				}
			});
		}
  }
  //지출 업데이트 폼에서 자산카테고리 변경 시 해당 자산중에서 보유중인 자산명 보여주기
	function showUpdateExpenseAom(eeca) {
		if(typeof(eeca) === 'number'){
			let jsonData = {"assetsId":eeca};
			let updateAOMIncome = document.querySelector('#update_aom_expense');
			$.ajax({
				url:'/main/postAOM',
				type:'post',
				data:jsonData,
				success:function(aomList) {
					let updateAOMStr = '<option id="update_aom_expense_selected" value="" selected disabled>선택하세요</option>';
					if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
					}else {
						for(let i = 0; i < aomList.length; i++) {
							updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
						}
						updateAOMIncome.innerHTML = updateAOMStr;
					}
				}
			});
		}else{
			let jsonData = {"assetsId":eeca.assetsId};
			let updateAOMIncome = document.querySelector('#update_aom_expense');
			$.ajax({
				url:'/main/postAOM',
				type:'post',
				data:jsonData,
				success:function(aomList) {
					let updateAOMStr = '<option id="update_aom_expense_selected" value='+eeca.memAssetId+' selected>'+eeca.aomName+'</option>';
					if(aomList == null) {updateAOMIncome.innerHTML = updateAOMStr;
					}else {
						for(let i = 0; i < aomList.length; i++) {
							if(eeca.memAssetId != aomList[i].memAssetId) {updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";}
						}
						updateAOMIncome.innerHTML = updateAOMStr;
					}
				}
			});
		}
	}
	//상세데이터에 표시된 지출 아이템을 클릭하면 updateModal창을 열어주고, form안에 값을 넣어주는 메서드
	function putExpenseDataToUpdateForm(eecaList) {
		for (let i = 0; i < eecaList.length; i++) {
			let classStr = 'div.di_expense' + i;
			let detailItem = document.querySelector(classStr);
			detailItem.addEventListener('click', function() {
				//상세데이터가 뿌려지면 상세데이터를 클릭시 그 데이터를 수정할 수 있는 이벤트를 걸어주자
				//모달창을 보여주고
				modal.classList.remove('hidden');
				//모달창의 수입 박스를 체크해주고, update_income_form의 hidden클래스를 없애준다.
				updateExpenseCategoryExpense.checked = true;
				updateExpenseForm.classList.remove('hidden');
				updateIncomeForm.classList.add('hidden');
				//form안의 input창들의 value에 선택한 날짜의 value를 넣어주자.
				let thisDate;
				if(eecaList[i].expenseDate.monthValue < 10) {
					if(eecaList[i].expenseDate.dayOfMonth < 10){thisDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
					}else{thisDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
				}else{
					if(eecaList[i].expenseDate.dayOfMonth < 10){thisDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
					}else{thisDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;}
				}
				updateExpenseId.value = eecaList[i].expenseId;
				updateExpenseDate.value = thisDate; //날짜
				updateAssetsExpenseSelected.value = eecaList[i].assetsId; //자산(value)
				updateAssetsExpenseSelected.innerText = eecaList[i].assetsName; //자산(name)
				showUpdateExpenseAom(eecaList[i]);
				document.querySelector('#update_aom_expense_origin').value = eecaList[i].memAssetId;
				updateExpenseCategorySelected.value = eecaList[i].ecId; //지출분류(value)
				updateExpenseCategorySelected.innerText = eecaList[i].ecName; //지출분류(name)
				document.querySelector('#update_expense_amount_origin').value = eecaList[i].amount;
				updateExpenseAmount.value = eecaList[i].amount;//금액
				updateExpenseMemo.value = eecaList[i].memo;//내용
			});
		}
	}
	//상세데이터에 표시된 수입 아이템을 클릭하면 updateModal창을 열어주고, form안에 값을 넣어주는 메서드
	function putIncomeDataToUpdateForm(iicaList) {
		for (let i = 0; i < iicaList.length; i++) {
			let classStr = 'div.di_income' + i;
			let detailItem = document.querySelector(classStr);
			detailItem.addEventListener('click', function () {
				//모달창을 보여주고
				modal.classList.remove('hidden');
				//모달창의 수입 박스를 체크해주고, update_income_form의 hidden클래스를 없애준다.
				updateIncomeCategoryIncome.checked = true;
				updateIncomeForm.classList.remove('hidden');
				updateExpenseForm.classList.add('hidden');
				//form안의 input창들의 value에 선택한 날짜의 value를 넣어주자.
				let thisDate;
				if(iicaList[i].incomeDate.monthValue < 10) {
					if(iicaList[i].incomeDate.dayOfMonth < 10) {thisDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else{thisDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
				}else{
					if(iicaList[i].incomeDate.dayOfMonth < 10){thisDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else {thisDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
				}
				updateIncomeId.value = iicaList[i].incomeId;
				updateIncomeDate.value = thisDate; //날짜
				updateAssetsIncomeSelected.value = iicaList[i].assetsId; //자산(value)
				updateAssetsIncomeSelected.innerText = iicaList[i].assetsName; //자산(name)
				showUpdateIncomeAom(iicaList[i]);
				document.querySelector('#update_aom_income_origin').value = iicaList[i].memAssetId;
				updateIncomeCategorySelected.value = iicaList[i].icId; //수입분류(value)
				updateIncomeCategorySelected.innerText = iicaList[i].icName; //수입분류(name)
				document.querySelector('#update_income_amount_origin').value = iicaList[i].amount;
				updateIncomeAmount.value = iicaList[i].amount;//금액
				updateIncomeMemo.value = iicaList[i].memo;//내용
			});
		}
  }
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
	//수입 상세란의 삭제버튼에 이벤트를 걸어주는 기능
	function addEvnetToIncomeDeleteButton(iicaList) {
		for(let i = 0; i < iicaList.length; i++) {
			let button = 'button.delete_income_button'+i;
			document.querySelector(button).addEventListener('click', function() {
				event.stopPropagation();
				document.querySelector('#modal').classList.remove('hidden');
				document.querySelector('#delete_income_form').classList.remove('hidden');
				document.querySelector('#delete_income_id').value = iicaList[i].incomeId;
				document.querySelector('#delete_income_memAssetId_selected').value = iicaList[i].memAssetId;
				document.querySelector('#delete_income_memAssetId_selected').innerText = iicaList[i].aomName;
				let incomeDate;
				if(iicaList[i].incomeDate.monthValue < 10) {
						if(iicaList[i].incomeDate.dayOfMonth < 10) {incomeDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else{incomeDate = iicaList[i].incomeDate.year+'\-0'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
					}else{
						if(iicaList[i].incomeDate.dayOfMonth < 10){incomeDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-0'+iicaList[i].incomeDate.dayOfMonth;
					}else {incomeDate = iicaList[i].incomeDate.year+'\-'+iicaList[i].incomeDate.monthValue+'\-'+iicaList[i].incomeDate.dayOfMonth;}
				}
				console.log(incomeDate);
				document.querySelector('#delete_income_date').value = incomeDate;
				console.log(document.querySelector('#delete_income_date').value);
				document.querySelector('#delete_income_assets_id_selected').value = iicaList[i].assetsId;
				document.querySelector('#delete_income_assets_id_selected').innerText = iicaList[i].assetsName;
				document.querySelector('#delete_income_category_id_selected').value = iicaList[i].icId;
				document.querySelector('#delete_income_category_id_selected').innerText = iicaList[i].icName;
				document.querySelector('#delete_income_amount').value = iicaList[i].amount;
				document.querySelector('#delete_income_memo').value = iicaList[i].memo;
			});
		}
	}
	//지출 상세란의 삭제버튼에 이벤트를 걸어주는 기능
	function addEvnetToExpenseDeleteButton(eecaList) {
		for(let i = 0; i < eecaList.length; i++) {
			let button = 'button.delete_expense_button'+i;
			document.querySelector(button).addEventListener('click', function() {
				event.stopPropagation();
				document.querySelector('#modal').classList.remove('hidden');
				document.querySelector('#delete_expense_form').classList.remove('hidden');
				document.querySelector('#delete_expense_id').value = eecaList[i].expenseId;
				document.querySelector('#delete_expense_memAssetId_selected').value = eecaList[i].memAssetId;
				document.querySelector('#delete_expense_memAssetId_selected').innerText = eecaList[i].aomName;
				let expenseDate;
				if(eecaList[i].expenseDate.monthValue < 10) {
						if(eecaList[i].expenseDate.dayOfMonth < 10) {expenseDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
					}else{expenseDate = eecaList[i].expenseDate.year+'\-0'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
					}else{
						if(eecaList[i].expenseDate.dayOfMonth < 10){expenseDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-0'+eecaList[i].expenseDate.dayOfMonth;
					}else {expenseDate = eecaList[i].expenseDate.year+'\-'+eecaList[i].expenseDate.monthValue+'\-'+eecaList[i].expenseDate.dayOfMonth;}
				}
				console.log(expenseDate);
				document.querySelector('#delete_expense_date').value = expenseDate;
				console.log(document.querySelector('#delete_expense_date').value);
				document.querySelector('#delete_expense_assets_id_selected').value = eecaList[i].assetsId;
				document.querySelector('#delete_expense_assets_id_selected').innerText = eecaList[i].assetsName;
				document.querySelector('#delete_expense_category_id_selected').value = eecaList[i].ecId;
				document.querySelector('#delete_expense_category_id_selected').innerText = eecaList[i].ecName;
				document.querySelector('#delete_expense_amount').value = eecaList[i].amount;
				document.querySelector('#delete_expense_memo').value = eecaList[i].memo;
			});
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
				str += '<div class=detailIAmount><span class=detailIAmount>' + iicaList[i].amount + '</span></div><div class=delete_income_button><button type=button class="delete_income_button'+i+'">삭제</button></div></div>';
			}
			detailContextIncome.innerHTML = str;
			addEvnetToIncomeDeleteButton(iicaList);
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
				str += '<div class=detailEAmount><span class=detailEAmount>' + eecaList[i].amount + '</span></div><div class=delete_expense_button><button type=button class="delete_expense_button'+i+'">삭제</button></div></div>';
			}
			detailContextExpense.innerHTML = str;
			addEvnetToExpenseDeleteButton(eecaList);
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
		document.querySelector('#delete_income_form').classList.add('hidden');
		document.querySelector('#delete_expense_form').classList.add('hidden');
		document.querySelector('#update_income_form').classList.add('hidden');
		document.querySelector('#update_expense_form').classList.add('hidden');
		document.querySelector('#insert_income_form').reset();
		document.querySelector('#insert_expense_form').reset();
		let cal = UpdateAndRefreshData.cal;
		let meecList = UpdateAndRefreshData.meecList;
		let miicList = UpdateAndRefreshData.miicList;
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
		document.querySelector('#insert_income_date').value = updateDate;
		document.querySelector('#insert_expense_date').value = updateDate;
		let updateDate = document.querySelector('span.detailDate').innerText;
		let detailData = {"currentDate":updateDate};
		//이건 날짜별 수입 데이터들
		$.ajax({
			url: '/main/postIICA',
			type: 'post',
			data: detailData,
			success: function (data) {
				let iicaList = data;
				if(iicaList != null){makeDetailIncomeDIV(iicaList);putIncomeDataToUpdateForm(iicaList);}else{document.querySelector('div.detail_context_income').innerHTML = null;document.querySelector('span.detailSumI').innerText=0;}
				//이건 날짜별 지출 데이터들
				$.ajax({
					url: '/main/postEECA',
					type: 'post',
					data: detailData,
					success: function (data) {
						let eecaList = data;
						if(eecaList != null){makeDetailExpenseDIV(eecaList);putExpenseDataToUpdateForm(eecaList);}else{document.querySelector('div.detail_context_expense').innerHTML = null;document.querySelector('span.detailSumE').innerText=0}
					}
				});
			}
		});
		let dateTd = document.querySelectorAll('.dateTd');
		put_MakeDetailDiv_to_dateTd(dateTd);
	}
	//인서트시에 업데이트 해주는 기능
	function refreshDataWhenInsert(UpdateAndRefreshData){
		modal.classList.add('hidden');
		document.querySelector('#delete_income_form').classList.add('hidden');
		document.querySelector('#delete_expense_form').classList.add('hidden');
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
	//Income데이터 삭제 기능
	function confirmDeleteIncome(really) {
		if(really == true){
			let formData = $('#delete_income_form').eq(0).serialize();
			$.ajax({
				url:'/main/postDeleteIncome',
				type:'post',
				data:formData,
				success:function(UR) {
					refreshDataWhenInsert(UR);
				},
				error:function(e) {
					alert('수입 내역을 삭제하는 도중 오류가 발생했습니다.');
				}
			});
		}
	}
	//Expense데이터 삭제 기능
	function confirmDeleteExpense(really) {
		if(really == true){
			let formData = $('#delete_expense_form').eq(0).serialize();
			$.ajax({
				url:'/main/postDeleteExpense',
				type:'post',
				data:formData,
				success:function(UR) {
					refreshDataWhenInsert(UR);
				},
				error:function(e) {
					alert('지출 내역을 삭제하는 도중 오류가 발생했습니다.');
				}
			});
		}
	}
	//데이트picker
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
					//캘린더가 만들어진 후, 해당 월에 맞는 expense 자료를 가져와서 뿌려주는 기능
					$.ajax({
						url: '/main/postMEEC',
						type: 'post',
						data: formData,
						success: function (meecList) {
							showThisMonthExpense(meecList);
						}
					});
					//날짜별 걸어줄 이벤트 실행
					//테이블이 생성되고 나면 각 테이블마다 onclick function을 걸어주자
					let dateTd = document.querySelectorAll('.dateTd');
					put_MakeDetailDiv_to_dateTd(dateTd);
				}
			});
    },
  });

	// 페이지 처음 load시, td에 이벤트 걸어준다.
	let dateTd = document.querySelectorAll('.dateTd');
	put_MakeDetailDiv_to_dateTd(dateTd);

/*########## grid_main 관련 기능 ##########*/
  //월을 변경하면, 해당 월에 맞는 날짜로 Calendar객체를 생성후 받아와서, 뿌려주는기능
  selecDate.addEventListener('change', function () {
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
        //캘린더가 만들어진 후, 해당 월에 맞는 expense 자료를 가져와서 뿌려주는 기능
        $.ajax({
          url: '/main/postMEEC',
          type: 'post',
          data: formData,
          success: function (meecList) {
            showThisMonthExpense(meecList);
          }
        });
        //날짜별 걸어줄 이벤트 실행
        //테이블이 생성되고 나면 각 테이블마다 onclick function을 걸어주자
        let dateTd = document.querySelectorAll('.dateTd');
        put_MakeDetailDiv_to_dateTd(dateTd);
      }
    });
  });

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
			}
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
			}
		});
		return false;
	});
	//수입 삭제버튼
	$('#delete_income_form').on('submit', function() {
		let really = confirm('삭제하시겠습니까');
		event.stopPropagation();
		confirmDeleteIncome(really);
		return false;
	});
	//지출 삭제버튼
	$('#delete_expense_form').on('submit', function() {
		let really = confirm('삭제하시겠습니까?');
		event.stopPropagation();
		confirmDeleteExpense(really);
		return false;
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