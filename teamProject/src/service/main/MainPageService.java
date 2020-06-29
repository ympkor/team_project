package service.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.main.AssetsAssetsOfMember;
import dto.main.AssetsOfMember;
import dto.main.ExpenseCategory;
import dto.main.ExpenseExpenseCategoryAssets;
import dto.main.IncomeCategory;
import dto.main.IncomeIncomeCategoryAssets;
import dto.main.MemberExpenseExpenseCategory;
import dto.main.MemberIncomeIncomeCategory;
import dto.main.SumAmounts;
import mapper.main.MainPageMapper;

@Service
public class MainPageService {
	@Autowired
	private MainPageMapper mainPageMapper;
	
	//userKey와 selecDate로 월에 해당되는 수입을 리스트로 반환
	public List<MemberIncomeIncomeCategory> selectMIICByUserKeyAndDate(int userKey, String incomeDate) {
		incomeDate = incomeDate+"%";
//		System.out.println("서비스 객체의 메서드가 실행됐다. 받은 값은 : "+selecDate+", userkey : "+userKey);
		List<MemberIncomeIncomeCategory> miicList = mainPageMapper.selecMIICByUserKeyAndIncomeDate(userKey, incomeDate);
//		System.out.println("서비스에서 리턴된 리스트 : "+miicList);
		if(miicList.size()==0) {return null;}
		else {return miicList;}
	}
	
	//userKey와 selecDate로 월에 해당되는 지출을 리스트로 반환
	public List<MemberExpenseExpenseCategory> selectMEECByUserKeyAndDate(int userKey, String expenseDate) {
		expenseDate = expenseDate+"%";
		List<MemberExpenseExpenseCategory> meecList = mainPageMapper.selectMEECBYUserKeyAndExpenseDate(userKey, expenseDate);
		if(meecList.size()==0) {return null;}
		else {return meecList;}
	}
	
	//userKey와 선택한 날짜로 그 날짜에 해당되는 수입을 리스트로 반환
	public List<IncomeIncomeCategoryAssets> selectIICAByUserKeyAndDate(int userKey, String incomeDate) {
		List<IncomeIncomeCategoryAssets> iicaList = mainPageMapper.selectIICAByUserKeyAndIncomeDate(userKey, incomeDate);
		if(iicaList.size()==0) {return null;}
		else {return iicaList;}
	}
	
	//userKey와 선택한 날짜로 그 날짜에 해당되는 지출을 리스트로 반환
	public List<ExpenseExpenseCategoryAssets> selectEECAByUserKeyAndDate(int userKey, String expenseDate) {
		List<ExpenseExpenseCategoryAssets> eecaList = mainPageMapper.selectEECAByUserKeyAndExpenseDate(userKey, expenseDate);
		if(eecaList.size()==0) {return null;}
		else {return eecaList;}
	}

	//모든 income category의 데이터 가져오기
	public List<IncomeCategory> selectAllIC() {
		List<IncomeCategory> icList = mainPageMapper.selectAllIncomeCategory();
		return icList;
	}

	//모든 Expense Category의 데이터 가져오기
	public List<ExpenseCategory> selectAllEc() {
		List<ExpenseCategory> ecList = mainPageMapper.selectAllExpenseCategory();
		return ecList;
	}

	//user key에 맞는 자산 항목 가져오기과 자산명 가져오기
	public List<AssetsAssetsOfMember> selectAAOMByUserKey(int userKey) {
		List<AssetsAssetsOfMember> aaomList = mainPageMapper.selectAAOMByUserKey(userKey);
		if(aaomList.size()==0) {return null;}
		else {return aaomList;}
	}

	//user key와 assets id로 해당 유저의 AOM LIST를 반환
	public List<AssetsOfMember> selectAOMByUserKeyAndAssetsId(int userKey, int assetsId) {
		List<AssetsOfMember> aomList = mainPageMapper.selectAOMByUserKeyAndAssetsId(userKey, assetsId);
		if(aomList.size()==0) {return null;}
		else {return aomList;}
	}

	//userKey와 년월로 sum 값 구해서 반환
	public SumAmounts selectSUMIEByUserKeyAndDate(int userKey, String yearMonth) {
		SumAmounts sumAmounts = new SumAmounts();
		yearMonth = yearMonth+"%";
		sumAmounts.setSumIncome(mainPageMapper.selectSUMIncomeByDate(userKey, yearMonth));
		sumAmounts.setSumExpense(mainPageMapper.selectSUMExpenseByDate(userKey, yearMonth));
		return null;
	}
}
