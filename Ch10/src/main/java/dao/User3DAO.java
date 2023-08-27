package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User3DTO;

public class User3DAO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String HOST = "jdbc:mysql://13.124.41.119:3306/UserDB";
	private final String USER = "userdb";
	private final String PASS = "UuuS1235789@";
	
	public void insertUser3(User3DTO dto ) {
		try {
			logger.info("User3DAO insertUser3...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User3` VALUES (?, ?, ?, ?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("User3DAO insertUser3...2");
		} catch (Exception e) {
			logger.error("User3DAO insertUser3 error : "+e.getMessage());
		}
	}
	public User3DTO selectUser3(String uid) {
		return null;
	}
	public List<User3DTO> selectUser3s() {
		return null;
	}
	public void updateUser3(User3DTO dto) {
		
	}
	public void deleteUser3(String uid) {
		
	}
}
