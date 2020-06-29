package dto.main;

public class AssetsAssetsOfMember {
	private int memAssetId;
	private int assetsId;
	private String assetsCategory;
	private String assetsName;
	private String memo;
	public AssetsAssetsOfMember() {}
	public AssetsAssetsOfMember(int memAssetId, int assetsId, String assetsCategory, String assetsName, String memo) {
		this.memAssetId = memAssetId;
		this.assetsId = assetsId;
		this.assetsCategory = assetsCategory;
		this.assetsName = assetsName;
		this.memo = memo;
	}
	public int getMemAssetId() {
		return memAssetId;
	}
	public void setMemAssetId(int memAssetId) {
		this.memAssetId = memAssetId;
	}
	public int getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(int assetsId) {
		this.assetsId = assetsId;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "AssetsAssetsOfMember [memAssetId=" + memAssetId + ", assetsId=" + assetsId + ", assetsCategory="
				+ assetsCategory + ", assetsName=" + assetsName + ", memo=" + memo + "]";
	}
}
