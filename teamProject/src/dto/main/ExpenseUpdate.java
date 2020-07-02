package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpenseUpdate {
	private int userkey;
	private int category;
	private int expenseId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expenseDate;
	private int assetsId;
	private int memAssetId;
	private int newMemAssetId;
	private int memAssetIdTo;
	private int ecId;
	private int icId;
	private int amount;
	private int newAmount;
	private String memo;
	public ExpenseUpdate() {}
	public ExpenseUpdate(int userkey, int category, int expenseId, LocalDate expenseDate, int assetsId, int memAssetId,
			int newMemAssetId, int memAssetIdTo, int ecId, int icId, int amount, int newAmount, String memo) {
		this.userkey = userkey;
		this.category = category;
		this.expenseId = expenseId;
		this.expenseDate = expenseDate;
		this.assetsId = assetsId;
		this.memAssetId = memAssetId;
		this.newMemAssetId = newMemAssetId;
		this.memAssetIdTo = memAssetIdTo;
		this.ecId = ecId;
		this.icId = icId;
		this.amount = amount;
		this.newAmount = newAmount;
		this.memo = memo;
	}
	public int getMemAssetIdTo() {
		return memAssetIdTo;
	}
	public void setMemAssetIdTo(int memAssetIdTo) {
		this.memAssetIdTo = memAssetIdTo;
	}
	public int getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(int newAmount) {
		this.newAmount = newAmount;
	}
	public int getUserkey() {
		return userkey;
	}
	public void setUserkey(int userkey) {
		this.userkey = userkey;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public LocalDate getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
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
	public int getNewMemAssetId() {
		return newMemAssetId;
	}
	public void setNewMemAssetId(int newMemAssetId) {
		this.newMemAssetId = newMemAssetId;
	}
	public int getEcId() {
		return ecId;
	}
	public void setEcId(int ecId) {
		this.ecId = ecId;
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
		return "ExpenseUpdate [userkey=" + userkey + ", category=" + category + ", expenseId=" + expenseId
				+ ", expenseDate=" + expenseDate + ", assetsId=" + assetsId + ", memAssetId=" + memAssetId
				+ ", newMemAssetId=" + newMemAssetId + ", memAssetIdTo=" + memAssetIdTo + ", ecId=" + ecId + ", icId="
				+ icId + ", amount=" + amount + ", newAmount=" + newAmount + ", memo=" + memo + "]";
	}
}
