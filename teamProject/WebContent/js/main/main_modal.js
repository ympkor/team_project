document.addEventListener('DOMContentLoaded', function() {
  const modal = document.querySelector('div.modal');
  const modalOverlay = document.querySelector('div.modal_overlay');
  const closeModalButton = document.querySelectorAll('button.close_modal');
  for(let i = 0; i < closeModalButton.length; i++) {
    closeModalButton[i].addEventListener('click', function() {
      modal.classList.add('hidden');
      document.querySelector('#update_expense_form').classList.add('hidden');
      document.querySelector('#update_income_form').classList.add('hidden');
      document.querySelector('#delete_income_form').classList.add('hidden');
      document.querySelector('#delete_expense_form').classList.add('hidden');
    })
  }
  modalOverlay.addEventListener('click', function() {
    modal.classList.add('hidden');
    document.querySelector('#update_expense_form').classList.add('hidden');
    document.querySelector('#update_income_form').classList.add('hidden');
    document.querySelector('#delete_income_form').classList.add('hidden');
    document.querySelector('#delete_expense_form').classList.add('hidden');
  })
});