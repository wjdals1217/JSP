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
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String oName = mr.getOriginalFileName("file");

		logger.debug("group : "+group);
		logger.debug("cate : "+cate);
		logger.debug("title : "+title);
		logger.debug("content : "+content);
		logger.debug("oName : "+oName);
		
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setTitle(title);
		articleDTO.setContent(content);
		articleDTO.setFile(oName);
		
		int ano = articleService.updateArticle(articleDTO);
		logger.debug("no : "+no);
		
		if(oName != null) {
			String sName = articleService.renameToFile(req, path, oName);
			
			FileDTO fileDTO = new FileDTO();
			fileDTO.setAno(ano);
			fileDTO.setOriName(oName);
			fileDTO.setNewName(sName);
			
			fileService.insertFile(fileDTO);
			
		}

		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
	}
}
