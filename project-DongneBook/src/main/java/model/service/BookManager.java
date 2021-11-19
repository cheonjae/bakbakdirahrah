package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.dao.BookDAO;

public class BookManager {
	private static BookManager bookMan = new BookManager();
	private BookDAO bookDAO;
	
	private BookManager() {
		try {
			ookDAO = new BookDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static BookManager getInstance() {
		return bookMan;
	}
	
	public int create(Book book) throws SQLException {
		return bookDAO.create(book);
	}

	// 북 정보변경
	public int update(Book book) throws SQLException, BookNotFoundException {
		if (book == null) {
			throw new BookNotFoundException("등록한 책이 없습니다.");
		}
		return bookDAO.update(book);
	}
	
	public List<Book> mainBookList()
			throws SQLException {
				return bookDAO.mainBookList();
		}
	
	public Book findBookDetails(int book_id) throws SQLException {
		return bookDAO.findBookDetails(book_id);
	}
	
	public List<Book> searchBookList(String title) throws SQLException, BookNotFoundException {
		List<Book> book = bookDAO.searchBookList(title);
		if (book == null) {
			throw new BookNotFoundException(title + "의 검색 결과가 없습니다.");
		}
		return book;
	}
	
	public List<Book> findMyBookList(String userId) throws SQLException, BookNotFoundException {
		List<Book> book = bookDAO.searchBookList(userId);
		if (book == null) {
			throw new BookNotFoundException("등록한 책이 없습니다.");
		}
		return book;
	}
	
	public List<Book> cateBookList(int cateId) throws SQLException {
		return bookDAO.cateBookList(cateId);
	}
	
	public List<Category> findCategoryList() throws SQLException {
		return bookDAO.findCategoryList();
	}
	
	public int deleteBook(int bookId) throws SQLException, BookNotFoundException {
		return bookDAO.deleteBook(bookId);
	}
}
