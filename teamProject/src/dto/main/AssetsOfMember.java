package dto.main;

public class AssetsOfMember {
	private int memAssetId;
	private int userKey;
	private String type;
	private long amount;
	private int assetsId;
	private String memo;
	public AssetsOfMember() {}
	public AssetsOfMember(int memAssetId, int userKey, String type, long amount, int assetsId, String memo) {
		super();
		this.memAssetId = memAssetId;
		this.userKey = userKey;
		this.type = type;
		this.amount = amount;
		this.assetsId = assetsId;
		this.memo = memo;
	}
	public int getMemAssetId() {
		return memAssetId;
	}
	public void setMemAssetId(int memAssetId) {
		this.memAssetId = memAssetId;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
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
	@Override
	public String toString() {
		return "AssetsOfMember [memAssetId=" + memAssetId + ", userKey=" + userKey + ", type=" + type + ", amount="
				+ amount + ", assetsId=" + assetsId + ", memo=" + memo + "]";
	}
}
