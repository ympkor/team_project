package dto.main;

public class SumAmounts {
	private int sumIncome;
	private int sumExpense;
	public SumAmounts() {}
	public SumAmounts(int sumIncome, int sumExpense) {
		this.sumIncome = sumIncome;
		this.sumExpense = sumExpense;
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
		return "SumAmounts [sumIncome=" + sumIncome + ", sumExpense=" + sumExpense + "]";
	}
}
