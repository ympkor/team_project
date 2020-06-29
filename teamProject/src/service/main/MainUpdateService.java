package service.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.main.Expense;
import dto.main.IncomeUpdate;
import mapper.main.MainUpdateMapper;

@Service
public class MainUpdateService {
	@Autowired
	private MainUpdateMapper mainUpdateMapper;

	//income 업데이트
	public void updateIncomeByIncomeId(IncomeUpdate incomeUpdate) {
		mainUpdateMapper.updateIncomeByIncomeId(incomeUpdate);
	}

	//select sum(income)
	public int selectSUMIncomeByUserKeyAndAssetsId(int userKey, int assetsId) {
		int sumIncome = 0;
		sumIncome = mainUpdateMapper.selectSUMINCOMEByUserKeyAndAssetsId(userKey, assetsId);
		return sumIncome;
	}

	//select sum(expense)
	public int selectSUMExpenseByUserKeyAndAssetsId(int userKey,  int assetsId) {
		int sumExpense = 0;
		sumExpense = mainUpdateMapper.selectSUMExpenseByUserKeyAndAssetsId(userKey, assetsId);
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

	//insert Expense
	public void insertExpense(Expense expense) {
		mainUpdateMapper.insertExpense(expense);
	}
}
