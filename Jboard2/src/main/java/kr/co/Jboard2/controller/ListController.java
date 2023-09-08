package kr.co.Jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.dto.ArticleDTO;
import kr.co.Jboard2.dto.UserDTO;
import kr.co.Jboard2.service.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = -7102841903919237058L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = new ArticleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 하지 않고 list를 볼 수 없게끔
		//현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		// 데이터 수신
		String pg  = req.getParameter("pg");
		String search  = req.getParameter("search");
		String success  = req.getParameter("success");
		logger.debug("pg : "+pg);
		logger.debug("search : "+search);
		
		// 현재 페이지 계산
		int currentPage = service.getCurrentPage(pg);
		
		// Limit 시작값 계산
		int start = service.getStartNum(currentPage);
		
		// 전체 게시물 개수 조회
		int total = service.selectCountTotal(search);
		
		// 마지막 페이지 번호 계산
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 계산
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호 계산
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 현재 페이지 게시물 조회
		List<ArticleDTO> articles = service.selectArticles(start, search);
		
		
		if(sessUser != null) { // 로그인 했을 때 list 페이지 forward
			
			// html에서 requestScope를 이용해 데이터 호출이 가능하도록 setAtrribute
			req.setAttribute("articles", articles);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("lastPageNum", lastPageNum);
			req.setAttribute("pageGroupStart", result[0]);
			req.setAttribute("pageGroupEnd", result[1]);
			req.setAttribute("pageStartNum", pageStartNum+1);
			req.setAttribute("search", search);
			req.setAttribute("success", success);
			
			RequestDispatcher dispatcher= req.getRequestDispatcher("/list.jsp");
			dispatcher.forward(req, resp);
			
		}else { // 로그인 하지 않았을 때 login 페이지로 리다이렉트
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
}
