package kr.co.farmstory2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = -6259363977100195051L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	// 컨텍스트 경로(/Farmstory2) 전역변수(모든 컨트롤러에 선언)
		private String ctxPath;
		
		@Override
		public void init(ServletConfig config) throws ServletException {
			// 컨텍스트 경로(/Farmstory2)구하기  (최초 1번, 모든 컨트롤러에 정의)
			ctxPath = config.getServletContext().getContextPath();
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		logger.debug("success : "+success);
		
		req.setAttribute("success", success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login.jsp");
		dispatcher.forward(req, resp);				
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		String success = req.getParameter("success");
		int suc = 0;
		logger.debug("success800 : " + success);
		if(success != "") {
			suc = Integer.parseInt(success);
		}
		
		logger.debug("uid : "+uid);
		logger.debug("pass : "+pass);
		
		UserDTO user = service.selectUser(uid, pass);
		req.setAttribute("user", user);
		req.setAttribute("success", success);
		
		logger.info("login"+user);
		
		if(user != null){
			HttpSession session = req.getSession();
			session.setAttribute("sessUser", user);
			
			logger.debug("success800 : " + suc);
			
			if(suc == 800) {
				PrintWriter pw = resp.getWriter();
				pw.print("<script>");
				pw.print("history.go(-2);");
				pw.print("</script>");
				pw.close();
			}
			
			// 컨텍스트 경로 전역변수를 이용한 리다이렉트
			resp.sendRedirect(ctxPath);
		}else{
			
			resp.sendRedirect(ctxPath+"/user/login.do?success=100");
		}
	}
}
