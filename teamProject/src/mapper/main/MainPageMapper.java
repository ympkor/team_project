package mapper.main;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.main.AssetsAssetsOfMember;
import dto.main.AssetsOfMember;
import dto.main.ExpenseCategory;
import dto.main.ExpenseExpenseCategoryAssets;
import dto.main.IncomeCategory;
import dto.main.IncomeIncomeCategoryAssets;
import dto.main.MemberExpenseExpenseCategory;
import dto.main.MemberIncomeIncomeCategory;
import dto.main.Transfer;
import dto.main.TransferAssetsOfMemeberFromAssetsOfMemeberTo;

public interface MainPageMapper {
	//달력에 뿌려줄 income 정보 반환
	public List<MemberIncomeIncomeCategory> selecMIICByUserKeyAndIncomeDate(@Param("userKey")int userKey, @Param("incomeDate")String incomeDate);
	
	//달력에 뿌려줄 expense 정보 반환
	public List<MemberExpenseExpenseCategory> selectMEECBYUserKeyAndExpenseDate(@Param("userKey")int userKey, @Param("expenseDate")String expenseDate);

	//달력에 뿌려줄 tranfer 정보 반환
	public List<Transfer> selectTranferByUserKeyAndTransferDate(@Param("userKey")int userKey, @Param("transferDate")String transferDate);

	//상세데이터에 뿌려줄 income 정보 반환
	public List<IncomeIncomeCategoryAssets> selectIICAByUserKeyAndIncomeDate(@Param("userKey")int userKey, @Param("incomeDate")String incomeDate);
	
	//상세데이터 뿌려줄 expense 정보 반환
	public List<ExpenseExpenseCategoryAssets> selectEECAByUserKeyAndExpenseDate(@Param("userKey")int userKey, @Param("expenseDate")String expenseDate);

	//userKey와 날짜로 상세데이터에 뿌려줄 transfer 정보 반환
	public List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> selectTAOMFAOMTByUserKeyAndTransferDate(@Param("userKey")int userKey, @Param("transferDate")String transferDate);

	//모든 Income Category 반환
	public List<IncomeCategory> selectAllIncomeCategory();

	//모든 expense_category 반환
	public List<ExpenseCategory> selectAllExpenseCategory();

	//userKey로 사용자가 등록한 자산항목과 자산명 가져오기
	public List<AssetsAssetsOfMember> selectAAOMByUserKey(@Param("userKey")int userKey);

	//userKey와 assetsId로 AOM LIST가져오기
	public List<AssetsOfMember> selectAOMByUserKeyAndAssetsId(@Param("userKey")int userKey, @Param("assetsId")int assetsId);

	//userKey와 년-월로 income의 sum가져오기
	public Integer selectSUMIncomeByDate(@Param("userKey")int userKey, @Param("incomeDate")String yearMonth);

//	userKey와 년-월로 expense의 sum가져오기
	public Integer selectSUMExpenseByDate(@Param("userKey")int userKey, @Param("expenseDate")String yearMonth);

	//memAssetId로 AOM 조회해서 반환
	public AssetsOfMember selectAOMByMemAssetId(@Param("memAssetId")int memAssetId);
}