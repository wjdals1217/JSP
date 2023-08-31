package kr.co.Jboard2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.Jboard2.dao.ArticleDAO;
import kr.co.Jboard2.dto.ArticleDTO;

public class ArticleService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleDAO dao = new ArticleDAO();
	
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
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		//context : 환경 ctx가 application 객체라 생각하면 된다
		ServletContext ctx =  req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i); // 확장자명(. 뒤 부터 마지막 까지)
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		// 파일명 수정
		f1.renameTo(f2);
		
		return sName;
	}
	
	// 파일 업로드 모듈화
	public MultipartRequest uploadFile(HttpServletRequest req) {
		// 파일 경로 구하기
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기(10MB) 1MB * 1MB * 10
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		// 파일 업로드 MultipartRequest생성자는 업로드 로직을 수행
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile() error : "+e.getMessage());
		}
		
		return mr;
	}
	
	// 파일 다운로드
	public void downloadFile() {
		
	}
}
