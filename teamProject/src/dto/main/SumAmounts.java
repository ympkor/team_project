package dto.main;

public class SumAmounts {
	private int sumIncome;
	private int sumExpense;
	private String selecDate;
	public SumAmounts() {}
		public SumAmounts(int sumIncome, int sumExpense, String selecDate) {
		this.sumIncome = sumIncome;
		this.sumExpense = sumExpense;
		this.selecDate = selecDate;
	}
	public String getSelecDate() {
			return selecDate;
	}
	public void setSelecDate(String selecDate) {
		this.selecDate = selecDate;
	}
	public int getSumIncome() {
		return sumIncome;
	}
	public void setSumIncome(int sumIncome) {
		this.sumIncome = sumIncome;
	}
	public int getSumExpense() {
		return sumExpense;
	}
	public void setSumExpense(int sumExpense) {
		this.sumExpense = sumExpense;
	}
	@Override
	public String toString() {
		return "SumAmounts [sumIncome=" + sumIncome + ", sumExpense=" + sumExpense + ", selecDate=" + selecDate + "]";
	}
}
