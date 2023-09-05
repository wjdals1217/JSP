package kr.co.farmstory2.service;

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

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;

public class ArticleService {

	private ArticleDAO dao = new ArticleDAO();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	// 업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		f1.renameTo(f2);
		
		return sName;
	}
	
	// 파일업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		// 파일 경로 구하기
		String path = getFilePath(req);
		// 최대 업로드 파일 크기(10MB)
		int maxSize = 1024 * 1024 * 10;
		
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.debug("uploadFille() error : "+e.getMessage());
		}
		
		return mr;
		
	}
}
