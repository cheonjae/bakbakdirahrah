package model;

public class Transaction {

	private int bookId;
	private String sellerId;
	private String buyerId;
	private int lastPrice;
	private String meetingDate;
	private String meetingPlace;
	private String meetingMemo;
	private int sellerCheck;
	private int buyerCheck;
	
	public Transaction() {	}
	
	public Transaction(int lastPrice, String meetingDate, String meetingPlace, String meetingMemo) { //for update
		super();
		this.lastPrice = lastPrice;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.meetingMemo = meetingMemo;
	}
	
		
	public Transaction(int bookId, String sellerId, String buyerId) { //for delete
		this.bookId = bookId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
	}
	
	// meetingMemo는 nullable -> 값 없이 생성 가능 
	public Transaction(int bookId, String sellerId, String buyerId, int lastPrice, String meetingDate,
			String meetingPlace, int sellerCheck, int buyerCheck) {
		super();
		this.bookId = bookId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.lastPrice = lastPrice;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.sellerCheck = sellerCheck;
		this.buyerCheck = buyerCheck;
	}

	public Transaction(int bookId, String sellerId, String buyerId, int lastPrice, String meetingDate,
			String meetingPlace, String meetingMemo, int sellerCheck, int buyerCheck) {
		super();
		this.bookId = bookId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.lastPrice = lastPrice;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.meetingMemo = meetingMemo;
		this.sellerCheck = sellerCheck;
		this.buyerCheck = buyerCheck;
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

	public int getSellerCheck() {
		return sellerCheck;
	}

	public void setSellerCheck(int sellerCheck) {
		this.sellerCheck = sellerCheck;
	}

	public int getBuyerCheck() {
		return buyerCheck;
	}

	public void setBuyerCheck(int buyerCheck) {
		this.buyerCheck = buyerCheck;
	}
	
}
