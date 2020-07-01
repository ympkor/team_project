package controller.main;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.main.Calendar;
import dto.main.DeleteExpense;
import dto.main.DeleteIncome;
import dto.main.Expense;
import dto.main.ExpenseUpdate;
import dto.main.Income;
import dto.main.IncomeUpdate;
import dto.main.UpdateAndRefresh;
import service.main.MainPageService;
import service.main.MainUpdateService;

@Controller
@RequestMapping("/main")
@SessionAttributes("userKey")
public class MainUpdateController {
	@Autowired
	private MainUpdateService mainUpdateService;
	@Autowired
	private MainPageService mainService;

//	int userKey = 1;
	
	//Income을 업데이트
	@PostMapping("/postUpdateIncome")
	public @ResponseBody UpdateAndRefresh updateIncomeGetMIICList(@ModelAttribute("userKey")int userKey, IncomeUpdate incomeUpdate){
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		incomeUpdate.setUserKey(userKey);
		if(incomeUpdate.getCategory() == 1) {//그대로 수입인것이다. id로 수입 업데이트 해주면 된다.
			mainUpdateService.updateIncomeAndUpdateAOM(incomeUpdate);
		}else {//수입을 지출로 바꾼 것이다. 
			mainUpdateService.deleteIncomeInsertExpenseUpdateAOM(incomeUpdate);
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(incomeUpdate.getIncomeDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postUpdateExpense")
	public @ResponseBody UpdateAndRefresh updateExpense(@ModelAttribute("userKey")int userKey, ExpenseUpdate expenseUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		expenseUpdate.setUserkey(userKey);
		if(expenseUpdate.getCategory() == 2) {//그대로 지출인 것.
			mainUpdateService.updateEpAndAmount(expenseUpdate);
		}else {//지출을 수입으로 바꾼 것
			mainUpdateService.deleteEpInsertInUpdateAOM(expenseUpdate);
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(expenseUpdate.getExpenseDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postInsertIncome")
	public @ResponseBody UpdateAndRefresh insertIncome(@ModelAttribute("userKey")int userKey, Income income) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		income.setUserKey(userKey);
		mainUpdateService.insertIncomeAndUpdateAOM(income);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(income.getIncomeDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, income.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, income.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, income.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, income.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, income.getIncomeDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postInsertExpense")
	public @ResponseBody UpdateAndRefresh insertExpense(@ModelAttribute("userKey")int userKey, Expense expense) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		expense.setUserKey(userKey);
		mainUpdateService.insertExpenseAndUpdateAOM(expense);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(expense.getExpenseDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postDeleteIncome")
	public @ResponseBody UpdateAndRefresh deleteIncome(@ModelAttribute("userKey")int userKey, DeleteIncome deleteIncome) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		deleteIncome.setUserKey(userKey);
		mainUpdateService.deleteIncomeAndUpdateAOM(deleteIncome);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(deleteIncome.getIncomeDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, deleteIncome.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, deleteIncome.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, deleteIncome.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, deleteIncome.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, deleteIncome.getIncomeDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postDeleteExpense")
	public @ResponseBody UpdateAndRefresh deleteExpense(@ModelAttribute("userKey")int userKey, DeleteExpense deleteExpense) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		deleteExpense.setUserKey(userKey);
		mainUpdateService.deleteExpenseAndUpdateAOM(deleteExpense);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(deleteExpense.getExpenseDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, deleteExpense.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, deleteExpense.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, deleteExpense.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, deleteExpense.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, deleteExpense.getExpenseDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
}
