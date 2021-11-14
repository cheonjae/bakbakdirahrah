package model.service;

import java.sql.SQLException;
import java.util.List;

import model.User;
import model.dao.UserDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
			throw new ExistingUserException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userDAO.create(user);
	}

	// ����� �������� - �������� Ȯ�� 
	public int update(User user) throws SQLException, UserNotFoundException, UserInfoException {
		String oldPassword = findUser(user.getUserId()).getPassword();
		if (user.getPassword() != oldPassword) {
			if (user.getPassword().length() < 6 || user.getPassword().length() > 13) { // ��й�ȣ �������� Ȯ�� 
				throw new UserInfoException("��й�ȣ�� �ʹ� ��ų� ª���ϴ�.");
			}
		}
		String oldEmail = findUser(user.getUserId()).getEmail();
		if (user.getEmail() != oldEmail) {
			if (user.getEmail().length() < 5) { // �̸��� �������� Ȯ�� 
				throw new UserInfoException("�̸��� �ּҰ� �ʹ� ª���ϴ�.");
			}
		}
		String oldPhone = findUser(user.getUserId()).getPhone();
		if (user.getPhone() != oldPhone) {
			if (user.getPhone().length() != 11) { // ��ȭ��ȣ �������� Ȯ�� 
				throw new UserInfoException("�������� ���� ��ȭ��ȣ�Դϴ�.");
			}
		}
//		location �������� Ȯ��?
		return userDAO.update(user);
	}	

	// ȸ������ ���� - ���� �� �ش� ȸ���� å�� ä�� DB���� ���� ��� 
	public int remove(String userId) throws SQLException, UserNotFoundException {
		return userDAO.remove(userId);
	}

	public User findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
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
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
