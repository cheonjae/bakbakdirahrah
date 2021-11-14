package model.dao;
import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * 책 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * BookInfo 테이블에 책 정보를 추가, 수정, 삭제(컨디션포함), 검색 수행 
*/

public class BookDAO {
	private JDBCUtil jdbcUtil = null;
	
	public BookDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	// 새로운 책 생성	
	public int create(Book book) throws SQLException {
		String sql = "INSERT INTO BOOK VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object [] param = new Object[] { book.getTitle(), book.getAuthor(),
						book.getPublisher(), book.getPublicationDate(), book.getBookId(), book.getPrice(),
						book.getDescription(), book.getImage(), book.getUserId(), book.getCateId(), book.getSold() }; 
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
		
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
	
	
	//메인에서 이용할 책 찾기 (책 이미지, 제목, 가격) -> findUerList 참고함.
	public List<Book> findBookMain() throws SQLException {
		// BOOK에서 .. book_id를 통해 이미지(주소), 제목, 가격 가져온다.
		// 쿼리문이~ 확실치 않아요~
        String sql = "SELECT image, title, price "  
        		   + "FROM BOOK "
        		+ "WHERE book_id=? "; 
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<User> BookMainList = new ArrayList<User>();	// User들의 리스트 생성
			while (rs.next()) {
				Book user = new Book(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("book_id"),
					rs.getString("title"),
					rs.getInt("price"),
					rs.getString("image"));
				BookMainList.add(book);				// List에 Book 객체 저장
			}		
			return BookMainList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
	//책 상세정보 보기에서 사용할 Find. (user_id를 포함한 책 정보 전부)
	public Book findBookDetails(String book_id) throws SQLException {
        String sql = "SELECT user_id, book_id, category_id, title, author, publisher,"
        		+ " publicationdate, price, description, image "
        			+ "FROM BOOK "
        			+ "WHERE book_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {book_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 책 정보 발견
				Book book = new Book(		// User 객체를 생성하여 학생 정보를 저장
					book_id,
					rs.getInt("user_id"),
					rs.getString("category_id"),
					rs.getString("title"),
					rs.getString("author"),
					rs.getString("publisher"),					
					rs.getDate("publication_date"),
					rs.getInt("price"),
					rs.getString("description"),
					rs.getString("image")
					);
				return book;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 북 ID에 해당하는 사용자를 삭제. 
	 */
	public int deleteBook(String book_id) throws SQLException {
		//보류
		String sql = "DELETE FROM condition WHERE book_id=?; "
				+ "DELETE FROM book WHERE book_id=?";	
		jdbcUtil.setSqlAndParameters(sql, new Object[] {book_id});	// JDBCUtil에 delete문과 매개 변수 설정

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
