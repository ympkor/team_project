package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberExpenseExpenseCategory {
	private int userKey;
	private String ecName;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expenseDate;
	public MemberExpenseExpenseCategory() {}
	public MemberExpenseExpenseCategory(int userKey, String ecName, int amount, LocalDate expenseDate) {
		super();
		this.userKey = userKey;
		this.ecName = ecName;
		this.amount = amount;
		this.expenseDate = expenseDate;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
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
	@Override
	public String toString() {
		return "MemberExpenseExpenseCategory [userKey=" + userKey + ", ecName=" + ecName + ", amount=" + amount
				+ ", expenseDate=" + expenseDate + "]";
	}
}
