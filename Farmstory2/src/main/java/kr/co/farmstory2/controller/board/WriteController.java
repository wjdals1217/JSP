package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = -4364857808561820306L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = new ArticleService();
	private FileService fileService = new FileService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		logger.debug("group : "+group);
		logger.debug("cate : "+cate);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MultipartRequest mr = articleService.uploadFile(req);
		
		String group = mr.getParameter("group");
		String cate = mr.getParameter("cate");
		String writer = mr.getParameter("writer");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String oName = mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();

		logger.debug("group : "+group);
		logger.debug("cate : "+cate);
		logger.debug("writer : "+writer);
		logger.debug("title : "+title);
		logger.debug("content : "+content);
		logger.debug("oName : "+oName);
		logger.debug("regip : "+regip);
		
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setCate(cate);
		articleDTO.setTitle(title);
		articleDTO.setContent(content);
		articleDTO.setFile(oName);
		articleDTO.setWriter(writer);
		articleDTO.setRegip(regip);
		
		int no = articleService.insertArticle(articleDTO);
		logger.debug("no : "+no);
		
		if(oName != null) {
			String sName = articleService.renameToFile(req, oName);
			
			FileDTO fileDTO = new FileDTO();
			fileDTO.setAno(no);
			fileDTO.setOriName(oName);
			fileDTO.setNewName(sName);
			
			fileService.insertFile(fileDTO);
			
		}

		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
		
	}
}
