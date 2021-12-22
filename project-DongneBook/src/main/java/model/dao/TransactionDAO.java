package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
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
    			+ "WHERE book_id=? AND ((seller_id=? AND buyer_id=?) OR (seller_id=? AND buyer_id=?))";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId, userId, buddyId, buddyId, userId});	
		
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
			} else if (userId.equals(buyer)) {
				sql = "UPDATE transaction SET buyer_check=1 "
						+ "WHERE book_id=? AND seller_id=? AND buyer_id=?";
				
				jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId, buddyId, userId});
			}
			result = jdbcUtil.executeUpdate();
			
			return result;		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
			
			soldUpdate(bookId);
		}	
		return 0;
	}
	
	public int soldUpdate(int bookId) throws SQLException {
		String sql = "UPDATE /*+ BYPASS_UJVC */ "
				+ "(SELECT seller_check, buyer_check, sold FROM transaction t, book b WHERE t.book_id = b.book_id AND t.book_id=?) "
				+ "SET sold= "
				+ "CASE WHEN seller_check=1 AND buyer_check=1 THEN 1 "
				+ "ELSE 0 "
				+ "END";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId});	
		
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
	
	public List<Transaction> buyList(String userId) throws SQLException {
        String sql = "SELECT b.title, "
        		+ "t.book_id, seller_id, last_price, meeting_date, meeting_place, meeting_memo "  
     		   + "FROM book b LEFT OUTER JOIN transaction t "
     		   + "ON b.book_id = t.book_id "
     		   + "WHERE buyer_id=? and buyer_check=1 AND seller_check=1";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			List<Transaction> transactionList = new ArrayList<Transaction>();	
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("title"));
				Transaction transaction = new Transaction();
				transaction.setBookId(rs.getInt("book_id"));
				transaction.setSellerId(rs.getString("seller_id"));
				transaction.setLastPrice(rs.getInt("last_price"));
				transaction.setMeetingDate(rs.getString("meeting_date"));
				transaction.setMeetingPlace(rs.getString("meeting_place"));
				transaction.setMeetingMemo(rs.getString("meeting_memo"));
				transaction.setBook(book);
				transactionList.add(transaction);
			}	
			return transactionList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public  List<Transaction> sellDetail(String userId, int bookId) throws SQLException {
        String sql = "SELECT b.title, b.author, b.price, b.publisher, b.image, b.category_id, b.description, "
        		+ "b.page_discoloration, b.writing, b.page_damage, b.cover_damage, "
        		+ "seller_id, buyer_id, last_price, meeting_date, meeting_place, meeting_memo, seller_check, buyer_check "  
        		   + "FROM book b LEFT OUTER JOIN transaction t "
        		   + "ON b.book_id = t.book_id "
        		   + "WHERE b.user_id=? and b.book_id=?"; 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, bookId});	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			List<Transaction> transactionList = new ArrayList<Transaction>();	
			while (rs.next()) {					
				Book book = new Book();
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setPublisher(rs.getString("publisher"));
				book.setImage(rs.getString("image"));
				book.setCateId(rs.getInt("category_id"));
				book.setDescription(rs.getString("description"));
				book.setPageDiscoloration(rs.getInt("page_discoloration"));
				book.setWriting(rs.getInt("writing"));
				book.setPageDamage(rs.getInt("page_damage"));
				book.setCoverDamage(rs.getInt("cover_damage"));
				
				Transaction transaction = new Transaction();
				transaction.setSellerId(rs.getString("seller_id"));
				transaction.setBuyerId(rs.getString("buyer_id"));
				transaction.setLastPrice(rs.getInt("last_price"));
				transaction.setMeetingDate(rs.getString("meeting_date"));
				transaction.setMeetingPlace(rs.getString("meeting_place"));
				transaction.setMeetingMemo(rs.getString("meeting_memo"));
				transaction.setSellerCheck(rs.getInt("seller_check"));
				transaction.setBuyerCheck(rs.getInt("buyer_check"));
				
				transaction.book = book;
				transactionList.add(transaction);
			}	
			return transactionList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
