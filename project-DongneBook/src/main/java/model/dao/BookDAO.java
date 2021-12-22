package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Category;

/**
 * 梨� 愿�由щ�� �쐞�빐 �뜲�씠�꽣踰좎씠�뒪 �옉�뾽�쓣 �쟾�떞�븯�뒗 DAO �겢�옒�뒪
 * BookInfo �뀒�씠釉붿뿉 梨� �젙蹂대�� 異붽�, �닔�젙, �궘�젣(而⑤뵒�뀡�룷�븿), 寃��깋 �닔�뻾 
*/

public class BookDAO {
	private JDBCUtil jdbcUtil = null;
	
	public BookDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 媛앹껜 �깮�꽦
	}
		
	// �깉濡쒖슫 梨� �깮�꽦	
	public Book create(Book book) throws SQLException {
		String sql = "INSERT INTO book VALUES (?, ?, ?, BOOKSEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object [] param = new Object[] { book.getTitle(), book.getAuthor(), book.getPublisher(),
					book.getPrice(), book.getDescription(), book.getImage(), book.getUserId(), book.getSold(), 
					book.getPageDiscoloration(), book.getCoverDamage(), book.getPageDamage(), book.getWriting(), book.getCateId()}; 
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		
		String key[] = {"book_id"}; 
		try {
			jdbcUtil.executeUpdate(key);     // insert 臾� �떎�뻾
			ResultSet rs = jdbcUtil.getGeneratedKeys();    // �깮�꽦�맂 PK 媛믪쓣 �룷�븿�븳 result set 媛앹껜 諛섑솚
		   	
			//int generatedKey = 0;
			if(rs.next()) {
				//generatedKey = rs.getInt("book_id"); - ��由� 援щЦ
				int generatedKey = rs.getInt(1);
		   		book.setBookId(generatedKey);
		   	}
			return book;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return null;	
	}
	
	//梨� �젙蹂� �닔�젙 (user_id�� book_id�뒗 �닔�젙 遺덇��뒫)
	public int update(Book book) throws SQLException {
		String sql = "UPDATE book "
				+ "SET price=?, description=?, sold=? "
				+ "WHERE book_id=?";
		Object[] param = new Object[] {book.getPrice(), book.getDescription(), book.getSold(), book.getBookId()};				
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
	
	//硫붿씤�뿉�꽌 �씠�슜�븷 梨� 李얘린. �럹�씠吏��떦 異쒕젰�븷 梨� �닔 異붽�
	//(梨� �씠誘몄�, �젣紐�, 媛�寃�) -> findUerList 李멸퀬�븿.
	public List<Book> mainBookList(String location) throws SQLException {
		// BOOK�뿉�꽌 .. book_id瑜� �넻�빐 �씠誘몄�(二쇱냼), �젣紐�, 媛�寃� 媛��졇�삩�떎.

		if(location.equals("")) {
			location = "구";
		}
		
		String keyword = "%" + location + "%";
        	String sql = "SELECT book_id, title, price, image "
				+ "FROM book b, users u "
				+ "WHERE b.user_id = u.user_id AND b.sold = 0 AND u.location LIKE ? ";
        
		jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword}, 
				ResultSet.TYPE_SCROLL_INSENSITIVE,	// cursor scroll 媛��뒫
				ResultSet.CONCUR_READ_ONLY);		// JDBCUtil�뿉 query臾� �꽕�젙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾		
			List<Book> mainBookList = new ArrayList<Book>();	// User�뱾�쓽 由ъ뒪�듃 �깮�꽦
			while (rs.next()) {
				Book book = new Book(			// User 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
					rs.getInt("book_id"),
					rs.getString("title"),
					rs.getInt("price"),
					rs.getString("image"));
				mainBookList.add(book);				// List�뿉 Book 媛앹껜 ���옣
			}	
			return mainBookList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	//梨� �긽�꽭�젙蹂� 蹂닿린�뿉�꽌 �궗�슜�븷 Find. (user_id瑜� �룷�븿�븳 梨� �젙蹂� �쟾遺�)
	public Book findBookDetails(int bookId) throws SQLException  {
        	String sql = "SELECT user_id, title, author, publisher, "
        		+ "price, description, image, sold, category_id, "
			+ "page_discoloration, cover_damage, page_damage, writing "
        		+ "FROM BOOK "
        		+ "WHERE book_id=?";    
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {						// 梨� �젙蹂� 諛쒓껄
				Book book = new Book(		// User 媛앹껜瑜� �깮�꽦�븯�뿬 �븰�깮 �젙蹂대�� ���옣
					bookId,
					rs.getString("user_id"),
					rs.getString("title"),
					rs.getString("author"),
					rs.getString("publisher"),
					rs.getInt("price"),
					rs.getString("description"),
					rs.getString("image"),
					rs.getInt("sold"),
					rs.getInt("category_id"),
					rs.getInt("page_discoloration"),
					rs.getInt("cover_damage"),
					rs.getInt("page_damage"),
					rs.getInt("writing")
					);
				return book;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	
	/**
	 * 遺� ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄瑜� �궘�젣. 
	 */
	public int deleteBook(int bookId, String userId) throws SQLException {
		String sql = "DELETE FROM wishlist WHERE book_id=?";	
		jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 臾� �떎�뻾
			
			jdbcUtil.commit();
			jdbcUtil.close();
			
			sql = "DELETE FROM book WHERE book_id=? AND user_id=?";	
			jdbcUtil.setSqlAndParameters(sql, new Object[] {bookId, userId});
			
			result = jdbcUtil.executeUpdate();
			
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	
	//留덉씠�럹�씠吏��뿉 �궡媛� �벑濡앺븳 梨낅뱾�쓣 �럹�씠吏�蹂꾨줈(4 * 4) �굹�뿴
	public List<Book> findMyBookList(String userId) throws SQLException {
		String sql = "SELECT book_id, title, price, image "
        			+ "FROM book "
        			+ "WHERE user_id=?";   

		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId},		// JDBCUtil�뿉 query臾� �꽕�젙
				ResultSet.TYPE_SCROLL_INSENSITIVE,		// cursor scroll 媛��뒫
				ResultSet.CONCUR_READ_ONLY);					
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query �떎�뻾			
			List<Book> myBookList = new ArrayList<Book>();	// �궡 �벑濡� 梨낅뱾�쓽 由ъ뒪�듃 �깮�꽦
			while(rs.next()) {
				Book book = new Book(			// Book 媛앹껜瑜� �깮�꽦�븯�뿬 梨� �젙蹂대�� ���옣
				rs.getInt("book_id"),
				rs.getString("title"),
				rs.getInt("price"),
				rs.getString("image"));
				myBookList.add(book);			// 由ъ뒪�듃�뿉 Book 媛앹껜 ���옣
			} 	
			return myBookList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	//�젣紐⑹쑝濡� 寃��깋�븳 梨낅뱾�쓣 �굹�뿴
	public List<Book> searchBookList(String title, String location) throws SQLException {
		String keyword1 = "%" + location + "%";
		String keyword2 = "%" + title + "%";
        	String sql = "SELECT book_id, title, price, image "
				+ "FROM book b, users u "
				+ "WHERE b.user_id = u.user_id AND b.sold = 0 AND u.location LIKE ? AND title LIKE ? ";
	  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword1, keyword2},		// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
				ResultSet.TYPE_SCROLL_INSENSITIVE,		// cursor scroll 媛��뒫
				ResultSet.CONCUR_READ_ONLY);					
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query �떎�뻾	
				List<Book> searchBookList = new ArrayList<Book>();	// �궡 �벑濡� 梨낅뱾�쓽 由ъ뒪�듃 �깮�꽦
			while(rs.next()) {
				Book book = new Book(			// Book 媛앹껜瑜� �깮�꽦�븯�뿬 梨� �젙蹂대�� ���옣
					rs.getInt("book_id"),
					rs.getString("title"),
					rs.getInt("price"),
					rs.getString("image"));
				searchBookList.add(book);			// 由ъ뒪�듃�뿉 Book 媛앹껜 ���옣
			}		
			return searchBookList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	//移댄뀒怨좊━ �븘�씠�뵒蹂꾨줈 梨낅뱾�쓣 �굹�뿴(遺꾨쪟)
	public List<Book> cateBookList(int cateId, String location) throws SQLException {
		String keyword = "%" + location + "%";
        	String sql = "SELECT book_id, title, price, image "
				+ "FROM book b, users u "
				+ "WHERE b.user_id = u.user_id AND b.sold = 0 AND u.location LIKE ? AND category_id=?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword, cateId},	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
				ResultSet.TYPE_SCROLL_INSENSITIVE,		// cursor scroll 媛��뒫
				ResultSet.CONCUR_READ_ONLY);					
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query �떎�뻾			
			List<Book> cateBookList = new ArrayList<Book>();	// �궡 �벑濡� 梨낅뱾�쓽 由ъ뒪�듃 �깮�꽦
			while(rs.next()) {
				Book book = new Book(			// Book 媛앹껜瑜� �깮�꽦�븯�뿬 梨� �젙蹂대�� ���옣
					rs.getInt("book_id"),
					rs.getString("title"),
					rs.getInt("price"),
					rs.getString("image"));
				cateBookList.add(book);			// 由ъ뒪�듃�뿉 Book 媛앹껜 ���옣
			} 	
			return cateBookList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	public List<Category> findCategoryList() throws SQLException {
		String sql = "SELECT category_id, cd_name "
     		   + "FROM category "
     		   + "ORDER BY cd_name";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�뿉 query臾� �꽕�젙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾			
			List<Category> categoryList = new ArrayList<Category>();	// Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
			while (rs.next()) {
				Category cate = new Category(			// Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
						rs.getInt("cateId"),
						rs.getString("cateName"));
				categoryList.add(cate);				// List�뿉 Community 媛앹껜 ���옣
			}		
			return categoryList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	public List<Book> sellList(String userId) throws SQLException {
        String sql = "SELECT book_id, title, price, image, sold "
        		+ "FROM book "
        		+ "WHERE user_id=?"; 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			List<Book> sellList = new ArrayList<Book>();	
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				book.setImage(rs.getString("image"));
				sellList.add(book);
			}					
			return sellList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
}
