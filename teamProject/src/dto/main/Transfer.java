package dto.main;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Transfer {
	private int transferId;
	private int userKey;
	private int memAssetIdFrom;
	private int memAssetIdTo;
	private int amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate transferDate;
	private String memo;
	public Transfer() {}
	public Transfer(int transferId, int userKey, int memAssetIdFrom, int memAssetIdTo, int amount,
			LocalDate transferDate, String memo) {
		this.transferId = transferId;
		this.userKey = userKey;
		this.memAssetIdFrom = memAssetIdFrom;
		this.memAssetIdTo = memAssetIdTo;
		this.amount = amount;
		this.transferDate = transferDate;
		this.memo = memo;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
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
	@Override
	public String toString() {
		return "Transfer [transferId=" + transferId + ", userKey=" + userKey + ", memAssetIdFrom=" + memAssetIdFrom
				+ ", memAssetIdTo=" + memAssetIdTo + ", amount=" + amount + ", transferDate=" + transferDate + ", memo="
				+ memo + "]";
	}
}
