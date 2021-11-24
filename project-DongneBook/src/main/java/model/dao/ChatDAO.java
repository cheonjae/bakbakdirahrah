package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Chat;

public class ChatDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ChatDAO() {			
		jdbcUtil = new JDBCUtil();	
	}
	
	public Chat create(Chat chat) throws SQLException {
		String sql = "INSERT INTO chat VALUES (SYSTIMESTAMP, ?, CHATSEQ.nextval, ?, ?)";
		Object [] param = new Object[] { chat.getContents(), chat.getSenderId(), chat.getReceiverId()}; 
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
		
		String key[] = {"chat_id"}; 
		try {
			jdbcUtil.executeUpdate(key);     // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();    // 생성된 PK 값을 포함한 result set 객체 반환
		   	
			//int generatedKey = 0;
			if(rs.next()) {
				//generatedKey = rs.getInt("book_id"); - 틀린 구문
				int generatedKey = rs.getInt(1);
		   		chat.setChatId(generatedKey);
		   	}
			return chat;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return null;
	}
	
	//채팅내역 삭제(방 나가기)
	public int deleteRoom(String userId, String buddyId) throws SQLException {
		String sql = "DELETE FROM chat WHERE (sender_id=? AND receiver_id=?) OR (sender_id=? AND receiver_id=?)";	
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, buddyId, buddyId, userId});	// JDBCUtil에 delete문과 매개 변수 설정

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
	
	public List<Chat> findChatContents(String userId, String buddyId) throws SQLException {
        String sql = "SELECT sender_id, receiver_id, contents "  
        		   + "FROM chat "
        		   + "WHERE (sender_id=? AND receiver_id=?) OR (sender_id=? AND receiver_id=?) "
        		   + "ORDER BY created_at"; 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, buddyId, buddyId, userId});	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<Chat> chatContentsList = new ArrayList<Chat>();	
			while (rs.next()) {
				Chat chat = new Chat(			
					rs.getString("contents"),
					rs.getString("sender_id"),
					rs.getString("receiver_id"));
					chatContentsList.add(chat);	
			}	
			return chatContentsList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<String> findBuddyId(String userId) {
//		중복값 제외하는 방법 있을까?
		String sql = "SELECT sender_id, receiver_id "
				   + "FROM chat "
				   + "WHERE sender_id=? OR receiver_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, userId});	
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<String> buddyIdList = new ArrayList<String>();	
			while (rs.next()) {
				buddyIdList.add(rs.getString("sender_id"));
				buddyIdList.add(rs.getString("receiver_id"));
				// 현재 로그인한 유저 정보를 controller에서 확인할 수 있기 때문에 sender가 상대방인지 receiver가 상대방인지 확인하는 if문은 controller 안에서 돌려야될듯
				// 일단 sender_id와 receiver_id 모두 반환하고 컨트롤러에서 상대방id 저장
			}	
			return buddyIdList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
}
