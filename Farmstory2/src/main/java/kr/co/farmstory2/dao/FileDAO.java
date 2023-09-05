package kr.co.farmstory2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.FileDTO;

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
	public FileDTO selectFile(String no) {
		FileDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, no);
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
	public void updateFile(FileDTO dto) {}

	public void updateDownload(FileDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_DOWNLOAD);
			psmt.setInt(1, dto.getFno());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("updateDownload() error : "+e.getMessage());
		}
	}
	
	public void deleteFile(String no) {}

}
