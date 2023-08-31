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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.Jboard2.dto.ArticleDTO;
import kr.co.Jboard2.dto.FileDTO;
import kr.co.Jboard2.service.ArticleService;
import kr.co.Jboard2.service.FileService;

@WebServlet("/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = -9051224184952512645L;
	private ArticleService Articleservice = new ArticleService();
	private FileService fileService = FileService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일 업로드 경로 구하기
		//context : 환경 ctx가 application 객체라 생각하면 된다
		ServletContext ctx =  req.getServletContext();
		String path = ctx.getRealPath("/upload");

		// 최대 업로드 파일 크기(10MB) 1MB * 1MB * 10
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		// 파일 업로드 MultipartRequest생성자는 업로드 로직을 수행
		MultipartRequest mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());

		// 폼 데이터 수신
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String oName = mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();
		
		// DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// Article Insert
		int no = Articleservice.insertArticle(dto);
		
		// 파일명 수정 및 파일테이블 Insert
		// 파일 첨부를 했을 때
		if(oName != null) {
			int i = oName.lastIndexOf(".");
			String ext = oName.substring(i); // 확장자명(. 뒤 부터 마지막 까지)
			String uuid = UUID.randomUUID().toString();
			String sName = uuid + ext;
			
			File f1 = new File(path+"/"+oName);
			File f2 = new File(path+"/"+sName);
			
			// 파일명 수정
			f1.renameTo(f2);
			
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
