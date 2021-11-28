package model.service;

import java.sql.SQLException;
import java.util.List;

import model.*;
import model.dao.WishlistDAO;

public class WishlistManager {
	private static WishlistManager wishMan = new WishlistManager();
	private WishlistDAO wishDAO;

	private WishlistManager() {
		try {
			wishDAO = new WishlistDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static WishlistManager getInstance() {
		return wishMan;
	}
	
	public int add(Wishlist wishlist) throws SQLException {
		return wishDAO.add(wishlist);
		
	}
	
	public int delete(String userId, int bookId) throws SQLException {
		return wishDAO.delete(userId, bookId);
	}
	
	public List<Book> wishBookList(String userId) throws SQLException, BookNotFoundException {
		List<Book> book = wishDAO.wishBookList(userId);
		if (book == null) {
			throw new BookNotFoundException("찜한 책이 없습니다.");
		}
		return book;
	}
}
