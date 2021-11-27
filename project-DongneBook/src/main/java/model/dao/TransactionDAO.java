package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transaction;

public class TransactionDAO {
	private JDBCUtil jdbcUtil = null;
	
	public TransactionDAO() {			
		jdbcUtil = new JDBCUtil();	
	}
	
	public int create(Transaction transaction) throws SQLException {
		String sql = "INSERT INTO transaction VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object [] param = new Object[] {transaction.getLastPrice(), transaction.getMeetingDate(), transaction.getMeetingPlace(), 
				transaction.getMeetingMemo(),transaction.getBookId(), transaction.getSellerId(), transaction.getBuyerId(),
				transaction.getSellerCheck(), transaction.getBuyerCheck()}; 
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;	
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
	
	public int checkUpdate(int bookId, String userId, String buddyId) throws SQLException {
		String sql = "SELECT seller_id, buyer_id "
				+ "FROM transaction "
				+ "WHERE (book_id=? AND seller_id=? AND buyer_id=?) OR (book_id=? AND buyer_id=? AND seller_id=?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId, userId, buddyId, bookId, userId, buddyId});	
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			String seller = "";
			String buyer = "";	
			while (rs.next()) {
				seller = rs.getString("seller_id");
				buyer = rs.getString("buyer_id");
			}
			
			jdbcUtil.close();
			
			int result = 0;
			if (userId.equals(seller)) {
				sql = "UPDATE transaction SET seller_check=1 "
						+ "WHERE book_id=? AND seller_id=? AND buyer_id=?";
				jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId, userId, buddyId});
				
				result = jdbcUtil.executeUpdate();
			} else if (userId.equals(buyer)) {
				sql = "UPDATE transaction SET buyer_check=1 "
						+ "WHERE book_id=? AND seller_id=? AND buyer_id=?";
				jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId, buddyId, userId});
				
				result = jdbcUtil.executeUpdate();
			}
			
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
	
	public Transaction view(int bookId, String userId, String buddyId) throws SQLException {
        	String sql = "SELECT seller_id, buyer_id, last_price, meeting_date, "
        			+ "meeting_place, meeting_memo, seller_check, buyer_check "
        			+ "FROM transaction "
        			+ "WHERE book_id=? AND ((seller_id=? AND buyer_id=?) OR (seller_id=? AND buyer_id=?))";  
        
		jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId, userId, buddyId, buddyId, userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 책 정보 발견
				Transaction transaction = new Transaction(		// User 객체를 생성하여 학생 정보를 저장
					bookId,
					rs.getString("seller_id"),
					rs.getString("buyer_id"),
					rs.getInt("last_price"),
					rs.getString("meeting_date"),
					rs.getString("meeting_place"),
					rs.getString("meeting_memo"),
					rs.getInt("seller_check"),
					rs.getInt("buyer_check")
					);
				return transaction;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
}
