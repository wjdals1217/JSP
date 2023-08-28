package kr.co.Jboard2.service;

import java.util.List;

import kr.co.Jboard2.dao.UserDAO;
import kr.co.Jboard2.dto.UserDTO;

public class UserService {
	private static UserService instance = new UserService();
	public static UserService getInstance() {
		return instance;
	}
	private UserService() {}

	private UserDAO dao = UserDAO.getInstance();
	
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	public UserDTO selectUser(String uid) {
		return dao.selectUser(uid);
	}

	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	
	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}
	
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
}