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
	
	//income 업데이트
	public void updateIncomeByIncomeId(Income income) {
		mainUpdateMapper.updateIncomeByIncomeId(income);
	}

	//expense 업데이트
	private void updateExpenseByExpenseId(Expense expense) {
		mainUpdateMapper.updateExpenseByExpenseId(expense);
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
	
	//asset of member의 amount 업데이트(플러스) 기능
	public void plusAOMAmountById(int amount, int memAssetId) {
		mainUpdateMapper.plusAOMAmountById(amount, memAssetId);
	}
	
	//asset of member의 amount 업데이트(마이너스) 기능
	private void minusAOMAountById(int amount, int memAssetId) {
		mainUpdateMapper.minusAOMAmountById(amount, memAssetId);
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
	
	//수입 항목을 update하고 AOM도 update해주는 서비스
	@Transactional
	public void updateIncomeAndUpdateAOM(IncomeUpdate incomeUpdate) {
		//income업데이트
		updateIncomeByIncomeId(new Income(incomeUpdate.getIncomeId(), incomeUpdate.getUserKey(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getAssetsId(), incomeUpdate.getIcId(), incomeUpdate.getMemo(), incomeUpdate.getNewMemAssetId()));
//		aom을 업데이트
		minusAOMAountById(incomeUpdate.getAmount(), incomeUpdate.getMemAssetId());
		plusAOMAmountById(incomeUpdate.getNewAmount(), incomeUpdate.getNewMemAssetId());			
	}

	//수입을 지우고 지출을 insert하고 AOM업데이트
	@Transactional
	public void deleteIncomeInsertExpenseUpdateAOM(IncomeUpdate incomeUpdate) {
//		income id로 income지워주고, 
		deleteIncomeByIncomeId(incomeUpdate.getIncomeId());
//		delete하면 자산도 수정해야한다.
		minusAOMAountById(incomeUpdate.getAmount(), incomeUpdate.getMemAssetId());
		//해당 정보로 다시 expense에 insert 해준다.
		Expense expense = new Expense(incomeUpdate.getIncomeId(), incomeUpdate.getUserKey(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getAssetsId(), incomeUpdate.getEcId(), incomeUpdate.getMemo(), incomeUpdate.getNewMemAssetId());
		insertExpense(expense);
//		AOM도 업데이트 해준다
		minusAOMAountById(incomeUpdate.getNewAmount(), incomeUpdate.getNewMemAssetId());
	}

	//지출항목을 업데이트 해주고, income과 expense의 sum(amount)를 select하고 aom의 amout를 업데이트 한다.
	@Transactional
	public void updateEpAndAmount(ExpenseUpdate expenseUpdate) {
//		Expense를 업데이트
		updateExpenseByExpenseId(new Expense(expenseUpdate.getExpenseId(), expenseUpdate.getUserkey(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getEcId(), expenseUpdate.getMemo(), expenseUpdate.getNewMemAssetId()));
//		aom을 업데이트
		plusAOMAmountById(expenseUpdate.getAmount(), expenseUpdate.getMemAssetId());
		minusAOMAountById(expenseUpdate.getNewAmount(), expenseUpdate.getNewMemAssetId());
	}

	//지출항목을 지워주고, income항목에 insert해주고 aom의 amount를 업데이트 해주자.
	@Transactional
	public void deleteEpInsertInUpdateAOM(ExpenseUpdate expenseUpdate) {
		//Expense를 delete
		deleteExpenseByExpenseId(expenseUpdate.getExpenseId());
		plusAOMAmountById(expenseUpdate.getAmount(), expenseUpdate.getMemAssetId());
		//Income을 insert
		insertIncome(new Income(0, expenseUpdate.getUserkey(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getIcId(), expenseUpdate.getMemo(), expenseUpdate.getNewMemAssetId()));
//		aom도 업데이트
		plusAOMAmountById(expenseUpdate.getNewAmount(), expenseUpdate.getNewMemAssetId());
	}

	//수입 항목을 insert하고 aom을 업데이트 한다.
	@Transactional
	public void insertIncomeAndUpdateAOM(Income income) {
		insertIncome(income);
		plusAOMAmountById(income.getAmount(), income.getMemAssetId());
	}

	//지출 항목을 insert하고 aom을 업데이트 한다
	@Transactional
	public void insertExpenseAndUpdateAOM(Expense expense) {
		insertExpense(expense);
		minusAOMAountById(expense.getAmount(), expense.getMemAssetId());
	}
	
	//수입 항목을 delete하고 aom을 업데이트 한다.
	@Transactional
	public void deleteIncomeAndUpdateAOM(DeleteIncome deleteIncome) {
		deleteIncomeByIncomeId(deleteIncome.getIncomeId());
		minusAOMAountById(deleteIncome.getAmount(), deleteIncome.getMemAssetId());
	}

	//지출 항목을 delete하고 aom을 업데이트 한다.
	@Transactional
	public void deleteExpenseAndUpdateAOM(DeleteExpense deleteExpense) {
		deleteExpenseByExpenseId(deleteExpense.getExpenseId());
		plusAOMAmountById(deleteExpense.getAmout(), deleteExpense.getMemAssetId());
	}
}
