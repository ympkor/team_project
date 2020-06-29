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

//사이드 메뉴의 자바스크립트.
window.addEventListener('DOMContentLoaded', function() {
  //오늘 날짜를 상수로 선언해놓기. yyyy-MM-dd 형식
  const todayString = new Date().toISOString().substring(0, 10);
  document.querySelector('#insert_income_date').value = todayString;
  document.querySelector('#insert_expense_date').value = todayString;
});