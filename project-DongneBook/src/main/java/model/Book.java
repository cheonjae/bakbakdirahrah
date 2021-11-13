//å ������ ���� �ʿ��� ������ Ŭ����.

public class Book {
	private String title;
	private String authors;
	private String publisher;
	private Date publicationDate;
	private String productidentifier;
	private int price;
	private String description;
	private String image;
	
	public Book() {} 	// �⺻ ������
	
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
	public String getAuthors() {
		return authors;
	}
	public int Publisher() {
		return publisher;
	}
	public String Productidentifier() {
		return productidentifier;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public int getPrice() {
		return price;
	}
	public int getDescription() {
		return description;
	}
	public int getImage() {
		return image;
	}


}
