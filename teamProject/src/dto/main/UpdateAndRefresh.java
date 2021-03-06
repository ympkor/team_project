package dto.main;

import java.util.List;

public class UpdateAndRefresh {
	private Calendar cal;
	private List<MemberIncomeIncomeCategory> miicList;
	private List<MemberExpenseExpenseCategory> meecList;
	private List<IncomeIncomeCategoryAssets> iicaList;
	private List<ExpenseExpenseCategoryAssets> eecaList;
	private List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> taomfaomtList;
	private List<Transfer> tbmList;
	private SumAmounts sumAmounts;
	public UpdateAndRefresh() {}
	public UpdateAndRefresh(Calendar cal, List<MemberIncomeIncomeCategory> miicList,
			List<MemberExpenseExpenseCategory> meecList, List<IncomeIncomeCategoryAssets> iicaList,
			List<ExpenseExpenseCategoryAssets> eecaList,
			List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> taomfaomtList, List<Transfer> tbmList,
			SumAmounts sumAmounts) {
		this.cal = cal;
		this.miicList = miicList;
		this.meecList = meecList;
		this.iicaList = iicaList;
		this.eecaList = eecaList;
		this.taomfaomtList = taomfaomtList;
		this.tbmList = tbmList;
		this.sumAmounts = sumAmounts;
	}
	public List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> getTaomfaomtList() {
		return taomfaomtList;
	}
	public void setTaomfaomtList(List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> taomfaomtList) {
		this.taomfaomtList = taomfaomtList;
	}
	public List<Transfer> getTbmList() {
		return tbmList;
	}
	public void setTbmList(List<Transfer> tbmList) {
		this.tbmList = tbmList;
	}
	public Calendar getCal() {
		return cal;
	}
	public void setCal(Calendar cal) {
		this.cal = cal;
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
		return "UpdateAndRefresh [cal=" + cal + ", miicList=" + miicList + ", meecList=" + meecList + ", iicaList="
				+ iicaList + ", eecaList=" + eecaList + ", taomfaomtList=" + taomfaomtList + ", tbmList=" + tbmList
				+ ", sumAmounts=" + sumAmounts + "]";
	}
}
