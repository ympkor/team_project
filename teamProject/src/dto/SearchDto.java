package dto;

import java.time.LocalDate;

public class SearchDto {

	private int userKey;
	private int amount;
	private LocalDate date;
	private String assets;
	private String category;
	private String memo;
	
	public SearchDto() {}

	public SearchDto(int userKey, int amount, LocalDate date, String assets, String category, String memo) {
		super();
		this.userKey = userKey;
		this.amount = amount;
		this.date = date;
		this.assets = assets;
		this.category = category;
		this.memo = memo;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "SearchDto [userKey=" + userKey + ", amount=" + amount + ", date=" + date + ", assets=" + assets
				+ ", category=" + category + ", memo=" + memo + "]";
	}
	
}
