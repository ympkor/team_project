package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class IncomeIncomeCategoryAssets {
	private int userKey;
	private int incomeId;
	private int icId;
	private String icName;
	private String memo;
	private int memAssetId;
	private String aomName;
	private int assetsId;
	private String assetsName;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate incomeDate;
	public IncomeIncomeCategoryAssets() {}
	public IncomeIncomeCategoryAssets(int userKey, int incomeId, int icId, String icName, String memo, int memAssetId,
			String aomName, int assetsId, String assetsName, int amount, LocalDate incomeDate) {
		this.userKey = userKey;
		this.incomeId = incomeId;
		this.icId = icId;
		this.icName = icName;
		this.memo = memo;
		this.memAssetId = memAssetId;
		this.aomName = aomName;
		this.assetsId = assetsId;
		this.assetsName = assetsName;
		this.amount = amount;
		this.incomeDate = incomeDate;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
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
	public int getMemAssetId() {
		return memAssetId;
	}
	public void setMemAssetId(int memAssetId) {
		this.memAssetId = memAssetId;
	}
	public String getAomName() {
		return aomName;
	}
	public void setAomName(String aomName) {
		this.aomName = aomName;
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
		return "IncomeIncomeCategoryAssets [userKey=" + userKey + ", incomeId=" + incomeId + ", icId=" + icId
				+ ", icName=" + icName + ", memo=" + memo + ", memAssetId=" + memAssetId + ", aomName=" + aomName
				+ ", assetsId=" + assetsId + ", assetsName=" + assetsName + ", amount=" + amount + ", incomeDate="
				+ incomeDate + "]";
	}
}
