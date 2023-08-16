package kr.farmstory1.dao;

import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;
import kr.farmstory1.dto.TermsDTO;
import kr.farmstory1.dto.UserDTO;

public class UserDAO extends DBHelper{
	
	// 싱글톤 객체 생성
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	private UserDAO() {}
	
	// selectTerms
	public TermsDTO selectTerms() {
		TermsDTO dto = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			if(rs.next()) {
				dto = new TermsDTO();
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
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
			e.printStackTrace();
		}
	}
	
	public UserDTO selectUser(String uid, String pass) {
		UserDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto =  new UserDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNick(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setHp(rs.getString(6));
				dto.setRole(rs.getString(7));
				dto.setZip(rs.getString(8));
				dto.setAddr1(rs.getString(9));
				dto.setAddr2(rs.getString(10));
				dto.setRegip(rs.getString(11));
				dto.setRegDate(rs.getString(12));
				dto.setLeaveDate(rs.getString(13));
				
			}
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
