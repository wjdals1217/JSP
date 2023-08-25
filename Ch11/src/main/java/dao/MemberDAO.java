package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.MemberDTO;

public class MemberDAO {
	
	public static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	// Logger 생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertMember(MemberDTO dto) {
		try {
			logger.info("MemberDAO insertMember...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/UserDB");
			Connection conn = ds.getConnection();
			PreparedStatement psmt =  conn.prepareStatement("INSERT INTO `member` VALUES (?, ?, ?, ?, ?, NOW())");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setString(4, dto.getPos());
			psmt.setInt(5, dto.getDep());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			logger.info("MemberDAO insertMember...2");
			
		} catch (Exception e) {
			logger.error("MemberDAO insertMember error : "+ e.getMessage());
		}
		
	}
	public MemberDTO selectMember(String uid) {
		MemberDTO dto = null;
		try {
			logger.info("MemberDAO selectMember()...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/UserDB");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `member` WHERE `uid`=?");
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setPos(rs.getString(4));
				dto.setDep(rs.getString(5));
				dto.setRdate(rs.getString(6));
			}
			rs.close();
			psmt.close();
			conn.close();
			logger.info("MemberDAO selectMember()...2");
		} catch (Exception e) {
			logger.error("MemberDAO selectMember error : "+e.getMessage());
		}
		
		return dto;
	}
	public List<MemberDTO> selectMembers() {
		List<MemberDTO> members = new ArrayList<>();
		try {
			logger.info("MemberDAO selectMembers...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/UserDB");
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `member`");
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setPos(rs.getString(4));
				dto.setDep(rs.getString(5));
				dto.setRdate(rs.getString(6));
				members.add(dto);
			}
			rs.close();
			stmt.close();
			conn.close();
			logger.info("MemberDAO selectMembers...2");
		} catch (Exception e) {
			logger.error("MemberDAO selectMembers error : "+e.getMessage());
		}
		return members;
	}
	public void updateMember(MemberDTO dto) {
		try {
			logger.info("MemberDAO updateMember...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/UserDB");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("UPDATE `member` SET `name`=?, `hp`=?, `pos`=?, `dep`=? WHERE `uid`=? ");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setString(3, dto.getPos());
			psmt.setInt(4, dto.getDep());
			psmt.setString(5, dto.getUid());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("MemberDAO updateMember...2");
		} catch (Exception e) {
			logger.error("MemberDAO updateMember() error : "+e.getMessage());
		}
		
	}
	public void deleteMember(String uid) {
		try {
			logger.info("MemberDAO deleteMember()...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/UserDB");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `member` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			logger.info("MemberDAO deleteMember()...2");
		} catch (Exception e) {
			logger.error("MemberDAO deleteMember() error : "+e.getMessage());
		}
		
	}
}
