package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Income {
	private int incomeId;
	private int userKey;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate incomeDate;
	private int assetsId;
	private int icId;
	private String memo;
	private int memAssetId;
	public Income() {}
	public Income(int incomeId, int userKey, int amount, LocalDate incomeDate, int assetsId, int icId, String memo, int memAssetId) {
		this.incomeId = incomeId;
		this.userKey = userKey;
		this.amount = amount;
		this.incomeDate = incomeDate;
		this.assetsId = assetsId;
		this.icId = icId;
		this.memo = memo;
		this.memAssetId = memAssetId;
	}
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
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
	public int getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(int assetsId) {
		this.assetsId = assetsId;
	}
	public int getIcId() {
		return icId;
	}
	public void setIcId(int icId) {
		this.icId = icId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getMemAssetId() {
		return memAssetId;
	}
	public void setMemAssetId(int memAssetId) {
		this.memAssetId = memAssetId;
	}
	@Override
	public String toString() {
		return "Income [incomeId=" + incomeId + ", userKey=" + userKey + ", amount=" + amount + ", incomeDate="
				+ incomeDate + ", assetsId=" + assetsId + ", icId=" + icId + ", memo=" + memo + ", memAssetId="
				+ memAssetId + "]";
	}
}
