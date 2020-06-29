package dto.main;

import java.util.List;

public class UpdateAndRefresh {
	private List<MemberIncomeIncomeCategory> miicList;
	private List<MemberExpenseExpenseCategory> meecList;
	private List<IncomeIncomeCategoryAssets> iicaList;
	private List<ExpenseExpenseCategoryAssets> eecaList;
	private SumAmounts sumAmounts;
	public UpdateAndRefresh() {}
	public UpdateAndRefresh(List<MemberIncomeIncomeCategory> miicList, List<MemberExpenseExpenseCategory> meecList,
			List<IncomeIncomeCategoryAssets> iicaList, List<ExpenseExpenseCategoryAssets> eecaList,
			SumAmounts sumAmounts) {
		this.miicList = miicList;
		this.meecList = meecList;
		this.iicaList = iicaList;
		this.eecaList = eecaList;
		this.sumAmounts = sumAmounts;
	}
	public List<IncomeIncomeCategoryAssets> getIicaList() {
		return iicaList;
	}
	public void setIicaList(List<IncomeIncomeCategoryAssets> iicaList) {
		this.iicaList = iicaList;
	}

	public List<ExpenseExpenseCategoryAssets> getEecaList() {
		return eecaList;
	}

	public void setEecaList(List<ExpenseExpenseCategoryAssets> eecaList) {
		this.eecaList = eecaList;
	}

	public List<MemberIncomeIncomeCategory> getMiicList() {
		return miicList;
	}
	public void setMiicList(List<MemberIncomeIncomeCategory> miicList) {
		this.miicList = miicList;
	}
	public List<MemberExpenseExpenseCategory> getMeecList() {
		return meecList;
	}
	public void setMeecList(List<MemberExpenseExpenseCategory> meecList) {
		this.meecList = meecList;
	}
	public SumAmounts getSumAmounts() {
		return sumAmounts;
	}
	public void setSumAmounts(SumAmounts sumAmounts) {
		this.sumAmounts = sumAmounts;
	}
	@Override
	public String toString() {
		return "UpdateAndRefresh [miicList=" + miicList + ", meecList=" + meecList + ", iicaList=" + iicaList
				+ ", eecaList=" + eecaList + ", sumAmounts=" + sumAmounts + "]";
	}
}
