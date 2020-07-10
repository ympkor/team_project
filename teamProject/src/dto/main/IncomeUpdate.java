package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class IncomeUpdate {
	private int category;
	private int incomeId;
	private int userKey;
	private int amount;
	private int newAmount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate incomeDate;
	private int memAssetId;
	private int newMemAssetId;
	private int memAssetIdTo;
	private int assetsId;
	private int icId;
	private int ecId;
	private String memo;
	public IncomeUpdate() {}
	public IncomeUpdate(int category, int incomeId, int userKey, int amount, int newAmount, LocalDate incomeDate,
			int memAssetId, int newMemAssetId, int memAssetIdTo, int assetsId, int icId, int ecId, String memo) {
		this.category = category;
		this.incomeId = incomeId;
		this.userKey = userKey;
		this.amount = amount;
		this.newAmount = newAmount;
		this.incomeDate = incomeDate;
		this.memAssetId = memAssetId;
		this.newMemAssetId = newMemAssetId;
		this.memAssetIdTo = memAssetIdTo;
		this.assetsId = assetsId;
		this.icId = icId;
		this.ecId = ecId;
		this.memo = memo;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
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
	public int getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(int newAmount) {
		this.newAmount = newAmount;
	}
	public LocalDate getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
	}
	public int getMemAssetId() {
		return memAssetId;
	}
	public void setMemAssetId(int memAssetId) {
		this.memAssetId = memAssetId;
	}
	public int getNewMemAssetId() {
		return newMemAssetId;
	}
	public void setNewMemAssetId(int newMemAssetId) {
		this.newMemAssetId = newMemAssetId;
	}
	public int getMemAssetIdTo() {
		return memAssetIdTo;
	}
	public void setMemAssetIdTo(int memAssetIdTo) {
		this.memAssetIdTo = memAssetIdTo;
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
	public int getEcId() {
		return ecId;
	}
	public void setEcId(int ecId) {
		this.ecId = ecId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "IncomeUpdate [category=" + category + ", incomeId=" + incomeId + ", userKey=" + userKey + ", amount="
				+ amount + ", newAmount=" + newAmount + ", incomeDate=" + incomeDate + ", memAssetId=" + memAssetId
				+ ", newMemAssetId=" + newMemAssetId + ", memAssetIdTo=" + memAssetIdTo + ", assetsId=" + assetsId
				+ ", icId=" + icId + ", ecId=" + ecId + ", memo=" + memo + "]";
	}
}