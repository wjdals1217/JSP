package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User5DTO;

public class User5DAO {
	private static User5DAO instance = new User5DAO();
	public static User5DAO getInstance() {
		return instance;
	}
	private User5DAO() {}
	
	private final String HOST = "jdbc:mysql://13.124.41.119:3306/UserDB";
	private final String USER = "userdb";
	private final String PASS = "UuuS1235789@";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser5(User5DTO dto) {
		try {
			logger.info("insertUser5()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User5` VALUES (?, ?, ?, ?, ?, ?, ?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getBirth());
			psmt.setInt(4, dto.getGender());
			psmt.setInt(5, dto.getAge());
			psmt.setString(6, dto.getAddress());
			psmt.setString(7, dto.getHp());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("insertUser5()...2");
		} catch (Exception e) {
			logger.error("insertUser5() error : "+e.getMessage());
		}
	}
	public User5DTO selectUser5(String uid) {
		User5DTO dto = null;
		try {
			logger.info("selectUser5()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User5`WHERE `uid`=?");
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new User5DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirth(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setAge(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setHp(rs.getString(7));
			}
			logger.info("selectUser5()...2");
	} catch (Exception e) {
		logger.error("selectUser5() error : "+e.getMessage());
	}
		return dto;
	}
	public List<User5DTO> selectUser5s() {
		List<User5DTO> users = new ArrayList<>();
		try {
			logger.info("selectUser5()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User5`");
			while(rs.next()) {
				User5DTO dto = new User5DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirth(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setAge(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setHp(rs.getString(7));
				users.add(dto);
			}
			logger.info("selectUser5()...2");
	} catch (Exception e) {
		logger.error("selectUser5() error : "+e.getMessage());
	}
		return users;
	}
	public void updateUser5(User5DTO dto) {
		try {
			logger.info("updateUser5()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `User5` SET `name`=?, `birth`=?, `gender`=?, `age`=?, `address`=?, `hp`=? WHERE `uid`=?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getBirth());
			psmt.setInt(3, dto.getGender());
			psmt.setInt(4, dto.getAge());
			psmt.setString(5, dto.getAddress());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getUid());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("updateUser5()...2");
		} catch (Exception e) {
			logger.error("updateUser5() error : "+e.getMessage());
		}
	}
	public void deleteUser5(String uid) {
		try {
			logger.info("deleteUser5()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `User5` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("deleteUser5()...2");
		} catch (Exception e) {
			logger.error("deleteUser5() error : "+e.getMessage());
		}
	}
	
}
