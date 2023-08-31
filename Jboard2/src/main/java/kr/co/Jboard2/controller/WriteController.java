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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		logger.debug("oName : "+oName);
		logger.debug("regip : "+regip);
		
		// DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
			//파일첨부개수
			dto.setFile(oName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// Article Insert
		int no = articleService.insertArticle(dto);
		
		// 파일명 수정 및 파일테이블 Insert
		// 파일 첨부를 했을 때
		if(oName != null) {
			String sName = articleService.renameToFile(req, oName);
			// 파일 테이블 Insert
			FileDTO fileDTO = new FileDTO();
			// ano가 필요하기 때문에 Article 테이블 isert가 먼저 일어나야 함
			fileDTO.setAno(no);
			fileDTO.setOriName(oName);
			fileDTO.setNewName(sName);
			
			fileService.insertFile(fileDTO);
		}
		
		// 리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	}
}
