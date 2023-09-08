package kr.co.farmstory2.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = -4100251041333721754L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = new ArticleService();
	private FileService fileService = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		logger.debug("group"+group);
		logger.debug("cate"+cate);
		logger.debug("no"+no);
		
		ArticleDTO article = articleService.selectArticleToModify(no);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("article", article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = articleService.getPath(req, "/upload");
		
		MultipartRequest mr = articleService.uploadFile(req, path);
		
		String group = mr.getParameter("group");
		String cate = mr.getParameter("cate");
		String no = mr.getParameter("no");
		String prevFile = mr.getParameter("prevFile");
		String prevFiledelete = mr.getParameter("prevFiledelete");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String oName = mr.getOriginalFileName("fileModi");

		logger.debug("group : "+group);
		logger.debug("cate : "+cate);
		logger.debug("no : "+no);
		logger.debug("prevFile : "+prevFile);
		logger.debug("prevFiledelete : "+prevFiledelete);
		logger.debug("title : "+title);
		logger.debug("content : "+content);
		logger.debug("oName : "+oName);
		
		
		if(oName != null) {
			String sName = articleService.renameToFile(req, path, oName);// 파일 이름 수정
			if(!prevFile.isEmpty()) {
				if(!prevFile.equals(sName)) {
					FileDTO fileDTO = new FileDTO();
					fileDTO.setAno(no);
					fileDTO.setOriName(oName);
					fileDTO.setNewName(sName);

					// fileDB 업데이트
					fileService.updateFile(fileDTO);
					// 원래 파일 directory에서 삭제
					File file = new File(path+"/"+prevFile);
					if(file.exists()) {
						file.delete();
					}
					
					ArticleDTO articleDTO = new ArticleDTO();
					articleDTO.setTitle(title);
					articleDTO.setContent(content);
					articleDTO.setFile(oName);
					articleDTO.setNo(no);
					
					articleService.updateArticle(articleDTO);
					logger.info("파일 수정");
				}
			}else {
				// 파일 테이블 Insert
				FileDTO fileDTO = new FileDTO();
				
				// ano가 필요하기 때문에 Article 테이블 isert가 먼저 일어나야 함
				fileDTO.setAno(no);
				fileDTO.setOriName(oName);
				fileDTO.setNewName(sName);
				
				fileService.insertFile(fileDTO); // DB에 파일 정보 저장
				
				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setTitle(title);
				articleDTO.setContent(content);
				articleDTO.setFile(oName);
				articleDTO.setNo(no);
				
				articleService.updateArticle(articleDTO);
				logger.info("파일 추가");
			}
			
		}else {
			if(prevFiledelete.equals("DELETE")) {
				fileService.deleteFile(no);
				File filedelete = new File(path+ "/"+prevFile);
				if(filedelete.exists()) {
					filedelete.delete();
				}
				
				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setTitle(title);
				articleDTO.setContent(content);
				articleDTO.setFile(oName);
				articleDTO.setNo(no);
				
				articleService.updateArticle(articleDTO);
				logger.info("파일 삭제");
			}else {
				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setTitle(title);
				articleDTO.setContent(content);
				articleDTO.setFile(prevFile);
				articleDTO.setNo(no);
				
				logger.debug("ariticleDTO : "+articleDTO);
				articleService.updateArticle(articleDTO);
				logger.info("글만 수정");
			}
		}

				
		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
	}
}
