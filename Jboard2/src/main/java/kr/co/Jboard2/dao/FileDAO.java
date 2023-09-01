package kr.co.Jboard2.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.db.DBHelper;
import kr.co.Jboard2.db.SQL;
import kr.co.Jboard2.dto.FileDTO;

public class FileDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertFile(FileDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOriName());
			psmt.setString(3, dto.getNewName());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertFile() error : "+e.getMessage());
		}
	}
	public FileDTO selectFile(String fno) {
		FileDTO dto =null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOriName(rs.getString(3));
				dto.setNewName(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
			}
			close();
		} catch (Exception e) {
			logger.error("selectFile() error : "+e.getMessage());
		}
		return dto;
	}
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {
		
	}
	public List<FileDTO> deleteFile(String ano) {
		List<FileDTO> files = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			PreparedStatement psmt2 = conn.prepareStatement(SQL.SELECT_FILES_BY_ANO);
			psmt = conn.prepareStatement(SQL.DELETE_FILE);
			psmt.setString(1, ano);
			psmt2.setString(1, ano);
			rs = psmt2.executeQuery();
			psmt.executeUpdate();
			conn.commit();
			while(rs.next()) {
				FileDTO dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOriName(rs.getString(3));
				dto.setNewName(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
				files.add(dto);
			}
			psmt2.close();
			close();
		} catch (Exception e) {
			logger.error("deleteFile() error : "+e.getMessage());
		}
		return files;
	}
}
