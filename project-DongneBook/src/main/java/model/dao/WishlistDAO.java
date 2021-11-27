package model.dao;

import java.sql.SQLException;

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
	
	
}
