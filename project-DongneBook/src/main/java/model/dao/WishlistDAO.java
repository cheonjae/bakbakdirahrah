package model.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Wishlist;

public class WishlistDAO {
	private JDBCUtil jdbcUtil = null;
	
	public WishlistDAO() {			
		jdbcUtil = new JDBCUtil();	
	}
	
	//추가.
	public int add(Wishlist wishlist) throws SQLException {
		String sql = "INSERT INTO wishlist VALUES (?, ?)";
		Object [] param = new Object[]  { wishlist.getUserId(), wishlist.getBookId() }; 
	
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}			
		return 0;
	}
	
	//삭제
	public int delete(String userId, int bookId) throws SQLException {
		String sql = "DELETE FROM wishlist WHERE userId=? AND bookId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId,bookId}); 
		
		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	// 찜한 상품 리스트
	public List<Book> wishBookList(String userId) throws SQLException {
		String sql = "SELECT book_id, title, price, image "
				   + "FROM book "
				   + "WHERE book_id IN (SELECT book_id FROM wishlist WHERE user_id=?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId}, 
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Book> wishBookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(
					rs.getInt("book_id"),
					rs.getString("title"),
					rs.getInt("price"),
					rs.getString("image"));
					wishBookList.add(book);	
			}	
			return wishBookList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
}
