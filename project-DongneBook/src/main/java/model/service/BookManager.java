package model.service;

import java.sql.SQLException;
import java.util.List;
import model.Book;
import model.dao.BookDAO;

public class BookManager {
	private BookDAO bookDAO;
	
	public List<Book> searchBookList(int currentPage, int countPerPage, String title) throws SQLException, BookNotFoundException {
		List<Book> book = bookDAO.searchBookList(currentPage, countPerPage, title);
		if (book == null) {
			throw new BookNotFoundException(title + "�� �˻� ����� �����ϴ�.");
		}
		return book;
	}
	
	public List<Book> findMyBookList(int currentPage, int countPerPage, String userId) throws SQLException, BookNotFoundException {
		List<Book> book = bookDAO.searchBookList(currentPage, countPerPage, userId);
		if (book == null) {
			throw new BookNotFoundException("����� å�� �����ϴ�.");
		}
		return book;
	}
}
