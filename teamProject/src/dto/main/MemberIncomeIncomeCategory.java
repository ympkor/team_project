package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberIncomeIncomeCategory {
	private int userKey;
	private String icName;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate incomeDate;
	public MemberIncomeIncomeCategory() {}
	public MemberIncomeIncomeCategory(int userKey, String icName, int amount, LocalDate incomeDate) {
		this.userKey = userKey;
		this.icName = icName;
		this.amount = amount;
		this.incomeDate = incomeDate;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getIcName() {
		return icName;
	}
	public void setIcName(String icName) {
		this.icName = icName;
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
		return "MemberIncomeIncomeCategory [userKey=" + userKey + ", icName=" + icName + ", amount=" + amount
				+ ", incomeDate=" + incomeDate + "]";
	}
}
