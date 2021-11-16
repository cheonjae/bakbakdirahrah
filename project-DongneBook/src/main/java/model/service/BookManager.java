package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.Condition;
import model.dao.BookDAO;
import model.dao.ConditionDAO;

public class BookManager {
	private static BookManager bookMan = new BookManager();
	private BookDAO bookDAO;
	private ConditionDAO conditionDAO;
	
	public static BookManager getInstance() {
		return bookMan;
	}
	
	public int create(Book book) throws SQLException {
		return bookDAO.create(book);
	}
	
	public int insertCondition(Condition condition) throws SQLException {
		return conditionDAO.insert(condition);		
	}	

	// 북 정보변경
	public int update(Book book) throws SQLException, BookNotFoundException {
		if (book == null) {
			throw new BookNotFoundException("등록한 책이 없습니다.");
		}
		return bookDAO.update(book);
	}
	
	public List<Book> mainBookList(int currentPage, int countPerPage, int bookId)
			throws SQLException {
				return bookDAO.mainBookList(currentPage, countPerPage, bookId);
		}
	
	public Book findBookDetails(int book_id) throws SQLException {
		return bookDAO.findBookDetails(book_id);
	}
	
	public List<Book> searchBookList(int currentPage, int countPerPage, String title) throws SQLException, BookNotFoundException {
		List<Book> book = bookDAO.searchBookList(currentPage, countPerPage, title);
		if (book == null) {
			throw new BookNotFoundException(title + "의 검색 결과가 없습니다.");
		}
		return book;
	}
	
	public List<Book> findMyBookList(int currentPage, int countPerPage, String userId) throws SQLException, BookNotFoundException {
		List<Book> book = bookDAO.searchBookList(currentPage, countPerPage, userId);
		if (book == null) {
			throw new BookNotFoundException("등록한 책이 없습니다.");
		}
		return book;
	}
	
	public List<Book> cateBookList(int currentPage, int countPerPage, int cateId) throws SQLException {
		return bookDAO.cateBookList(currentPage, countPerPage, cateId);
	}
	
	public int deleteBook(int bookId) throws SQLException, BookNotFoundException {
		return bookDAO.deleteBook(bookId);
	}
}
