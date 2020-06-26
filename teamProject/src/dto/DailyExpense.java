package dto;

import java.time.LocalDate;

public class DailyExpense {
	private String ecName;
	private int amount;
	private LocalDate expenseDate;
	private String assetsName;
	private String memo;
	
	public LocalDate getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	@Override
	public String toString() {
		return "DailyExpense [ecName=" + ecName + ", amount=" + amount + ", expenseDate=" + expenseDate
				+ ", assetsName=" + assetsName + ", memo=" + memo + "]";
	}
	
	
	
	
}
