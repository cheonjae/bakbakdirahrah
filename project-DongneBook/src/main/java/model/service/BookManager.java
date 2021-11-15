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

	public List<Book> mainBookList(int currentPage, int countPerPage)
			throws SQLException {
				return bookDAO.mainBookList(currentPage, countPerPage);
		}
	
	public List findBookDetails(int book_id) throws SQLException {
	return BookDAO.findBookDetails(book_id);
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
}
