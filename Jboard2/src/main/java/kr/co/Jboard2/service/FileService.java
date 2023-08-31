package kr.co.Jboard2.service;

import java.util.List;

import kr.co.Jboard2.dao.FileDAO;
import kr.co.Jboard2.dto.FileDTO;

public enum FileService {
	INSTANCE;
	
	private FileDAO dao = new FileDAO();
	
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	public FileDTO selectFile(int fno) {
		return dao.selectFile(fno);
	}
	public List<FileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	public void deleteFile(int fno) {
		dao.deleteFile(fno);
	}
}
