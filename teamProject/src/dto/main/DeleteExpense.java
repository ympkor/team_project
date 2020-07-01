package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class DeleteExpense {
	private int userKey;
	private String type;
	private int expenseId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expenseDate;
	private int assetsId;
	private int memAssetId;
	private int ecId;
	private int amout;
	private String memo;
	public DeleteExpense() {}
	public DeleteExpense(int userKey, String type, int expenseId, LocalDate expenseDate, int assetsId, int memAssetId,
			int ecId, int amout, String memo) {
		this.userKey = userKey;
		this.type = type;
		this.expenseId = expenseId;
		this.expenseDate = expenseDate;
		this.assetsId = assetsId;
		this.memAssetId = memAssetId; 
		this.ecId = ecId;
		this.amout = amout;
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
	public int getEcId() {
		return ecId;
	}
	public void setEcId(int ecId) {
		this.ecId = ecId;
	}
	public int getAmout() {
		return amout;
	}
	public void setAmout(int amout) {
		this.amout = amout;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "DeleteExpense [userKey=" + userKey + ", type=" + type + ", expenseId=" + expenseId + ", expenseDate="
				+ expenseDate + ", assetsId=" + assetsId + ", memAssetId=" + memAssetId + ", ecId=" + ecId + ", amout="
				+ amout + ", memo=" + memo + "]";
	}
}
