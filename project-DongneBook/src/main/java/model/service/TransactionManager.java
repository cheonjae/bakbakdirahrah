package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Transaction;
import model.dao.BookDAO;
import model.dao.TransactionDAO;


public class TransactionManager {
	private static TransactionManager tmanager = new TransactionManager();
	private TransactionDAO transactionDAO;
	
	private TransactionManager() {
		try {
			transactionDAO = new TransactionDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static TransactionManager getInstance() {
		return tmanager;
	}
	
	public int deleteTransaction(int bookId, String sellerId, String buyerId) throws SQLException, BookNotFoundException {
	
		return transactionDAO.deleteTransaction(bookId,sellerId,buyerId);
	}
}
