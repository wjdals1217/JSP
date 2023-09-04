package kr.co.farmstory2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.famrstory2.db.DBHelper;
import kr.co.famrstory2.db.SQL;
import kr.co.famrstory2.dto.UserDTO;

public class UserDAO extends DBHelper{
	
	private static UserDAO instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	private UserDAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser(UserDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertLogger() error : "+e.getMessage());
		}
	}
	public UserDTO selectUser(String uid, String pass) {
		UserDTO user = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();	
			if(rs.next()) {
				user = new UserDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));
			}
			close();
		} catch (Exception e) {
			logger.error("selectUsers() error : "+e.getMessage());
		}
		return user;
	}
	
	public int selectCountUid(String uid) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountUid() error : "+e.getMessage());
		}
		return result;
	}
	public int selectCountNick(String nick) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountNick() error : "+e.getMessage());
		}
		return result;
	}
	public int selectCountEmail(String email) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountEmail() error : "+e.getMessage());
		}
		return result;
	}
	public int selectCountHp(String hp) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountHp() error : "+e.getMessage());
		}
		return result;
	}
	public List<UserDTO> selectUsers() {
		return null;
	}
	public void updateUser(UserDTO dto) {
		
	}
	public void deleteUser(String uid) {
	}
}
