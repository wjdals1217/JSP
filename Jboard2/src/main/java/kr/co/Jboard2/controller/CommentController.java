package kr.co.Jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Jboard2.dto.ArticleDTO;
import kr.co.Jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 5175561048127790378L;
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	private ArticleService service = new ArticleService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		logger.debug("parent : " + parent);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("regip : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		if(content != null) {
			// 댓글 입력
			int result = service.insertComment(dto);
			
			// 댓글 카운트 수정 Plus
			service.updateArticleForCommentPlus(parent);
			
			// 리다이렉트(ajax로 요청했기 때문에 json으로 출력해야한다.)
			//resp.sendRedirect("/Jboard2/view.do?no="+parent);
			
			// Json 출력(AJAX 요청)
			JsonObject json = new JsonObject();
			json.addProperty("result", result);
			resp.getWriter().print(json);
			
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kind = req.getParameter("kind");
		String no = req.getParameter("no");
		String parent = req.getParameter("parent");
		int result = 0;
		
		switch(kind) {
			case "REMOVE":
				result = service.deleteComment(no);
				logger.debug("result : "+result);
				service.updateArticleForCommentMinus(parent);
				
				break;
				
		}

		// Json 출력(AJAX 요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
}
