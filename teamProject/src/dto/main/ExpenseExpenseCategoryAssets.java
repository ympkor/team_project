package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpenseExpenseCategoryAssets {
	private int expenseId;
	private int ecId;
	private String ecName;
	private String memo;
	private int assetsId;
	private String assetsName;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expenseDate;
	public ExpenseExpenseCategoryAssets() {}
	public ExpenseExpenseCategoryAssets(int expensdId, int ecId, String ecName, String memo, int assetsId, String assetsName,
			int amount, LocalDate expenseDate) {
		this.expenseId = expensdId;
		this.ecId = ecId;
		this.ecName = ecName;
		this.memo = memo;
		this.assetsId = assetsId;
		this.assetsName = assetsName;
		this.amount = amount;
		this.expenseDate = expenseDate;
	}
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public int getEcId() {
			return ecId;
		}
	public void setEcId(int ecId) {
		this.ecId = ecId;
	}
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
}