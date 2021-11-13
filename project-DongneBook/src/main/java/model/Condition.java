package model;

public class Condition {
	private int bookId;
	private int pageDiscoloration; // º¯»ö
	private int coverDamage;
	private int pageDamage;
	private int writing;
	
	public Condition() {}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getPageDiscoloration() {
		return pageDiscoloration;
	}
	public void setPageDiscoloration(int pageDiscoloration) {
		this.pageDiscoloration = pageDiscoloration;
	}
	public int getCoverDamage() {
		return coverDamage;
	}
	public void setCoverDamage(int coverDamage) {
		this.coverDamage = coverDamage;
	}
	public int getPageDamage() {
		return pageDamage;
	}
	public void setPageDamage(int pageDamage) {
		this.pageDamage = pageDamage;
	}
	public int getWriting() {
		return writing;
	}
	public void setWriting(int writing) {
		this.writing = writing;
	}
}
