package dto.main;

public class ExpenseCategory {
	private int ecId;
	private String ecName;
	public ExpenseCategory() {}
	public ExpenseCategory(int ecId, String ecName) {
		this.ecId = ecId;
		this.ecName = ecName;
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
}