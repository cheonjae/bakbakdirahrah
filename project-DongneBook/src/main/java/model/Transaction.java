package model;

public class Transaction {

	private int bookId;
	private String sellerId;
	private String buyerId;
	private int lastPrice;
	private String meetingDate;
	private String meetingPlace;
	private String meetingMemo;
	
	public Transaction() {	}

	public Transaction(int bookId, String sellerId, String buyerId, int lastPrice, String meetingDate,
			String meetingPlace, String meetingMemo) {
		super();
		this.bookId = bookId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.lastPrice = lastPrice;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.meetingMemo = meetingMemo;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public int getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(int lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public String getMeetingMemo() {
		return meetingMemo;
	}

	public void setMeetingMemo(String meetingMemo) {
		this.meetingMemo = meetingMemo;
	}
	
}
