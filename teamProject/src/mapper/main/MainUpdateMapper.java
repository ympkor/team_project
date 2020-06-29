package mapper.main;

import org.apache.ibatis.annotations.Param;

import dto.main.Expense;
import dto.main.IncomeUpdate;

public interface MainUpdateMapper {

	//income_id로 income update
	public void updateIncomeByIncomeId(IncomeUpdate incomeUpdate);

	//mem_asset_id로 asset_of_member update
	public void updateAssetsOfMemeberByMemAssetId(IncomeUpdate incomeUpdate);

	//해당 유저의 모든 income SUM
	public int selectSUMINCOMEByUserKeyAndAssetsId(@Param("userKey")int userKey, @Param("assetsId") int assetsId);

	//해당 유저의 모든 expense SUM
	public int selectSUMExpenseByUserKeyAndAssetsId(@Param("userKey")int userKey,  @Param("assetsId") int assetsId);

	//AOM의 amount update
	public void updateAOMByMemAssetId(@Param("amount")int amount, @Param("memAssetId")int memAssetId);

	//DELETE income by income id
	public void deleteIncomeByIncomeId(@Param("incomeId")int incomeId);

	//Insert expense
	public void insertExpense(Expense expense);

}
