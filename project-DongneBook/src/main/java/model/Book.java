
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
	private Date publicationDate;
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

	// 책 제목, 가격, 이미지만 보여주기 위해서 만든..생성자..
	public Book(int bookId, String title, int price, String image) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.image = image;
	}

	public Book(int bookId, String userId, String title, String author, String publisher, Date publicationDate,
			int price, String description, String image, int sold, int cateId) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.price = price;
		this.description = description;
		this.image = image;
		this.sold = sold;
		this.cateId = cateId;
	}

	public Book(int bookId, String userId, String title, String author, String publisher, Date publicationDate,
				int price, String description, String image, int sold, int cateId,
				int pageDiscoloration, int coverDamage, int pageDamage, int writing) {
		this(bookId, userId, title, author, publisher, publicationDate, price, description, image, sold, cateId);
		pageDiscoloration = this.pageDiscoloration;
		coverDamage = this.coverDamage;
		pageDamage = this.pageDamage;
		writing = this.writing;
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

	public String getauthor() {
		return author;
	}

	public void setauthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
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
				+ author + ", publisher=" + publisher + ", publicationDate=" + publicationDate + ", price=" + price
				+ ", description=" + description + ", image=" + image + ", sold=" + sold + "]";
	}

}
