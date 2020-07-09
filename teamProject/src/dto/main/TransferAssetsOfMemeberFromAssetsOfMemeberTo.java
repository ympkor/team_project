package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TransferAssetsOfMemeberFromAssetsOfMemeberTo {
	private int userKey;
	private int transferId;
	private int memAssetIdFrom;
	private int memAssetIdTo;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate transferDate;
	private String memo;
	private int assetsId;
	private String assetsNameFrom;
	private String aomNameFrom;
	private int assetsIdTo;
	private String assetsNameTo;
	private String aomNameTo;
	public TransferAssetsOfMemeberFromAssetsOfMemeberTo() {}
	public TransferAssetsOfMemeberFromAssetsOfMemeberTo(int userKey, int transferId, int memAssetIdFrom,
			int memAssetIdTo, int amount, LocalDate transferDate, String memo, int assetsId, String assetsNameFrom,
			String aomNameFrom, int assetsIdTo, String assetsNameTo, String aomNameTo) {
		this.userKey = userKey;
		this.transferId = transferId;
		this.memAssetIdFrom = memAssetIdFrom;
		this.memAssetIdTo = memAssetIdTo;
		this.amount = amount;
		this.transferDate = transferDate;
		this.memo = memo;
		this.assetsId = assetsId;
		this.assetsNameFrom = assetsNameFrom;
		this.aomNameFrom = aomNameFrom;
		this.assetsIdTo = assetsIdTo;
		this.assetsNameTo = assetsNameTo;
		this.aomNameTo = aomNameTo;
	}
	public String getAssetsNameFrom() {
		return assetsNameFrom;
	}
	public void setAssetsNameFrom(String assetsNameFrom) {
		this.assetsNameFrom = assetsNameFrom;
	}
	public String getAssetsNameTo() {
		return assetsNameTo;
	}
	public void setAssetsNameTo(String assetNameTo) {
		this.assetsNameTo = assetNameTo;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getMemAssetIdFrom() {
		return memAssetIdFrom;
	}
	public void setMemAssetIdFrom(int memAssetIdFrom) {
		this.memAssetIdFrom = memAssetIdFrom;
	}
	public int getMemAssetIdTo() {
		return memAssetIdTo;
	}
	public void setMemAssetIdTo(int memAssetIdTo) {
		this.memAssetIdTo = memAssetIdTo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(LocalDate transferDate) {
		this.transferDate = transferDate;
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
	public String getAomNameFrom() {
		return aomNameFrom;
	}
	public void setAomNameFrom(String aomNameFrom) {
		this.aomNameFrom = aomNameFrom;
	}
	public int getAssetsIdTo() {
		return assetsIdTo;
	}
	public void setAssetsIdTo(int assetsIdTo) {
		this.assetsIdTo = assetsIdTo;
	}
	public String getAomNameTo() {
		return aomNameTo;
	}
	public void setAomNameTo(String aomNameTo) {
		this.aomNameTo = aomNameTo;
	}
	@Override
	public String toString() {
		return "TransferAssetsOfMemeberFromAssetsOfMemeberTo [userKey=" + userKey + ", transferId=" + transferId
				+ ", memAssetIdFrom=" + memAssetIdFrom + ", memAssetIdTo=" + memAssetIdTo + ", amount=" + amount
				+ ", transferDate=" + transferDate + ", memo=" + memo + ", assetsId=" + assetsId + ", assetsNameFrom="
				+ assetsNameFrom + ", aomNameFrom=" + aomNameFrom + ", assetsIdTo=" + assetsIdTo + ", assetsNameTo="
				+ assetsNameTo + ", aomNameTo=" + aomNameTo + "]";
	}
}
