//수입 업데이트 폼에서 수입 카테고리 클릭 시
function updateIncomeFormSwitchToIncome() {
  document.querySelector('#update_assets_income_memAssetIdFrom_label').innerText = '자산';
  document.querySelector('#update_assets_income_memAssetIdTo').classList.add('hidden');
  document.querySelector('#update_assets_income_memAssetIdTo_label').classList.add('hidden');
  document.querySelector('#update_income_expense_category').classList.add('hidden');
  document.querySelector('#update_income_expense_category_label').classList.add('hidden');
  document.querySelector('#update_income_income_category_label').classList.remove('hidden');
  document.querySelector('#update_income_income_category').classList.remove('hidden');
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
}
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

document.addEventListener('DOMContentLoaded', function() {
  const modal = document.querySelector('div.modal');
  const modalOverlay = document.querySelector('div.modal_overlay');
  const closeModalButton = document.querySelectorAll('button.close_modal');
  for(let i = 0; i < closeModalButton.length; i++) {
    closeModalButton[i].addEventListener('click', function() {
      modal.classList.add('hidden');
      document.querySelector('#update_expense_form').classList.add('hidden');
      document.querySelector('#update_income_form').classList.add('hidden');
      document.querySelector('#update_transfer_form').classList.add('hidden');
    })
  }
  modalOverlay.addEventListener('click', function() {
    modal.classList.add('hidden');
    document.querySelector('#update_expense_form').classList.add('hidden');
    document.querySelector('#update_income_form').classList.add('hidden');
    document.querySelector('#update_transfer_form').classList.add('hidden');
  })
});