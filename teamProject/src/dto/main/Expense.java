package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Expense {
	private int expenseId;
	private int userKey;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expenseDate;
	private int assetsId;
	private int ecId;
	private String memo;
	public Expense() {}
	public Expense(int expenseId, int userKey, int amount, LocalDate expenseDate, int assetsId, int ecId, String memo) {
		this.expenseId = expenseId;
		this.userKey = userKey;
		this.amount = amount;
		this.expenseDate = expenseDate;
		this.assetsId = assetsId;
		this.ecId = ecId;
		this.memo = memo;
	}
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
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
		return "Expense [expenseId=" + expenseId + ", userKey=" + userKey + ", amount=" + amount + ", expenseDate="
				+ expenseDate + ", assetsId=" + assetsId + ", ecId=" + ecId + ", memo=" + memo + "]";
	}
}
