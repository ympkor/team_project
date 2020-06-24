package dto;

import java.time.LocalDateTime;

public class Income {
	
	private int incomeId;
	private int userKey;
	private int amount;
	private LocalDateTime incomeDate;
	private int bankId;
	private int typeId;
	private String memo;
	
	public Income() {
	}

	public Income(int incomeId, int userKey, int amount, LocalDateTime incomeDate, int bankId, int typeId,
			String memo) {
		super();
		this.incomeId = incomeId;
		this.userKey = userKey;
		this.amount = amount;
		this.incomeDate = incomeDate;
		this.bankId = bankId;
		this.typeId = typeId;
		this.memo = memo;
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

	public LocalDateTime getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(LocalDateTime incomeDate) {
		this.incomeDate = incomeDate;
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
		return "Income [incomeId=" + incomeId + ", userKey=" + userKey + ", amount=" + amount + ", incomeDate="
				+ incomeDate + ", bankId=" + bankId + ", typeId=" + typeId + ", memo=" + memo + "]";
	}

}
