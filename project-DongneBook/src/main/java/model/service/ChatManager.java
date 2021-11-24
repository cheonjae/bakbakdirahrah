package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Chat;
import model.dao.ChatDAO;

public class ChatManager {
	private static ChatManager chatMan = new ChatManager();
	private ChatDAO chatDAO;
	
	private ChatManager() {
		try {
			chatDAO = new ChatDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ChatManager getInstance() {
		return chatMan;
	}
	
	public Chat create(Chat chat) throws SQLException {
		return chatDAO.create(chat);
	}
	
	public int deleteRoom(String userId, String buddyId) throws SQLException {
		return chatDAO.deleteRoom(userId, buddyId);
	}
	
	public List<Chat> findChatContents(String userId, String buddyId) throws SQLException {
		return chatDAO.findChatContents(userId, buddyId);
	}
	
	public List<String> findBuddyId(String userId) throws SQLException {
		return chatDAO.findBuddyId(userId);
	}
}
