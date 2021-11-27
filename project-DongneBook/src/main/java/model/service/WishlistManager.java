package model.service;

import java.sql.SQLException;
import model.*;
import model.dao.BookDAO;
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
}
