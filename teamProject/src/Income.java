package dto;

import java.time.LocalDate;

public class Income {
	
	private int incomeId;
	private int userKey;
	private int amount;
	private LocalDate incomeDate;
	private int assetsId;
	private int icId;
	private String memo;
	
	public Income() {
	}

	public Income(int incomeId, int userKey, int amount, LocalDate incomeDate, int assetsId, int icId,
			String memo) {
		super();
		this.incomeId = incomeId;
		this.userKey = userKey;
		this.amount = amount;
		this.incomeDate = incomeDate;
		this.assetsId = assetsId;
		this.icId = icId;
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

	public LocalDate getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
	}

	public int getassetsId() {
		return assetsId;
	}

	public void setassetsId(int assetsId) {
		this.assetsId = assetsId;
	}

	public int geticId() {
		return icId;
	}

	public void seticId(int icId) {
		this.icId = icId;
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
				+ incomeDate + ", assetsId=" + assetsId + ", icId=" + icId + ", memo=" + memo + "]";
	}

}
