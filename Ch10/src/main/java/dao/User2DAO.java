package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User2DTO;

public class User2DAO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String HOST = "jdbc:mysql://13.124.41.119:3306/UserDB";
	private final String USER = "userdb";
	private final String PASS = "UuuS1235789@";

	public void insertUser2(User2DTO dto){
		try {
			logger.info("User2DAO insertUser2...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User2` SET `uid`=?, `name`=?, `hp`=?, `age`=?");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("User2DAO insertUser2...2");
		} catch (Exception e) {
			logger.error("User2DAO insertUser2 error : "+e.getMessage());
		}
	}
	public User2DTO selectUser2(String uid){
		return null;
	}
	public List<User2DTO> selectUser2s(){
		return null;
	}
	public void updateUser2(User2DTO dto){}
	public void deleteUser2(String uid){}
}
