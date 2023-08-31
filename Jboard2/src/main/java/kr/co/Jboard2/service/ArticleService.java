package kr.co.Jboard2.service;

import java.util.List;

import kr.co.Jboard2.dao.ArticleDAO;
import kr.co.Jboard2.dto.ArticleDTO;

public class ArticleService {

	ArticleDAO dao = new ArticleDAO();
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	public List<ArticleDTO> selectArticles() {
		return dao.selectArticles();
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	
	// 업로드 경로 구하기 모듈화
	public void getUploadPath() {
		
	}
	// 파일 업로드 모듈화
	public void uploadFile() {
		
	}
	
	// 파일 다운로드
	public void downloadFile() {
		
	}
}
