package model;

public class Transaction {

	private int bookId;
	private String sellerId;
	private String buyerId;
	
	public Transaction() {	}
	
	public Transaction(int bookId, String sellerId, String buyerId) {
		super();
		this.bookId = bookId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
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
	
}
