package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class DeleteIncome {
	private int userKey;
	private String type;
	private int incomeId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate incomeDate;
	private int assetsId;
	private int memAssetId;
	private int icId;
	private int amount;
	private String memo;
	public DeleteIncome() {}
	public DeleteIncome(int userKey, String type, int incomeId, LocalDate incomeDate, int assetsId, int memAssetId, int icId,
			int amount, String memo) {
		this.type = type;
		this.incomeId = incomeId;
		this.incomeDate = incomeDate;
		this.assetsId = assetsId;
		this.memAssetId = memAssetId;
		this.icId = icId;
		this.amount = amount;
		this.memo = memo;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
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
	public int getMemAssetId() {
		return memAssetId;
	}
	public void setMemAssetId(int memAssetId) {
		this.memAssetId = memAssetId;
	}
	public int getIcId() {
		return icId;
	}
	public void setIcId(int icId) {
		this.icId = icId;
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
		return "DeleteIncome [userKey=" + userKey + ", type=" + type + ", incomeId=" + incomeId + ", incomeDate="
				+ incomeDate + ", assetsId=" + assetsId + ", memAssetId=" + memAssetId + ", icId=" + icId + ", amount="
				+ amount + ", memo=" + memo + "]";
	}
}
