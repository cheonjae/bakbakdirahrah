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
		Object [] param = new Object[] { book.getTitle(), book.getAuthors(),
						book.getPublisher(), book.getProductidentifier(), book.getPrice(),
						book.getDescription(), book.getImage(), book.getcateId(), book.getuserId(), book.getBookId() }; 
		
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
		// BOOK���� .. bookId�� ���� �̹���(�ּ�), ����, ���� �����´�.
		// ��������~ Ȯ��ġ �ʾƿ�~
        String sql = "SELECT image, title, price "  
        		   + "FROM BOOK "
        		+ "WHERE bookId=? "; 
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<User> BookMainList = new ArrayList<User>();	// User���� ����Ʈ ����
			while (rs.next()) {
				Book user = new Book(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
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
	
	
	//å ������ ���⿡�� ����� Find. (userid�� ������ å ���� ����)
	public Book findBookDetails(String bookId) throws SQLException {
        String sql = "SELECT userid, bookid, cateid, title, authors, publisher,"
        		+ " publicationdate, price, description, image "
        			+ "FROM BOOK "
        			+ "WHERE bookid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// å ���� �߰�
				Book book = new Book(		// User ��ü�� �����Ͽ� �л� ������ ����
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	

	
}
