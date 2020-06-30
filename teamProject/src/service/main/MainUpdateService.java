package service.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.main.DeleteExpense;
import dto.main.DeleteIncome;
import dto.main.Expense;
import dto.main.ExpenseUpdate;
import dto.main.Income;
import dto.main.IncomeUpdate;
import mapper.main.MainUpdateMapper;

@Service
public class MainUpdateService {
	@Autowired
	private MainUpdateMapper mainUpdateMapper;
	
	//수입 항목을 update하고 AOM도 update해주는 서비스
	@Transactional
	public void updateIncomeAndUpdateAOM(IncomeUpdate incomeUpdate) {
		//income업데이트
		updateIncomeByIncomeId(incomeUpdate);
		//유저의 update된 계좌의 sumIncome과 sumExpense를 가져온다
		int assetsId = incomeUpdate.getAssetsId();
		int sumIncome = selectSUMIncomeByUserKeyAndAssetsId(incomeUpdate.getUserKey(), assetsId);
		int sumExpense = selectSUMExpenseByUserKeyAndAssetsId(incomeUpdate.getUserKey(), assetsId);
		//sumI - sumE 해준 값(amount) userkey와 assetsId로 assts_of_member에 업데이트 해준다.
		int amount = sumIncome-sumExpense;
		updateAOMByid(amount, incomeUpdate.getMemAssetId());
	}

	//수입을 지우고 지출을 insert하고 AOM업데이트
	@Transactional
	public void deleteIncomeInsertExpenseUpdateAOM(IncomeUpdate incomeUpdate) {
//		income id로 income지워주고, 
		deleteIncomeByIncomeId(incomeUpdate.getIncomeId());
		//해당 정보로 다시 expense에 insert 해준다.
		Expense expense = new Expense(incomeUpdate.getIncomeId(), incomeUpdate.getUserKey(), incomeUpdate.getAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getAssetsId(), incomeUpdate.getEcId(), incomeUpdate.getMemo(), 0);
		insertExpense(expense);
//		sumIncome과 sumExpense를 구해주고 aom에 update해준다.
		int sumIncome = selectSUMIncomeByUserKeyAndAssetsId(incomeUpdate.getUserKey(), incomeUpdate.getAssetsId());
		int sumExpense = selectSUMExpenseByUserKeyAndAssetsId(incomeUpdate.getUserKey(), incomeUpdate.getAssetsId());
		//sumI - sumE 해준 값(amount) userkey와 assetsId로 assts_of_member에 업데이트 해준다.
		int amount = sumIncome-sumExpense;
		updateAOMByid(amount, incomeUpdate.getMemAssetId());
	}
	
	//income 업데이트
	public void updateIncomeByIncomeId(IncomeUpdate incomeUpdate) {
		mainUpdateMapper.updateIncomeByIncomeId(incomeUpdate);
	}

	//select sum(income)
	public int selectSUMIncomeByUserKeyAndAssetsId(int userKey, int assetsId) {
		Integer sumIncome = 0;
		sumIncome = mainUpdateMapper.selectSUMINCOMEByUserKeyAndAssetsId(userKey, assetsId);
		if(sumIncome == null) {sumIncome = 0;}
		return sumIncome;
	}

	//select sum(expense)
	public int selectSUMExpenseByUserKeyAndAssetsId(int userKey,  int assetsId) {
		Integer sumExpense = 0;
		sumExpense = mainUpdateMapper.selectSUMExpenseByUserKeyAndAssetsId(userKey, assetsId);
		if(sumExpense == null) {sumExpense = 0;}
		return sumExpense;
	}

	//update AOM amount By user key and assets id
	public void updateAOMByid(int amount, int memAssetId) {
		mainUpdateMapper.updateAOMByMemAssetId(amount, memAssetId);
	}

	//delete Income By incomeId
	public void deleteIncomeByIncomeId(int incomeId) {
		mainUpdateMapper.deleteIncomeByIncomeId(incomeId);
	}
	
	//delete Expense By ExpenseId
	public void deleteExpenseByExpenseId(int expenseId) {
		mainUpdateMapper.deleteExpenseByExpenseId(expenseId);
	}

	//insert Expense
	public void insertExpense(Expense expense) {
		mainUpdateMapper.insertExpense(expense);
	}
	
	//insert Income
	public void insertIncome(Income income) {
		mainUpdateMapper.insertIncome(income);
	}

	//지출항목을 업데이트 해주고, income과 expense의 sum(amount)를 select하고 aom의 amout를 업데이트 한다.
	@Transactional
	public void updateEpAndAmount(ExpenseUpdate expenseUpdate) {
//		Expense를 업데이트
		Expense expense = new Expense(expenseUpdate.getExpenseId(), expenseUpdate.getUserkey(), expenseUpdate.getAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getEcId(), expenseUpdate.getMemo(), 0);
		mainUpdateMapper.updateExpenseByExpenseId(expense);
//		income과 expense의 sum을 구하자
		int sumI = selectSUMIncomeByUserKeyAndAssetsId(expenseUpdate.getUserkey(), expenseUpdate.getAssetsId());
		int sumE = selectSUMExpenseByUserKeyAndAssetsId(expenseUpdate.getUserkey(), expenseUpdate.getAssetsId());
		int amount = sumI-sumE;
//		구한 amount로 해당 유저의 aom을 수정하자.
		updateAOMByid(amount, expenseUpdate.getMemAssetId());
	}

	//지출항목을 지워주고, income항목에 insert해주고 aom의 amount를 업데이트 해주자.
	@Transactional
	public void deleteEpInsertInUpdateAOM(ExpenseUpdate expenseUpdate) {
		//Expense를 delete
		deleteExpenseByExpenseId(expenseUpdate.getExpenseId());
		//Income을 insert
		Income income = new Income(0, expenseUpdate.getUserkey(), expenseUpdate.getAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getIcId(), expenseUpdate.getMemo(), 0);
		insertIncome(income);
//		income과 expense의 sum을 구하자
		int sumI = selectSUMIncomeByUserKeyAndAssetsId(expenseUpdate.getUserkey(), expenseUpdate.getAssetsId());
		int sumE = selectSUMExpenseByUserKeyAndAssetsId(expenseUpdate.getUserkey(), expenseUpdate.getAssetsId());
		int amount = sumI-sumE;
//		구한 amount로 해당 유저의 aom을 수정하자.
		updateAOMByid(amount, expenseUpdate.getMemAssetId());
	}

	//수입 항목을 insert하고 aom을 업데이트 한다.
	@Transactional
	public void insertIncomeAndUpdateAOM(Income income) {
		insertIncome(income);
		int sumI = selectSUMIncomeByUserKeyAndAssetsId(income.getUserKey(), income.getAssetsId());
		int sumE = selectSUMExpenseByUserKeyAndAssetsId(income.getUserKey(), income.getAssetsId());
		int amount = sumI-sumE;
		updateAOMByid(amount, income.getMemAssetId());
	}

	//지출 항목을 insert하고 aom을 업데이트 한다
	@Transactional
	public void insertExpenseAndUpdateAOM(Expense expense) {
		insertExpense(expense);
		int sumI = selectSUMIncomeByUserKeyAndAssetsId(expense.getUserKey(), expense.getAssetsId());
		int sumE = selectSUMExpenseByUserKeyAndAssetsId(expense.getUserKey(), expense.getAssetsId());
		int amount = sumI-sumE;
		updateAOMByid(amount, expense.getMemAssetId());
	}
	
	//수입 항목을 delete하고 aom을 업데이트 한다.
	@Transactional
	public void deleteIncomeAndUpdateAOM(DeleteIncome deleteIncome) {
		deleteIncomeByIncomeId(deleteIncome.getIncomeId());
		int sumI = selectSUMIncomeByUserKeyAndAssetsId(deleteIncome.getUserKey(), deleteIncome.getAssetsId());
		int sumE = selectSUMExpenseByUserKeyAndAssetsId(deleteIncome.getUserKey(), deleteIncome.getAssetsId());
		int amount = sumI-sumE;
		updateAOMByid(amount, deleteIncome.getMemAssetId());
	}

	//지출 항목을 delete하고 aom을 업데이트 한다.
	@Transactional
	public void deleteExpenseAndUpdateAOM(DeleteExpense deleteExpense) {
		deleteExpenseByExpenseId(deleteExpense.getExpenseId());
		int sumI = selectSUMIncomeByUserKeyAndAssetsId(deleteExpense.getUserKey(), deleteExpense.getAssetsId());
		int sumE = selectSUMExpenseByUserKeyAndAssetsId(deleteExpense.getUserKey(), deleteExpense.getAssetsId());
		int amount = sumI-sumE;
		updateAOMByid(amount, deleteExpense.getMemAssetId());
	}

}
