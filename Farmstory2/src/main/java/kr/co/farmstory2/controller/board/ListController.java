package kr.co.farmstory2.controller.board;

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

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 7261682355196660715L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = new ArticleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");		
		String pg = req.getParameter("pg");		
		String search = req.getParameter("search");
		String success = req.getParameter("success");
		logger.debug("group : "+group);
		logger.debug("cate : "+cate);
		logger.debug("pg : "+pg);
		logger.debug("search : "+search);
		logger.debug("success : "+success);
		
		int currentPage = service.getCurrentPage(pg);
		
		int start = service.getStartNum(currentPage);
		
		int total = service.selectCountTotal(cate, search);
		
		int lastPageNum = service.getLastPageNum(total);
		
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		List<ArticleDTO> articles = service.selectArticles(cate, search, start);
		
		req.setAttribute("articles", articles);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("search", search);
		req.setAttribute("success", success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/list.jsp");
		dispatcher.forward(req, resp);
	}
}
