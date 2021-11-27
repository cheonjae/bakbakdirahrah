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
	
	public int create(Transaction transaction) throws SQLException {
		return transactionDAO.create(transaction);
	}
	
	public int update(Transaction transaction) throws SQLException, BookNotFoundException {
		//null처리 어떻게 할 것인가~
		return transactionDAO.update(transaction);
	}
	
	public Transaction view(int bookId, String userId, String buddyId) throws SQLException {
		return transactionDAO.view(bookId, userId, buddyId);
	}
	
	public int checkUpdate(int bookId, String userId, String buddyId) throws SQLException {
		return transactionDAO.checkUpdate(bookId, userId, buddyId);
	}
}
