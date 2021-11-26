package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transaction;

public class TransactionDAO {
	private JDBCUtil jdbcUtil = null;
	
	public TransactionDAO() {			
		jdbcUtil = new JDBCUtil();	
	}
	
	public Transaction create(Transaction transaction) throws SQLException {
		String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object [] param = new Object[] { transaction.getBookId(), transaction.getSellerId(), transaction.getBuyerId(),
				transaction.getLastPrice(), transaction.getMeetingDate(), transaction.getMeetingPlace(), 
				transaction.getMeetingMemo(), transaction.getSellerCheck(),transaction.getBuyerCheck()}; 
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"book_id", "seller_id", "buyer_id"}; 
		try {
			jdbcUtil.executeUpdate(key);
			return transaction;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return null;	
	}
	
	public int deleteTransaction(int bookId, String sellerId, String buyerId) throws SQLException{
		//sellerId(param) and buyerId(param)가 둘다 들어잇는 트잭을 D E L E T E 틀렸으면 말해줘
		String sql = "DELETE FROM transaction WHERE sellerId=? AND buyerId=?";	
		jdbcUtil.setSqlAndParameters(sql, new Object[] {sellerId, buyerId});
		
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	public int update(Transaction transaction) throws SQLException {
		String sql = "UPDATE transaction "
				+ "SET last_price=?, meeting_date=?, meeting_place=?, meeting_memo=? "
				+ "WHERE book_id=? AND seller_id=? AND buyer_id=?";
		Object[] param = new Object[] {transaction.getLastPrice(), transaction.getMeetingDate(), 
				transaction.getMeetingPlace(), transaction.getMeetingMemo(),
				transaction.getBookId(), transaction.getSellerId(), transaction.getBuyerId()};				
		jdbcUtil.setSqlAndParameters(sql, param);
			
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}
}
