package dto.main;

public class IncomeCategory {
	private int icId;
	private String icName;
	public IncomeCategory() {}
	public IncomeCategory(int icId, String icName) {
		this.icId = icId;
		this.icName = icName;
	}
	public int getIcId() {
		return icId;
	}
	public void setIcId(int icId) {
		this.icId = icId;
	}
	public String getIcName() {
		return icName;
	}
	public void setIcName(String icName) {
		this.icName = icName;
	}
}