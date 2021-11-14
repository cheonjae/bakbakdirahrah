package model.dao;
import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * å ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * BookInfo ���̺� å ������ �߰�, ����, ����(���������), �˻� ���� 
*/

public class BookDAO {
	private JDBCUtil jdbcUtil = null;
	
	public BookDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	// ���ο� å ����	
	public int create(Book book) throws SQLException {
		String sql = "INSERT INTO BOOK VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object [] param = new Object[] { book.getTitle(), book.getauthor(),
						book.getPublisher(), book.getPublicationDate(), book.getPrice(),
						book.getDescription(), book.getImage(), book.getgetCateId(), book.getUserId(), book.getBookId() }; 
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;	
	}
	
	
	//���ο��� �̿��� å ã�� (å �̹���, ����, ����) -> findUerList ������.
	public List<Book> findBookMain() throws SQLException {
		// BOOK���� .. book_id�� ���� �̹���(�ּ�), ����, ���� �����´�.
		// ��������~ Ȯ��ġ �ʾƿ�~
        String sql = "SELECT image, title, price "  
        		   + "FROM BOOK "
        		+ "WHERE book_id=? "; 
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<User> BookMainList = new ArrayList<User>();	// User���� ����Ʈ ����
			while (rs.next()) {
				Book user = new Book(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("book_id"),
					rs.getString("title"),
					rs.getInt("price"),
					rs.getString("image"));
				BookMainList.add(book);				// List�� Book ��ü ����
			}		
			return BookMainList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
	//å ������ ���⿡�� ����� Find. (user_id�� ������ å ���� ����)
	public Book findBookDetails(String book_id) throws SQLException {
        String sql = "SELECT user_id, book_id, category_id, title, author, publisher,"
        		+ " publicationdate, price, description, image "
        			+ "FROM BOOK "
        			+ "WHERE book_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {book_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// å ���� �߰�
				Book book = new Book(		// User ��ü�� �����Ͽ� �л� ������ ����
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �� ID�� �ش��ϴ� ����ڸ� ����. 
	 */
	public int deleteBook(String book_id) throws SQLException {
		//����
		String sql = "DELETE book, condition "
				+ "FROM book JOIN condition USING(book_id) "
				+ "WHERE book_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {book_id});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	

	
}
