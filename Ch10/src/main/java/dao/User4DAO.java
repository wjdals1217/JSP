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

import dto.User4DTO;

public class User4DAO {
	private final String HOST = "jdbc:mysql://13.124.41.119:3306/UserDB";
	private final String USER = "userdb";
	private final String PASS = "UuuS1235789@";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser4(User4DTO dto) {
		try {
			logger.info("User4DAO insertUser4...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User4` SET `name`=?, `gender`=?, `age`=?, `addr`=?");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			psmt.close();
			conn.close();			
			logger.info("User4DAO insertUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO insertUser4 error : "+e.getMessage());
		}
	}
	public User4DTO selectUser4(String seq) {
		User4DTO dto = null;
		try {
			logger.info("User4DAO selectUser4...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User4` WHERE `seq`=?");
			psmt.setString(1, seq);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new User4DTO();
				dto.setSeq(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));;
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));
			}
			rs.close();
			psmt.close();
			conn.close();
			logger.info("User4DAO selectUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO selectUser4 error : "+e.getMessage());
		}
		return dto;
	}
	public List<User4DTO> selectUser4s() {
		List<User4DTO> users = new ArrayList<>();
		try {
			logger.info("User4DAO selectUser4s...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User4`");
			while(rs.next()) {
				User4DTO dto = new User4DTO();
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));
				users.add(dto);
			}
			rs.close();
			stmt.close();
			conn.close();
			logger.info("User4DAO selectUser4s...2");
		} catch (Exception e) {
			logger.error("User4DAO selectUser4s error : "+e.getMessage());
		}
		return users;
	}
	public void updateUser4(User4DTO dto) {
		try {
			logger.info("User4DAO updateUser4...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `User4` SET `name`=?, `gender`=?, `age`=?, `addr`=? WHERE `seq`=?");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.setInt(5, dto.getSeq());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("User4DAO updateUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO updateUser4 error : "+e.getMessage());
		}
	}
	public void deleteUser4(String seq) {
		try {
			logger.info("User4DAO deleteUser4...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `User4` WHERE `seq`=?");
			psmt.setString(1, seq);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("User4DAO deleteUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO deleteUser4 error : "+e.getMessage());
		}
	}
}
