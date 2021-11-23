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
	
}
