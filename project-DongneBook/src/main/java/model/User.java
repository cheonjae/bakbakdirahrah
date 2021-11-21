package model;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. USERINFO 테이블과 대응됨
 */
public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String location;

	public User() { }		// 기본 생성자
	
	public User(String userId, String password, String name, String location, String phone, String email) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.email = email;
	}

	public User(String userId, String name, String location, String phone, String email) {
		this.userId = userId;
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.email = email;
	}
	
	public void update(User updateUser) {
		this.password = updateUser.password;
		this.name = updateUser.name;
		this.location = updateUser.location;
		this.phone = updateUser.phone;
		this.email = updateUser.email;
    }
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", location=" + location + "]";
	}	
}
