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
window.addEventListener('DOMContentLoaded', function () {
	//필요한 태그들 변수 선언해두었다.
	const modal = document.getElementById('modal');
	const updateIncomeForm = document.querySelector('#update_income_form');
	const updateIncomeCategoryIncome = document.querySelector('#update_income_category_income');
	const updateIncomeDate = document.querySelector('#update_income_date');
	const updateAssetsIncomeSelected = document.querySelector('#update_assets_income_selected');
	const updateIncomeCategorySelected = document.querySelector('#update_income_category_selected');
	const updateIncomeAmount = document.querySelector('#update_income_amount');
	const updateIncomeMemo = document.querySelector('#update_income_memo');
	const updateExpenseForm = document.querySelector('#update_expense_form');
	const updateExpenseCategoryExpense = document.querySelector('#update_expense_category_expense');
	const updateExpenseDate = document.querySelector('#update_expense_date');
	const updateAssetsExpenseSelected = document.querySelector('#update_assets_expense_selected');
	const updateExpenseCategorySelected = document.querySelector('#update_expense_category_selected');
	const updateExpenseAmount = document.querySelector('#update_expense_amount');
	const updateExpenseMemo = document.querySelector('#update_expense_memo');
	const updateExpenseId = document.querySelector('#update_expense_id');
	const updateIncomeId = document.querySelector('#update_income_id');
	//상세데이터를 뿌려야되는 div.detailContext, div.detailDate미리 설정
	const detailDateSpan = document.querySelector('span.detailDate');
	const detailContextIncome = document.querySelector('div.detail_context_income');
	const detailContextExpense = document.querySelector('div.detail_context_expense');
	//테이블이 생성되고 나면 각 테이블마다 onclick function을 걸어주자
	const dateTd = document.querySelectorAll('.dateTd');
	//날짜의 정보를 담고 있는 dateSpan객체들도 선택해놓기(null값인지 확인)
	const dateSpan = document.querySelectorAll('.dateSpan');
	
	function showUpdateIncomeAom(assetsId) {
		let jsonData = {"assetsId":assetsId};
		let updateAOMIncome = document.querySelector('#update_aom_income');
		$.ajax({
			url:'/main/postAOM',
			type:'post',
			data:jsonData,
			success:function(aomList) {
				console.dir(aomList);
				if(aomList == null) {updateAOMIncome.innerHTML = null;
				}else {
					let updateAOMStr;
					for(let i = 0; i < aomList.length; i++) {
						updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
					}
					updateAOMIncome.innerHTML = updateAOMStr;
				}
			}
		})
	}
	function showUpdateExpenseAom(assetsId) {
		let jsonData = {"assetsId":assetsId};
		let updateAOMIncome = document.querySelector('#update_aom_expense');
		$.ajax({
			url:'/main/postAOM',
			type:'post',
			data:jsonData,
			success:function(aomList) {
				console.dir(aomList);
				if(aomList == null) {updateAOMIncome.innerHTML = null;
				}else {
					let updateAOMStr;
					for(let i = 0; i < aomList.length; i++) {
						updateAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
					}
					updateAOMIncome.innerHTML = updateAOMStr;
				}
			}
		})
	}
	//상세데이터에 표시된 지출 아이템을 클릭하면 updateModal창을 열어주고, form안에 값을 넣어주는 메서드
	function putExpenseDataToUpdateForm(eecaList) {
		for (let i = 0; i < eecaList.length; i++) {
			let classStr = 'div.dl_expense' + i;
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
				showUpdateExpenseAom(eecaList[i].assetsId);
				updateExpenseCategorySelected.value = eecaList[i].ecId; //지출분류(value)
				updateExpenseCategorySelected.innerText = eecaList[i].ecName; //지출분류(name)
				updateExpenseAmount.value = eecaList[i].amount;//금액
				updateExpenseMemo.value = eecaList[i].memo;//내용
			});
		}
	}
	
	//상세데이터에 표시된 수입 아이템을 클릭하면 updateModal창을 열어주고, form안에 값을 넣어주는 메서드
	function putIncomeDataToUpdateForm(iicaList) {
		for (let i = 0; i < iicaList.length; i++) {
			let classStr = 'div.di_Income' + i;
			console.log(classStr);
			let detailItem = document.querySelector(classStr);
			console.dir(detailItem);
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
				showUpdateIncomeAom(iicaList[i].assetsId);
				updateIncomeCategorySelected.value = iicaList[i].icId; //수입분류(value)
				updateIncomeCategorySelected.innerText = iicaList[i].icName; //수입분류(name)
				updateIncomeAmount.value = iicaList[i].amount;//금액
				updateIncomeMemo.value = iicaList[i].memo;//내용
			});
		}
	}

	for (let i = 0; i < dateTd.length; i++) {
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
				//이건 날짜별 수입 데이터들
				$.ajax({
					url: '/main/postIICA',
					type: 'post',
					data: detailData,
					success: function (data) {
						let iicaList = data;
						let sumI = 0;
						//받은 상세데이터를 상세데이터란에 뿌려주자
						for (let i = 0; i < iicaList.length; i++) {
							sumI += iicaList[i].amount;
							let str = '<div class="di_Income' + i + ' detailItem"><div class=detailIcName><span class=detailIcName>' + iicaList[i].icName + '</span></div>';
							str += '<div class=detailEntry><span class=detailIMemo>' + iicaList[i].memo + '</span><br><span class=detailAName>' + iicaList[i].assetsName + '</span></div>';
							str += '<div class=detailIAmount><span class=detailIAmount>' + iicaList[i].amount + '</span></div></div>';
							detailContextIncome.innerHTML += str;
						}
						document.querySelector('span.detailSumI').innerHTML = sumI;
						if(iicaList!=null){putIncomeDataToUpdateForm(iicaList);}

						//이건 날짜별 지출 데이터들
						$.ajax({
							url: '/main/postEECA',
							type: 'post',
							data: detailData,
							success: function (data) {
								let eecaList = data;
								let sumE = 0;
								for (let i = 0; i < eecaList.length; i++) {
									sumE += eecaList[i].amount;
									let str = '<div class="dl_expense' + i + ' detailItem"><div class=detailEcName><span class=detailEcName>' + eecaList[i].ecName + '</span></div>';
									str += '<div class=detailEntry><span class=detailEMemo>' + eecaList[i].memo + '</span><br><span class=detailAName>' + eecaList[i].assetsName + '</span></div>';
									str += '<div class=detailEAmount><span class=detailEAmount>' + eecaList[i].amount + '</span></div></div>';
									detailContextExpense.innerHTML += str;
								}
								document.querySelector('span.detailSumE').innerHTML = sumE;
								
								if(eecaList!=null){putExpenseDataToUpdateForm(eecaList);}
							}
						});
					}
				});
			}
		}
	}

	//업데이트 버튼 눌렀을 때 실행할 메서드 정의
	const updateIncomeButton = document.querySelector('#update_income_button'); //update_income_form의 update버튼
	const updateExpenseButton = document.querySelector('#update_expense_button'); //expense update 버튼
	$('#update_income_form').on('submit', function() {
		let formData = $('#update_income_form').eq(0).serialize();
		$.ajax({
			url:'/main/postUpdateIncome',
			type:'post',
			data:formData,
			success:function(UR) {
				console.dir('ajax연결성공');
				console.dir(UR);
				if(UR.miicList != null){

				}
			}
		});
		return false;
	});
});