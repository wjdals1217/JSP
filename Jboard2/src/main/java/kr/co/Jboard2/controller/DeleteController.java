package kr.co.Jboard2.controller;

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

import kr.co.Jboard2.dto.FileDTO;
import kr.co.Jboard2.service.ArticleService;
import kr.co.Jboard2.service.FileService;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -182229155581389332L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = new ArticleService();
	private FileService fileService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글번호 수신
		String no = req.getParameter("no");
		logger.debug("no : "+no);
		
		// 파일 삭제(DB)
		List<FileDTO> files = fileService.deleteFile(no);
		
		// 파일 삭제(디렉토리)
		if(files != null) { // 첨부 파일이 있을 때
			
			// 파일이 저장되어 있는 디렉토리 경로
			String path= articleService.getFilePath(req);
			
			for(FileDTO filedto :files) {
				File file = new File(path+"/"+filedto.getNewName());
			
				if(file.exists()) {
					file.delete();				
				}
			}

		}
		
		// 글, 댓글 삭제
		articleService.deleteArticle(no);
		
		// 리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	}
}
