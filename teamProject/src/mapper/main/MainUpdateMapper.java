package mapper.main;

import org.apache.ibatis.annotations.Param;

import dto.main.Expense;
import dto.main.Income;
import dto.main.Transfer;

public interface MainUpdateMapper {
	//Insert Income
	public void insertIncome(Income income);

	//Insert expense
	public void insertExpense(Expense expense);
	
	//INSERT transfer
	public void insertTransfer(Transfer transfer);
	
	//income_id로 income update
	public void updateIncomeByIncomeId(Income income);

	//ExpenseId로 Expense update
	public void updateExpenseByExpenseId(Expense expense);
	
	//transferId로  transfer update
	public void updateTransferByTransferId(Transfer transfer);

	//해당 유저의 모든 income SUM
	public Integer selectSUMINCOMEByUserKeyAndAssetsId(@Param("userKey")int userKey, @Param("assetsId") int assetsId);

	//해당 유저의 모든 expense SUM
	public Integer selectSUMExpenseByUserKeyAndAssetsId(@Param("userKey")int userKey,  @Param("assetsId") int assetsId);

	//AOM의 amount를 plus해서 업데이트 해주는 기능
	public void plusAOMAmountById(@Param("amount")int amount, @Param("memAssetId")int memAssetId);

	//AOM의 amount를 minus해서 업데이트 해주는 기능
	public void minusAOMAmountById(@Param("amount")int amount, @Param("memAssetId")int memAssetId);
	
	//DELETE income by income id
	public void deleteIncomeByIncomeId(@Param("incomeId")int incomeId);
	
	//DELETE expense By Expense id
	public void deleteExpenseByExpenseId(@Param("expenseId")int expenseId);

	//DELETE transfer By transfer id
	public void deleteTransferByTransferId(int transferId);
}
