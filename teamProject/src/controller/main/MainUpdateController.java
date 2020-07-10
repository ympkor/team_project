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
import dto.main.Expense;
import dto.main.ExpenseUpdate;
import dto.main.Income;
import dto.main.IncomeUpdate;
import dto.main.Transfer;
import dto.main.TransferUpdate;
import dto.main.UpdateAndRefresh;
import service.main.MainPageService;
import service.main.MainUpdateService;

@Controller
@SessionAttributes("userKey")
@RequestMapping("/main")
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
		}else if(incomeUpdate.getCategory() == 2){//수입을 지출로 바꾼 것이다. 
			mainUpdateService.deleteIncomeInsertExpenseUpdateAOM(incomeUpdate);
		}else {//수입을 이체로 바꾼것
			mainUpdateService.deleteIncomeInsertTransferUpdateAOM(incomeUpdate);
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(incomeUpdate.getIncomeDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
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
		}else if(expenseUpdate.getCategory() == 1){//지출을 수입으로 바꾼 것
			mainUpdateService.deleteEpInsertInUpdateAOM(expenseUpdate);
		}else {//지출을 이체로 바꾼 것
			mainUpdateService.deleteExpenseInsertTransferUpdateAOM(expenseUpdate);			
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(expenseUpdate.getExpenseDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postUpdateTransfer")
	public @ResponseBody UpdateAndRefresh updateTransfer(@ModelAttribute("userKey")int userKey, TransferUpdate transferUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		transferUpdate.setUserKey(userKey);
		if(transferUpdate.getCategory() == 3) {//그대로 이체인것
			mainUpdateService.updateTransferAndAmount(transferUpdate);
		}else if(transferUpdate.getCategory() == 2) {//이체를 지출로 변경한것
			mainUpdateService.deleteTransferInsertExpenseUpdateAom(transferUpdate);
		}else {//이체를 수입으로 변경한것
			mainUpdateService.deleteTransferInsertIncomeUpdateAom(transferUpdate);
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(transferUpdate.getTransferDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
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
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, income.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, income.getIncomeDate().toString().substring(0, 10)));
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
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, expense.getExpenseDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postInsertTransfer")
	public @ResponseBody UpdateAndRefresh insertTransfer(@ModelAttribute("userKey")int userKey, Transfer transfer) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		transfer.setUserKey(userKey);
		mainUpdateService.insertTransferAndUpdateAom(transfer);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(transfer.getTransferDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, transfer.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, transfer.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, transfer.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, transfer.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, transfer.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, transfer.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, transfer.getTransferDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postDeleteIncome")
	public @ResponseBody UpdateAndRefresh deleteIncome(@ModelAttribute("userKey")int userKey, IncomeUpdate incomeUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		incomeUpdate.setUserKey(userKey);
		mainUpdateService.deleteIncomeAndUpdateAOM(incomeUpdate);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(incomeUpdate.getIncomeDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postDeleteExpense")
	public @ResponseBody UpdateAndRefresh deleteExpense(@ModelAttribute("userKey")int userKey, ExpenseUpdate expenseUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		expenseUpdate.setUserkey(userKey);
		mainUpdateService.deleteExpenseAndUpdateAOM(expenseUpdate);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(expenseUpdate.getExpenseDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postDeleteTransfer")
	public @ResponseBody UpdateAndRefresh deleteTransfer(@ModelAttribute("userKey")int userKey, TransferUpdate transferUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		transferUpdate.setUserKey(userKey);
		mainUpdateService.deleteTransferAndUpdateAOM(transferUpdate);
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(transferUpdate.getTransferDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postUpdateIncomeInsert")
	public @ResponseBody UpdateAndRefresh insertEventAtIncomeUpdateForm(@ModelAttribute("userKey")int userKey, IncomeUpdate incomeUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		incomeUpdate.setUserKey(userKey);
		if(incomeUpdate.getCategory() == 1) {
			mainUpdateService.insertIncomeAndUpdateAOM(new Income(0, incomeUpdate.getUserKey(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getAssetsId(), incomeUpdate.getIcId(), incomeUpdate.getMemo(), incomeUpdate.getNewMemAssetId()));
		}else if(incomeUpdate.getCategory() == 2) {
			mainUpdateService.insertExpenseAndUpdateAOM(new Expense(0, incomeUpdate.getUserKey(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getAssetsId(), incomeUpdate.getEcId(), incomeUpdate.getMemo(), incomeUpdate.getNewMemAssetId()));
		}else {
			mainUpdateService.insertTransferAndUpdateAom(new Transfer(0, incomeUpdate.getUserKey(), incomeUpdate.getNewMemAssetId(), incomeUpdate.getMemAssetIdTo(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getMemo()));
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(incomeUpdate.getIncomeDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, incomeUpdate.getIncomeDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postUpdateExpenseInsert")
	public @ResponseBody UpdateAndRefresh insertEventAtExpenseUpdateForm(@ModelAttribute("userKey")int userKey, ExpenseUpdate expenseUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		expenseUpdate.setUserkey(userKey);
		if(expenseUpdate.getCategory() == 1) {
			mainUpdateService.insertIncomeAndUpdateAOM(new Income(0, expenseUpdate.getUserkey(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getIcId(), expenseUpdate.getMemo(), expenseUpdate.getNewMemAssetId()));
		}else if(expenseUpdate.getCategory() == 2) {
			mainUpdateService.insertExpenseAndUpdateAOM(new Expense(0, expenseUpdate.getUserkey(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getEcId(), expenseUpdate.getMemo(), expenseUpdate.getNewMemAssetId()));
		}else {
			mainUpdateService.insertTransferAndUpdateAom(new Transfer(0, expenseUpdate.getUserkey(), expenseUpdate.getNewMemAssetId(), expenseUpdate.getMemAssetIdTo(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getMemo()));
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(expenseUpdate.getExpenseDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, expenseUpdate.getExpenseDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
	
	@PostMapping("/postUpdateTransferInsert")
	public @ResponseBody UpdateAndRefresh insertEventAtTransferUpdateForm(@ModelAttribute("userKey")int userKey, TransferUpdate transferUpdate) {
		UpdateAndRefresh updateAndRefresh = new UpdateAndRefresh();
		transferUpdate.setUserKey(userKey);
		if(transferUpdate.getCategory() == 1) {
			mainUpdateService.insertIncomeAndUpdateAOM(new Income(0, transferUpdate.getUserKey(), transferUpdate.getNewAmount(), transferUpdate.getTransferDate(), transferUpdate.getAssetsId(), transferUpdate.getIcId(), transferUpdate.getMemo(), transferUpdate.getNewMemAssetIdFrom()));
		}else if(transferUpdate.getCategory() == 2) {
			mainUpdateService.insertExpenseAndUpdateAOM(new Expense(0, transferUpdate.getUserKey(), transferUpdate.getNewAmount(), transferUpdate.getTransferDate(), transferUpdate.getAssetsId(), transferUpdate.getEcId(), transferUpdate.getMemo(), transferUpdate.getNewMemAssetIdFrom()));
		}else {
			mainUpdateService.insertTransferAndUpdateAom(new Transfer(0, transferUpdate.getUserKey(), transferUpdate.getNewMemAssetIdFrom(), transferUpdate.getNewMemAssetIdTo(), transferUpdate.getNewAmount(), transferUpdate.getTransferDate(), transferUpdate.getMemo()));
		}
		updateAndRefresh.setCal(new Calendar(LocalDate.parse(transferUpdate.getTransferDate().toString())));
		updateAndRefresh.setMiicList(mainService.selectMIICByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setMeecList(mainService.selectMEECByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTbmList(mainService.selectTransferByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		updateAndRefresh.setTaomfaomtList(mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setIicaList(mainService.selectIICAByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setEecaList(mainService.selectEECAByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 10)));
		updateAndRefresh.setSumAmounts(mainService.selectSUMIEByUserKeyAndDate(userKey, transferUpdate.getTransferDate().toString().substring(0, 7)));
		return updateAndRefresh;
	}
}
