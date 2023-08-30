package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.User5DAO;
import dto.User5DTO;

public class User5Service {

	private static User5Service instance = new User5Service();
	public static User5Service getInstance() {
		return instance;
	}
	private User5Service() {}
	private User5DAO dao = User5DAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser5(User5DTO dto) {
		dao.insertUser5(dto);
	}
	public User5DTO selectUser5(String uid) {
		return dao.selectUser5(uid);
	}
	public List<User5DTO> selectUser5s() {
		return dao.selectUser5s();
	}
	public void updateUser5(User5DTO dto) {
		dao.updateUser5(dto);
	}
	public void deleteUser5(String uid) {
		dao.deleteUser5(uid);
	}
}
