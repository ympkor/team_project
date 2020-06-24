package dto;

import java.time.LocalDateTime;

public class Expense {

	private int expenseId;
	private int userKey;
	private int amount;
	private LocalDateTime expenseDate;
	private int bankId;
	private int typeId;
	private String memo;
	
	public Expense() {
	}

	public Expense(int expenseId, int userKey, int amount, LocalDateTime expenseDate, int bankId, int typeId,
			String memo) {
		super();
		this.expenseId = expenseId;
		this.userKey = userKey;
		this.amount = amount;
		this.expenseDate = expenseDate;
		this.bankId = bankId;
		this.typeId = typeId;
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

	public LocalDateTime getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(LocalDateTime expenseDate) {
		this.expenseDate = expenseDate;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
				+ expenseDate + ", bankId=" + bankId + ", typeId=" + typeId + ", memo=" + memo + "]";
	}
}
