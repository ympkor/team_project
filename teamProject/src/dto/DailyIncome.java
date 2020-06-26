package dto;

import java.time.LocalDate;

public class DailyIncome {
	private String icName;
	private int amount;
	private LocalDate incomeDate;
	private String assetsName;
	private String memo;
	
	public LocalDate getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
	}
	
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public String getIcName() {
		return icName;
	}
	public void setIcName(String icName) {
		this.icName = icName;
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
	@Override
	public String toString() {
		return "DailyIncome [icName=" + icName + ", amount=" + amount + ", incomeDate=" + incomeDate + ", assetsName="
				+ assetsName + ", memo=" + memo + "]";
	}
	
	
}
