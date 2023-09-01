package kr.co.Jboard2.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.Jboard2.dto.ArticleDTO;
import kr.co.Jboard2.dto.FileDTO;
import kr.co.Jboard2.service.ArticleService;
import kr.co.Jboard2.service.FileService;

@WebServlet("/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = -9051224184952512645L;
	
	private ArticleService articleService = new ArticleService();
	private FileService fileService = FileService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 글쓰기 페이지 forward
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	// 글쓰기 페이지에서 폼 데이터 수신 후 DB 저장
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일 업로드
		MultipartRequest mr = articleService.uploadFile(req);
		
		// 폼 데이터 수신
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String oName = mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();
		
		logger.debug("title : "+title);
		logger.debug("content : "+content);
		logger.debug("writer : "+writer);
		logger.debug("oName : "+oName); // 파일 원본 이름
		logger.debug("regip : "+regip);
		
		// DTO 생성
		ArticleDTO dto = new ArticleDTO();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setFile(oName); //파일첨부개수
			dto.setWriter(writer);
			dto.setRegip(regip);
		
		// Article Insert
			//no : 최신 글 번호
		int no = articleService.insertArticle(dto); 
		logger.debug("no"+no);
		
		// 파일명 수정 및 파일테이블 Insert
		if(oName != null) { 																   // 파일 첨부를 했을 때
			
			String sName = articleService.renameToFile(req, oName); // 파일 이름 수정
			
			// 파일 테이블 Insert
			FileDTO fileDTO = new FileDTO();
			
			// ano가 필요하기 때문에 Article 테이블 isert가 먼저 일어나야 함
			fileDTO.setAno(no);
			fileDTO.setOriName(oName);
			fileDTO.setNewName(sName);
			
			fileService.insertFile(fileDTO); // DB에 파일 정보 저장
		}
		
		// list 페이지로 리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	}
}
