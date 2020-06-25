package dto;

public class IncomeCategory {

	private int icId;
	private String icName;
	
	public IncomeCategory() {
	}

	public IncomeCategory(int icId, String icName) {
		super();
		this.icId = icId;
		this.icName = icName;
	}

	public int geticId() {
		return icId;
	}

	public void seticId(int icId) {
		this.icId = icId;
	}

	public String geticName() {
		return icName;
	}

	public void seticName(String icName) {
		this.icName = icName;
	}
	
	@Override
	public String toString() {
		return "Type [icId=" + icId + ", icName=" + icName + "]";
	}
		
}
