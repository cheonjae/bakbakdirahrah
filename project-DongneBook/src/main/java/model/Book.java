
package model;

import java.util.Date;

// 책 관리를 위해 필요한 도메인 클래스. Book 테이블과 대응됨
public class Book {
	private int bookId;
	private String userId;
	private int cateId;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String description;
	private String image;
	private int sold;

//	condition info
	private int pageDiscoloration; // 페이지 변색 - 0(없음) or 1(있음)
	private int coverDamage; // 겉표지 - 0(깨끗함) or 1(깨끗하지 )
	private int pageDamage; // 페이지 훼손 - 0(없음) or 1(있음)
	private int writing; // 필기 흔적 - 0(없음) or 1(연필/샤프) or 2(볼펜/형광펜)

	public Book() {
	} // 기본 생성

	// 업뎃용 생성자
	public Book(int bookId, int price, String description, int sold) {
		this.bookId = bookId;
		this.price = price;
		this.description = description;
		this.sold = sold;
	}
	
	// 책 제목, 가격, 이미지만 보여주기 위해서 만든..생성자..
	public Book(int bookId, String title, int price, String image) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.image = image;
	}

	public Book(int bookId, String userId, String title, String author, String publisher,
			int price, String description, String image, int sold, int cateId) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.description = description;
		this.image = image;
		this.sold = sold;
		this.cateId = cateId;
	}

	public Book(int bookId, String userId, String title, String author, String publisher,
				int price, String description, String image, int sold, int cateId,
				int pageDiscoloration, int coverDamage, int pageDamage, int writing) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.description = description;
		this.image = image;
		this.sold = sold;
		this.cateId = cateId;
		this.pageDiscoloration = pageDiscoloration;
		this.coverDamage = coverDamage;
		this.pageDamage = pageDamage;
		this.writing = writing;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
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

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", userId=" + userId + ", cateId=" + cateId + ", title=" + title + ", author="
				+ author + ", publisher=" + publisher + ", price=" + price
				+ ", description=" + description + ", image=" + image + ", sold=" + sold + "]";
	}

}
