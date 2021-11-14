package model.service;

import java.sql.SQLException;
import java.util.List;

import model.User;
import model.dao.UserDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	
	private UserManager() {
		try {
			userDAO = new UserDAO();
			new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}

	// 사용자 정보변경 - 제약조건 확인 
	public int update(User user) throws SQLException, UserNotFoundException, UserInfoException {
		String oldPassword = findUser(user.getUserId()).getPassword();
		if (user.getPassword() != oldPassword) {
			if (user.getPassword().length() < 6 || user.getPassword().length() > 13) { // 비밀번호 제약조건 확인 
				throw new UserInfoException("비밀번호가 너무 길거나 짧습니다.");
			}
		}
		String oldEmail = findUser(user.getUserId()).getEmail();
		if (user.getEmail() != oldEmail) {
			if (user.getEmail().length() < 5) { // 이메일 제약조건 확인 
				throw new UserInfoException("이메일 주소가 너무 짧습니다.");
			}
		}
		String oldPhone = findUser(user.getUserId()).getPhone();
		if (user.getPhone() != oldPhone) {
			if (user.getPhone().length() != 11) { // 전화번호 제약조건 확인 
				throw new UserInfoException("적절하지 않은 전화번호입니다.");
			}
		}
//		location 제약조건 확인?
		return userDAO.update(user);
	}	

	// 회원정보 삭제 - 삭제 시 해당 회원의 책과 채팅 DB까지 삭제 고려 
	public int remove(String userId) throws SQLException, UserNotFoundException {
		return userDAO.remove(userId);
	}

	public User findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}		
		return user;
	}

	public List<User> findUserList() throws SQLException {
			return userDAO.findUserList();
	}
	
	public List<User> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
