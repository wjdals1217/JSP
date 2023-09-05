package kr.co.Jboard2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.Jboard2.dao.ArticleDAO;
import kr.co.Jboard2.dto.ArticleDTO;
import kr.co.Jboard2.dto.FileDTO;

public class ArticleService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleDAO dao = new ArticleDAO();
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public int insertComment(ArticleDTO dto) {
		return dao.insertComment(dto);
	}
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	public int selectCountTotal(String search) {
		return dao.selectCountTotal(search);
	}
	
	public List<ArticleDTO> selectArticles(int start, String search) {
		return dao.selectArticles(start, search);
	}
	public List<ArticleDTO> selectComments(String no) {
		return dao.selectComments(no);
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void updateArticleForCommentPlus(String no) {
		dao.updateArticleForCommentPlus(no);
	}
	public void updateArticleForCommentMinus(String no) {
		dao.updateArticleForCommentMinus(no);
	}
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	public int deleteComment(String no) {
		return dao.deleteComment(no);
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
		String ext = oName.substring(i); // 확장자명(.부터 마지막 까지)
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
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO fileDTO) throws IOException {
		// response 파일 다운로드 헤더 수정
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDTO.getOriName(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// response 파일 스트림 작업
		String path = getFilePath(req);
		
		File file = new File(path+"/"+fileDTO.getNewName());
		
		BufferedInputStream bis = new  BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		while(true) {
			int data = bis.read();
			if(data == -1) {
				break;
			}
			bos.write(data);
		}
		bos.close();
		bis.close();
	}
	
	// 현재 페이지 계산 
	public int currentPage(String pg) {
		int currentPage = 1;
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	// Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) *10;
	}
	
	// 마지막 페이지 번호 계산
	public int getLastPageNum(int total){
		int lastPageNum = 0;
		if(total % 10 == 0) {
			lastPageNum = (total / 10);
		}else {
			lastPageNum = (total / 10) + 1;
		}
		return lastPageNum;
	}
	
	// 페이지 그룹 계산
	public int[] getPageGroupNum (int currentPage, int lastPageNum){
		int pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		int[] result = {pageGroupStart, pageGroupEnd};
		return result;
	}
	
	// 페이지 시작번호 계산
	public int getPageStartNum (int total, int currentPage) {
		int start = (currentPage -1) * 10;
		return total - start;
	}
	
}
