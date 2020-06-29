document.addEventListener('DOMContentLoaded', function() {
  const modal = document.querySelector('div.modal');
  const modalOverlay = document.querySelector('div.modal_overlay');
  const closeModalButton = document.querySelector('button.close_modal');
  closeModalButton.addEventListener('click', function() {
    modal.classList.add('hidden');
  })
  modalOverlay.addEventListener('click', function() {
    modal.classList.add('hidden');
  })
});

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
function showInsertIncomeAom(assetsId) {
  let jsonData = {"assetsId":assetsId};
  let updateAOMIncome = document.querySelector('#insert_aom_income');
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
function showInsertExpenseAom(assetsId) {
  let jsonData = {"assetsId":assetsId};
  let updateAOMIncome = document.querySelector('#insert_aom_expense');
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