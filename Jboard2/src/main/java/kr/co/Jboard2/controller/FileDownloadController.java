package kr.co.Jboard2.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

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

@WebServlet("/fileDownload.do")
public class FileDownloadController extends HttpServlet{

	private static final long serialVersionUID = -5631083447848695037L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService articleService = new ArticleService();
	private FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터 수신
		String fno = req.getParameter("fno");
		logger.debug("fno"+fno);
		
		// 파일 조회
		FileDTO fileDTO = fileService.selectFile(fno);
		logger.debug(fileDTO.toString());
		
		// 파일 다운로드
		articleService.downloadFile(req, resp, fileDTO);
	}
}
