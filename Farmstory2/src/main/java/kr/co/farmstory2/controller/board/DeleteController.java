package kr.co.farmstory2.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -4550516370748336927L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = new ArticleService();
	private FileService fileService = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		
		logger.debug("group : "+group);
		logger.debug("cate : "+cate);
		logger.debug("no : "+no);
		
		List<String> snames = fileService.deleteFileInArticle(no);
		
		articleService.deleteArticle(no);
		
		// 파일 삭제(Directory)
				if(!snames.isEmpty()) {
					String path = articleService.getPath(req, "/upload");
					
					for(String sname : snames) {
						File file = new File(path+"/"+sname);
						
						if(file.exists()) {
							file.delete();	
						}
					}
				}
				
				// 리다이렉트
				resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate+"&success=404");
	}
}
