package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.User6DAO;
import dao.User6DAO;
import dto.User6DTO;

public class User6Service {

	private static User6Service instance = new User6Service();
	public static User6Service getInstance() {
		return instance;
	}
	private User6Service() {}
	private User6DAO dao = User6DAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser6(User6DTO dto) {
		dao.insertUser6(dto);
	}
	public User6DTO selectUser6(String hp) {
		return dao.selectUser6(hp);
	}
	public List<User6DTO> selectUser6s() {
		return dao.selectUser6s();
	}
	public void updateUser6(User6DTO dto) {
		dao.updateUser6(dto);
	}
	public void deleteUser6(String hp) {
		dao.deleteUser6(hp);
	}
}
