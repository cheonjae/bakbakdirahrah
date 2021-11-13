//책 관리를 위해 필요한 도메인 클래스.
package model;

import java.util.Date;

public class Book {
	private String title;
	private String authors;
	private String publisher;
	private Date publicationDate;
	private String productidentifier;
	private int price;
	private String description;
	private String image;
	
	public Book() {} 	// 기본 생성자
	
	public Book(String title, String authors, String publisher, Date publicationDate,
			String productidentifier, int price, String description,
			String image) {
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.productidentifier = productidentifier;
		this.price = price;
		this.description = description;
		this.image = image;
	
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
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

	public String getProductidentifier() {
		return productidentifier;
	}

	public void setProductidentifier(String productidentifier) {
		this.productidentifier = productidentifier;
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

	@Override
	public String toString() {
		return "Book [title=" + title + ", authors=" + authors + ", publisher=" + publisher + ", publicationDate="
				+ publicationDate + ", productidentifier=" + productidentifier + ", price=" + price + ", description="
				+ description + ", image=" + image + "]";
	}	
}
