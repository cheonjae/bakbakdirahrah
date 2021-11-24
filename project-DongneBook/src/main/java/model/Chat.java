package model;

public class Chat {

	private int chatId;
	private String createdAt;
	private String contents;
	private String senderId;
	private String receiverId;
	
	public Chat() {
	}

	public Chat(String contents, String senderId, String receiverId) {
		super();
		this.contents = contents;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}
	
	public Chat(String createdAt, String contents, int chatId, String senderId, String receiverId) {
		super();
		this.createdAt = createdAt;
		this.contents = contents;
		this.chatId = chatId;
		this.senderId = senderId;
		this.receiverId = receiverId;
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

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
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
