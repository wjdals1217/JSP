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

import dto.User6DTO;
import dto.User6DTO;

public class User6DAO {
	private static User6DAO instance = new User6DAO();
	public static User6DAO getInstance() {
		return instance;
	}
	private User6DAO() {}
	
	private final String HOST = "jdbc:mysql://13.124.41.119:3306/UserDB";
	private final String USER = "userdb";
	private final String PASS = "UuuS1235789@";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser6(User6DTO dto) {
		try {
			logger.info("insertUser6()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User6` VALUES (?, ?, ?, ?, ?)");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getBirth());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getHp());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("insertUser6()...2");
		} catch (Exception e) {
			logger.error("insertUser6() error : "+e.getMessage());
		}
	}
	public User6DTO selectUser6(String hp) {
		User6DTO dto = null;
		try {
			logger.info("selectUser6()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User6`WHERE `hp`=?");
			psmt.setString(1, hp);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new User6DTO();
				dto.setName(rs.getString(1));
				dto.setBirth(rs.getString(2));
				dto.setAge(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setHp(rs.getString(5));
			}
			logger.info("selectUser6()...2");
	} catch (Exception e) {
		logger.error("selectUser6() error : "+e.getMessage());
	}
		return dto;
	}
	public List<User6DTO> selectUser6s() {
		List<User6DTO> users = new ArrayList<>();
		try {
			logger.info("selectUser6()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User6`");
			while(rs.next()) {
				User6DTO dto = new User6DTO();
				dto.setName(rs.getString(1));
				dto.setBirth(rs.getString(2));
				dto.setAge(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setHp(rs.getString(5));
				users.add(dto);
			}
			logger.info("selectUser6()...2");
	} catch (Exception e) {
		logger.error("selectUser6() error : "+e.getMessage());
	}
		return users;
	}
	public void updateUser6(User6DTO dto) {
		try {
			logger.info("updateUser6()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `User6` SET `name`=?, `birth`=?,`age`=?, `address`=? WHERE `hp`=?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getBirth());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getHp());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("updateUser6()...2");
		} catch (Exception e) {
			logger.error("updateUser6() error : "+e.getMessage());
		}
	}
	public void deleteUser6(String hp) {
		try {
			logger.info("deleteUser6()...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `User6` WHERE `hp`=?");
			psmt.setString(1, hp);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("deleteUser6()...2");
		} catch (Exception e) {
			logger.error("deleteUser6() error : "+e.getMessage());
		}
	}
	
}
