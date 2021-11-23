package model;

public class Chat {

	private int chatId;
	private String createdAt;
	private String contents;
	private String senderId;
	private String receiverId;
	
	public Chat(String contents, String senderId, String receiverId) {
		super();
		this.contents = contents;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}
	
	public Chat(int chatId, String createdAt, String contents, String senderId, String receiverId) {
		super();
		this.chatId = chatId;
		this.createdAt = createdAt;
		this.contents = contents;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	
}
