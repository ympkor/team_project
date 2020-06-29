package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class IncomeIncomeCategoryAssets {
	private int incomeId;
	private int icId;
	private String icName;
	private String memo;
	private int assetsId;
	private String assetsName;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate incomeDate;
	public IncomeIncomeCategoryAssets() {}
	public IncomeIncomeCategoryAssets(int incomeId, int icId, String icName, String memo, int assetsId, String assetsName, int amount,
			LocalDate incomeDate) {
		this.incomeId = incomeId;
		this.icId = icId;
		this.icName = icName;
		this.memo = memo;
		this.assetsId = assetsId;
		this.assetsName = assetsName;
		this.amount = amount;
		this.incomeDate = incomeDate;
	}
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	public int getIcId() {
		return icId;
	}
	public void setIcId(int icId) {
		this.icId = icId;
	}
	public int getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(int assetsId) {
		this.assetsId = assetsId;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
	}
	@Override
	public String toString() {
		return "IncomeIncomeCategoryAssets [icId=" + icId + ", icName=" + icName + ", memo=" + memo + ", assetsId="
				+ assetsId + ", assetsName=" + assetsName + ", amount=" + amount + ", incomeDate=" + incomeDate + "]";
	}
}
