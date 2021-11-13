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
		String sql = "INSERT INTO BOOK VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object [] param = new Object[] { book.getTitle(), book.getAuthors(),
						book.getPublisher(), book.getProductidentifier(), book.getPrice(),
						book.getDescription(), book.getImage(), book.getcateId(), book.getuserId(), book.getBookId() }; 
		
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
		// BOOK에서 .. bookId를 통해 이미지(주소), 제목, 가격 가져온다.
		// 쿼리문이~ 확실치 않아요~
        String sql = "SELECT image, title, price "  
        		   + "FROM BOOK "
        		+ "WHERE bookId=? "; 
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<User> BookMainList = new ArrayList<User>();	// User들의 리스트 생성
			while (rs.next()) {
				Book user = new Book(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("bookId"),
					null,
					null,
					rs.getString("title"),
					null,
					null,
					null,
					null,
					rs.getString("price"),
					null,
					rs.getShort("image"));
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
	
	
	//책 상세정보 보기에서 사용할 Find. (userid를 포함한 책 정보 전부)
	public Book findBookDetails(String bookId) throws SQLException {
        String sql = "SELECT userid, bookid, cateid, title, authors, publisher,"
        		+ " publicationdate, price, description, image "
        			+ "FROM BOOK "
        			+ "WHERE bookid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 책 정보 발견
				Book book = new Book(		// User 객체를 생성하여 학생 정보를 저장
					bookId,
					rs.getInt("userid"),
					rs.getString("cateid"),
					rs.getString("title"),
					rs.getString("authors"),
					rs.getString("publisher"),					
					rs.getDate("publicationdate"),
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
	

	
}
