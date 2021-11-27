package model;

public class Wishlist {

	private String userId;
	private int bookId;
	
	//add, delete할때 모두 사용
	public Wishlist(String userId, int bookId) {
		super();
		this.userId = userId;
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
}
