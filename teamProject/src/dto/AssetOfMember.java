package dto;

public class AssetOfMember {

	private int userKey;
	private String userId;
	private String password;
	private String name;
	private String email;
	private int memAssetId;
	private String type;
	private int amount;
	private int assetsId;
	private String memo;
	private String assetsCategory;
	private String assetsName;
	
	public AssetOfMember() {
	}

	public AssetOfMember(int userKey, String userId, String password, String name, String email, int memAssetId,
			String type, int amount, int assetsId, String memo, String assetsCategory, String assetsName) {
		super();
		this.userKey = userKey;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.memAssetId = memAssetId;
		this.type = type;
		this.amount = amount;
		this.assetsId = assetsId;
		this.memo = memo;
		this.assetsCategory = assetsCategory;
		this.assetsName = assetsName;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMemAssetId() {
		return memAssetId;
	}

	public void setMemAssetId(int memAssetId) {
		this.memAssetId = memAssetId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(int assetsId) {
		this.assetsId = assetsId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAssetsCategory() {
		return assetsCategory;
	}

	public void setAssetsCategory(String assetsCategory) {
		this.assetsCategory = assetsCategory;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	@Override
	public String toString() {
		return "AssetOfMember [userKey=" + userKey + ", userId=" + userId + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", memAssetId=" + memAssetId + ", type=" + type + ", amount=" + amount
				+ ", assetsId=" + assetsId + ", memo=" + memo + ", assetsCategory=" + assetsCategory + ", assetsName="
				+ assetsName + "]";
	}


}
