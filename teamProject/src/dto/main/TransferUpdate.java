package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TransferUpdate {
	private int userKey;
	private int category;
	private int transferId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate transferDate;
	private int assetsId;
	private int memAssetIdFrom;
	private int newMemAssetIdFrom;
	private int memAssetIdTo;
	private int newMemAssetIdTo;
	private int ecId;
	private int icId;
	private int amount;
	private int newAmount;
	private String memo;
	public TransferUpdate() {}
	public TransferUpdate(int userKey, int category, int transferId, LocalDate transferDate, int assetsId, int memAssetIdFrom,
			int newMemAssetIdFrom, int memAssetIdTo, int newMemAssetIdTo, int ecId, int icId, int amount, int newAmount,
			String memo) {
		this.userKey = userKey;
		this.category = category;
		this.transferId = transferId;
		this.transferDate = transferDate;
		this.assetsId = assetsId;
		this.memAssetIdFrom = memAssetIdFrom;
		this.newMemAssetIdFrom = newMemAssetIdFrom;
		this.memAssetIdTo = memAssetIdTo;
		this.newMemAssetIdTo = newMemAssetIdTo;
		this.ecId = ecId;
		this.icId = icId;
		this.amount = amount;
		this.newAmount = newAmount;
		this.memo = memo;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public LocalDate getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(LocalDate transferDate) {
		this.transferDate = transferDate;
	}
	public int getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(int assetsId) {
		this.assetsId = assetsId;
	}
	public int getMemAssetIdFrom() {
		return memAssetIdFrom;
	}
	public void setMemAssetIdFrom(int memAssetIdFrom) {
		this.memAssetIdFrom = memAssetIdFrom;
	}
	public int getNewMemAssetIdFrom() {
		return newMemAssetIdFrom;
	}
	public void setNewMemAssetIdFrom(int newMemAssetIdFrom) {
		this.newMemAssetIdFrom = newMemAssetIdFrom;
	}
	public int getMemAssetIdTo() {
		return memAssetIdTo;
	}
	public void setMemAssetIdTo(int memAssetIdTo) {
		this.memAssetIdTo = memAssetIdTo;
	}
	public int getNewMemAssetIdTo() {
		return newMemAssetIdTo;
	}
	public void setNewMemAssetIdTo(int newMemAssetIdTo) {
		this.newMemAssetIdTo = newMemAssetIdTo;
	}
	public int getEcId() {
		return ecId;
	}
	public void setEcId(int ecId) {
		this.ecId = ecId;
	}
	public int getIcId() {
		return icId;
	}
	public void setIcId(int icId) {
		this.icId = icId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(int newAmount) {
		this.newAmount = newAmount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "TransferUpdate [userKey=" + userKey + ", category=" + category + ", transferId=" + transferId
				+ ", transferDate=" + transferDate + ", assetsId=" + assetsId + ", memAssetIdFrom=" + memAssetIdFrom
				+ ", newMemAssetIdFrom=" + newMemAssetIdFrom + ", memAssetIdTo=" + memAssetIdTo + ", newMemAssetIdTo="
				+ newMemAssetIdTo + ", ecId=" + ecId + ", icId=" + icId + ", amount=" + amount + ", newAmount="
				+ newAmount + ", memo=" + memo + "]";
	}
}
