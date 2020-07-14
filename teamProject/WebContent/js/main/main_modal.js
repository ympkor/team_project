//수입 업데이트 폼에서 수입 카테고리 클릭 시
function updateIncomeFormSwitchToIncome() {
  document.querySelector('#update_assets_income_memAssetIdFrom_label').innerText = '자산';
  document.querySelector('#update_assets_income_memAssetIdTo').classList.add('hidden');
  document.querySelector('#update_assets_income_memAssetIdTo_label').classList.add('hidden');
  document.querySelector('#update_income_expense_category').classList.add('hidden');
  document.querySelector('#update_income_expense_category_label').classList.add('hidden');
  document.querySelector('#update_income_income_category_label').classList.remove('hidden');
  document.querySelector('#update_income_income_category').classList.remove('hidden');
  document.querySelector('#update_income_income_category').required = true;
  document.querySelector('#update_income_expense_category').required = false;
  document.querySelector('#update_assets_income_memAssetIdTo').required = false;
}
//수입 업데이트 폼에서 지출 카테고리 클릭 시
function updateIncomeFormSwitchToExpense() {
  document.querySelector('#update_assets_income_memAssetIdFrom_label').innerText = '자산';
  document.querySelector('#update_assets_income_memAssetIdTo').classList.add('hidden');
  document.querySelector('#update_assets_income_memAssetIdTo_label').classList.add('hidden');
  document.querySelector('#update_income_expense_category').classList.remove('hidden');
  document.querySelector('#update_income_expense_category_label').classList.remove('hidden');
  document.querySelector('#update_income_income_category_label').classList.add('hidden');
  document.querySelector('#update_income_income_category').classList.add('hidden');
  document.querySelector('#update_income_income_category').required = false;
  document.querySelector('#update_income_expense_category').required = true;
  document.querySelector('#update_assets_income_memAssetIdTo').required = false;
}
//수입 업데이트 폼에서 이체 카테고리 클릭 시.
function updateIncomeFormSwitchToTransfer() {
  document.querySelector('#update_assets_income_memAssetIdFrom_label').innerText = '출금';
  document.querySelector('#update_assets_income_memAssetIdTo').classList.remove('hidden');
  document.querySelector('#update_assets_income_memAssetIdTo_label').classList.remove('hidden');
  document.querySelector('#update_income_expense_category').classList.add('hidden');
  document.querySelector('#update_income_expense_category_label').classList.add('hidden');
  document.querySelector('#update_income_income_category_label').classList.add('hidden');
  document.querySelector('#update_income_income_category').classList.add('hidden');
  document.querySelector('#update_income_income_category').required = false;
  document.querySelector('#update_income_expense_category').required = false;
  document.querySelector('#update_assets_income_memAssetIdTo').required = true;
}
//지출 업데이트 폼에서 수입 카테고리 클릭 시
function updateExpenseFormSwitchToIncome() {
  document.querySelector('#update_assets_expense_memAssetIdFrom_label').innerText = '자산';
  document.querySelector('#update_assets_expense_memAssetIdTo').classList.add('hidden');
  document.querySelector('#update_assets_expense_memAssetIdTo_label').classList.add('hidden');
  document.querySelector('#update_expense_expense_category').classList.add('hidden');
  document.querySelector('#update_expense_expense_category_label').classList.add('hidden');
  document.querySelector('#update_expense_income_category_label').classList.remove('hidden');
  document.querySelector('#update_expense_income_category').classList.remove('hidden');
  document.querySelector('#update_expense_income_category').required = true;
  document.querySelector('#update_expense_expense_category').required = false;
  document.querySelector('#update_assets_expense_memAssetIdTo').required = false;
}
//지출 업데이트 폼에서 지출 카테고리 클릭 시
function updateExpenseFormSwitchToExpense() {
  document.querySelector('#update_assets_expense_memAssetIdFrom_label').innerText = '자산';
  document.querySelector('#update_assets_expense_memAssetIdTo').classList.add('hidden');
  document.querySelector('#update_assets_expense_memAssetIdTo_label').classList.add('hidden');
  document.querySelector('#update_expense_expense_category').classList.remove('hidden');
  document.querySelector('#update_expense_expense_category_label').classList.remove('hidden');
  document.querySelector('#update_expense_income_category_label').classList.add('hidden');
  document.querySelector('#update_expense_income_category').classList.add('hidden');
  document.querySelector('#update_expense_income_category').required = false;
  document.querySelector('#update_expense_expense_category').required = true;
  document.querySelector('#update_assets_expense_memAssetIdTo').required = false;
}
//지출 업데이트 폼에서 이체 카테고리 클릭 시.
function updateExpenseFormSwitchToTransfer() {
  document.querySelector('#update_assets_expense_memAssetIdFrom_label').innerText = '출금';
  document.querySelector('#update_assets_expense_memAssetIdTo').classList.remove('hidden');
  document.querySelector('#update_assets_expense_memAssetIdTo_label').classList.remove('hidden');
  document.querySelector('#update_expense_expense_category').classList.add('hidden');
  document.querySelector('#update_expense_expense_category_label').classList.add('hidden');
  document.querySelector('#update_expense_income_category_label').classList.add('hidden');
  document.querySelector('#update_expense_income_category').classList.add('hidden');
  document.querySelector('#update_expense_income_category').required = false;
  document.querySelector('#update_expense_expense_category').required = false;
  document.querySelector('#update_assets_expense_memAssetIdTo').required = true;
}
//이체 업데이트 폼에서 수입 카테고리 클릭 시
function updateTransferFormSwitchToIncome() {
  document.querySelector('#update_assets_transfer_memAssetIdFrom_label').innerText = '자산';
  document.querySelector('#update_assets_transfer_memAssetIdTo').classList.add('hidden');
  document.querySelector('#update_assets_transfer_memAssetIdTo_label').classList.add('hidden');
  document.querySelector('#update_transfer_expense_category').classList.add('hidden');
  document.querySelector('#update_transfer_expense_category_label').classList.add('hidden');
  document.querySelector('#update_transfer_income_category_label').classList.remove('hidden');
  document.querySelector('#update_transfer_income_category').classList.remove('hidden');
  document.querySelector('#update_transfer_income_category').required = true;
  document.querySelector('#update_transfer_expense_category').required = false;
  document.querySelector('#update_assets_transfer_memAssetIdTo').required = false;
}
//이체 업데이트 폼에서 지출 카테고리 클릭 시
function updateTransferFormSwitchToExpense() {
  document.querySelector('#update_assets_transfer_memAssetIdFrom_label').innerText = '자산';
  document.querySelector('#update_assets_transfer_memAssetIdTo').classList.add('hidden');
  document.querySelector('#update_assets_transfer_memAssetIdTo_label').classList.add('hidden');
  document.querySelector('#update_transfer_expense_category').classList.remove('hidden');
  document.querySelector('#update_transfer_expense_category_label').classList.remove('hidden');
  document.querySelector('#update_transfer_income_category_label').classList.add('hidden');
  document.querySelector('#update_transfer_income_category').classList.add('hidden');
  document.querySelector('#update_transfer_income_category').required = false;
  document.querySelector('#update_transfer_expense_category').required = true;
  document.querySelector('#update_assets_transfer_memAssetIdTo').required = false;
}
//이체 업데이트 폼에서 이체 카테고리 클릭 시.
function updateTransferFormSwitchToTransfer() {
  document.querySelector('#update_assets_transfer_memAssetIdFrom_label').innerText = '출금';
  document.querySelector('#update_assets_transfer_memAssetIdTo').classList.remove('hidden');
  document.querySelector('#update_assets_transfer_memAssetIdTo_label').classList.remove('hidden');
  document.querySelector('#update_transfer_expense_category').classList.add('hidden');
  document.querySelector('#update_transfer_expense_category_label').classList.add('hidden');
  document.querySelector('#update_transfer_income_category_label').classList.add('hidden');
  document.querySelector('#update_transfer_income_category').classList.add('hidden');
  document.querySelector('#update_transfer_income_category').required = false;
  document.querySelector('#update_transfer_expense_category').required = false;
  document.querySelector('#update_assets_transfer_memAssetIdTo').required = true;
}
//수입이 체크되면 수입 폼을 보여주고 지출,이체 폼은 hidden
function showIncomeForm() {
  document.querySelector('#insert_income_form').classList.remove('hidden');
  document.querySelector('#insert_expense_form').classList.add('hidden');
  document.querySelector('#insert_transfer_form').classList.add('hidden');
}
//지출이 체크되면 지출 폼을 보여주고 수입,이체 폼은 hidden
function showExpenseForm() {
  document.querySelector('#insert_income_form').classList.add('hidden');
  document.querySelector('#insert_expense_form').classList.remove('hidden');
  document.querySelector('#insert_transfer_form').classList.add('hidden');
}
//이체가 체크되면 이체 폼을 보여주고 수입,지출 폼은 hidden
function showTransferForm() {
  document.querySelector('#insert_income_form').classList.add('hidden');
  document.querySelector('#insert_expense_form').classList.add('hidden');
  document.querySelector('#insert_transfer_form').classList.remove('hidden');
}
//인서트 창에서 사용자가 탭을 변경시 공유할 수 있는 데이터는 서로 공유하도록 해주자.
//날짜 공유
function syncronizeDateAtInsertForm(thisValue) {
  let insertDate = thisValue.value;
  document.querySelector('#insert_income_date').value = insertDate;
  document.querySelector('#insert_expense_date').value = insertDate;
  document.querySelector('#insert_transfer_date').value = insertDate;
}
//금액 공유
function syncronizeAmountAtInsertForm(thisValue) {
  document.querySelector('#insert_income_amount').value = thisValue.value;
  document.querySelector('#insert_expense_amount').value = thisValue.value;
  document.querySelector('#insert_transfer_amount').value = thisValue.value;
}
//메모 공유
function syncronizeMemoAtInsertForm(thisValue) {
  document.querySelector('#insert_income_memo').value = thisValue.value;
  document.querySelector('#insert_expense_memo').value = thisValue.value;
  document.querySelector('#insert_transfer_memo').value = thisValue.value;
}
//수입 인서트 창의 자산 항목을 변경하면, 숨겨진 input창에 assetsId를 넣어주는 기능.
//다른 인서트 창의 자산 항목도 자동으로 선택되게 해주자.
//onchange function으로 넣어준다.
function getAssetsIdAndPutDataAtIncomeForm() {
  let memAssetId = document.querySelector('#insert_assets_income_memAssetId').value;
  let jsonData = {"memAssetId":memAssetId};
  $.ajax({
    url:'/main/postGetAOM',
		type:'post',
		data:jsonData,
		success:function(aom){
      document.querySelector('#insert_assets_income_assetsId').value = aom.assetsId;
      document.querySelector('#insert_assets_expense_assetsId').value = aom.assetsId;
      document.querySelector('#insert_assets_expense_memAssetId').value = memAssetId;
      document.querySelector('#insert_assets_transfer_memAssetId').value = memAssetId;
		}
  });
}
//지출 인서트 창의 자산 항목을 변경하면, 숨겨진 input창에 assetsId를 넣어주는 기능.
//다른 인서트 창의 자산 항목도 자동으로 선택되게 해주자.
//onchange function으로 넣어준다.
function getAssetsIdAndPutDataAtExpenseForm() {
  let memAssetId = document.querySelector('#insert_assets_expense_memAssetId').value;
  let jsonData = {"memAssetId":memAssetId};
  $.ajax({
    url:'/main/postGetAOM',
		type:'post',
		data:jsonData,
		success:function(aom){
      document.querySelector('#insert_assets_income_assetsId').value = aom.assetsId;
      document.querySelector('#insert_assets_expense_assetsId').value = aom.assetsId;
      document.querySelector('#insert_assets_income_memAssetId').value = memAssetId;
      document.querySelector('#insert_assets_transfer_memAssetId').value = memAssetId;
		}
  });
}
//이체 인서트 창의 자산 항목을 변경하면, 숨겨진 input창에 assetsId를 넣어주는 기능.
//다른 인서트 창의 자산 항목도 자동으로 선택되게 해주자.
//onchange function으로 넣어준다.
function getAssetsIdAndPutDataAtTransferForm() {
  let memAssetId = document.querySelector('#insert_assets_transfer_memAssetId').value;
  let jsonData = {"memAssetId":memAssetId};
  $.ajax({
    url:'/main/postGetAOM',
		type:'post',
		data:jsonData,
		success:function(aom){
      document.querySelector('#insert_assets_income_assetsId').value = aom.assetsId;
      document.querySelector('#insert_assets_expense_assetsId').value = aom.assetsId;
      document.querySelector('#insert_assets_income_memAssetId').value = memAssetId;
      document.querySelector('#insert_assets_expense_memAssetId').value = memAssetId;
		}
  });
}
//수입 인서트 폼에서 해당 유저의 자산 목록을 보여주는 기능
function showAOMListAtInsertIncome(aaomList) {
  let aomStr = ' ';
  if(aaomList != null) {for(let i = 0; i < aaomList.length; i++) {
	  if(aaomList[i].assetsId!=99){aomStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+'</option>';}
    }}else{aomStr = '<option value id="insert_assets_income_memAssetId_selected" selected disabled>등록된 자산이 없습니다.</option>';}
  document.querySelector('#insert_assets_income_memAssetId').innerHTML += aomStr;
}
//지출 인서트 폼에서 해당 유저의 자산 목록을 보여주는 기능
function showAOMListAtInsertExpense(aaomList) {
  let aomStr = ' ';
  if(aaomList != null) {for(let i = 0; i < aaomList.length; i++) {
	  if(aaomList[i].assetsId!=99){aomStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+'</option>';}
    }}else{aomStr = '<option value id="insert_assets_expense_memAssetId_selected" selected disabled>등록된 자산이 없습니다.</option>';}
  document.querySelector('#insert_assets_expense_memAssetId').innerHTML += aomStr;
}
//이체 인서트 폼에서 출금자산에 해당 유저의 자산 목록을 보여주는 기능
function showAOMListAtInsertTransferAtFrom(aaomList) {
  let aomStr = ' ';
  if(aaomList != null) {for(let i = 0; i < aaomList.length; i++) {
	  if(aaomList[i].assetsId!=99){aomStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+'</option>';}
    }}else{aomStr = '<option value id="insert_assets_transfer_memAssetId_selected" selected disabled>등록된 자산이 없습니다.</option>';}
  document.querySelector('#insert_assets_transfer_memAssetId').innerHTML += aomStr;
}
//이체 인서트 폼에서 입금자산에 해당 유저의 자산 목록을 보여주는 기능
function showAOMListAtInsertTransferAtTo(aaomList) {
  let aomStr = ' ';
  if(aaomList != null) {for(let i = 0; i < aaomList.length; i++) {
	  if(aaomList[i].assetsId!=99){aomStr += '<option value='+aaomList[i].memAssetId+'>'+aaomList[i].assetsName+' '+aaomList[i].memo+'</option>';}
    }}else{aomStr = '<option value id="insert_assets_transfer_memAssetIdTo_selected" selected disabled>등록된 자산이 없습니다.</option>';}
  document.querySelector('#insert_assets_transfer_memAssetIdTo').innerHTML += aomStr;
}

// ########## 로드 된 후 이벤트 ##########
document.addEventListener('DOMContentLoaded', function() {
	//처음 브라우저 창이 켜졌을 떄, 화면크기 판단
	if(window.innerWidth <= 1200){
		 document.querySelector('.grid_aside').classList.add('hidden');
		 document.querySelector('.insert_modal_overlay').classList.remove('hidden');
		 document.querySelector('.show_insert_modal').classList.remove('hidden');
		 document.querySelector('.mainPage_sideMenuContainer').classList.add('hidden');
		 for(let i = 0; i < document.querySelectorAll('button.close_insert_modal').length; i++){
			  document.querySelectorAll('button.close_insert_modal')[i].classList.remove('hidden');
		 }
	 }else{
		 document.querySelector('.grid_aside').classList.remove('hidden');
		 document.querySelector('.insert_modal_overlay').classList.add('hidden');
		 document.querySelector('.show_insert_modal').classList.add('hidden');
		 document.querySelector('.mainPage_sideMenuContainer').classList.remove('hidden');
		 for(let i = 0; i < document.querySelectorAll('button.close_insert_modal').length; i++){
			  document.querySelectorAll('button.close_insert_modal')[i].classList.add('hidden');
		 }
	 }
	
  const modal = document.querySelector('div.modal');
  const modalOverlay = document.querySelector('div.modal_overlay');
  const closeModalButton = document.querySelectorAll('button.close_modal');
  for(let i = 0; i < closeModalButton.length; i++) {
    closeModalButton[i].addEventListener('click', function() {
      modal.classList.add('hidden');
      document.querySelector('#update_expense_form').classList.add('hidden');
      document.querySelector('#update_income_form').classList.add('hidden');
      document.querySelector('#update_transfer_form').classList.add('hidden');
    });
  }
  modalOverlay.addEventListener('click', function() {
    modal.classList.add('hidden');
    document.querySelector('#update_expense_form').classList.add('hidden');
    document.querySelector('#update_income_form').classList.add('hidden');
    document.querySelector('#update_transfer_form').classList.add('hidden');
  });
  document.querySelector('.show_insert_modal').addEventListener('click', function() {
    document.querySelector('.grid_aside').classList.remove('hidden');
    document.querySelector('.insert_modal_overlay').classList.remove('hidden');
    document.querySelector('.mainPage_sideMenuContainer').classList.remove('hidden');
  });
  document.querySelector('.insert_modal_overlay').addEventListener('click', function() {
    document.querySelector('.grid_aside').classList.add('hidden');
    document.querySelector('.insert_modal_overlay').classList.add('hidden');
    document.querySelector('.mainPage_sideMenuContainer').classList.add('hidden');
  });
  for(let i = 0; i < document.querySelectorAll('button.close_insert_modal').length; i++){
	  document.querySelectorAll('button.close_insert_modal')[i].addEventListener('click', function() {
		  document.querySelector('.grid_aside').classList.add('hidden');
		  document.querySelector('.insert_modal_overlay').classList.add('hidden');
		  document.querySelector('.mainPage_sideMenuContainer').classList.add('hidden');
		 });		
	 }
  
  //화면 사이즈가 줄어들면 인서트창 숨기고 버튼으로 불러오자
  window.addEventListener('resize', function() {
	 if(window.innerWidth <= 1200){
		 document.querySelector('.grid_aside').classList.add('hidden');
		 document.querySelector('.insert_modal_overlay').classList.remove('hidden');
		 document.querySelector('.show_insert_modal').classList.remove('hidden');
		 document.querySelector('.mainPage_sideMenuContainer').classList.add('hidden');
		 for(let i = 0; i < document.querySelectorAll('button.close_insert_modal').length; i++){
			  document.querySelectorAll('button.close_insert_modal')[i].classList.remove('hidden');
		 }
	 }else{
		 document.querySelector('.grid_aside').classList.remove('hidden');
		 document.querySelector('.insert_modal_overlay').classList.add('hidden');
		 document.querySelector('.show_insert_modal').classList.add('hidden');
		 document.querySelector('.mainPage_sideMenuContainer').classList.remove('hidden');
		 for(let i = 0; i < document.querySelectorAll('button.close_insert_modal').length; i++){
			  document.querySelectorAll('button.close_insert_modal')[i].classList.add('hidden');
		 }
	 }
  });
});