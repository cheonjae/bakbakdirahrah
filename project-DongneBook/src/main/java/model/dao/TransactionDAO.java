package model.dao;

import java.sql.SQLException;

import model.Transaction;

public class TransactionDAO {
	private JDBCUtil jdbcUtil = null;
	
	public TransactionDAO() {			
		jdbcUtil = new JDBCUtil();	
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
