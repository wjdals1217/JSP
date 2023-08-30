package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Jboard2.service.UserService;

@WebServlet("/user/myInfo.do")
public class MyInfoController extends HttpServlet{

	private static final long serialVersionUID = 7696394098194933411L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/myInfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String kind = req.getParameter("kind");
		String uid = req.getParameter("uid");
		
		logger.debug("kind : " +kind);
		logger.debug("uid : " +uid);
		
		switch(kind) {
		case "WITHDRAW" :
			int result = service.updateUserForWithdraw(uid);
			
			JsonObject json = new JsonObject();
			json.addProperty("result", result);
			resp.getWriter().print(json);
			
			// AJAX로 요청했기 때문에 json 데이터로 data를 받아야 하므로 리다이렉트는 쓰지 못한다.
			//resp.sendRedirect("/Jboard2/user/login.do?success=400");
			break;
		case "PASSWORD" :
			service.updateUserForWithdraw(uid);
			break;
		case "MODIFY" :
			service.updateUserForWithdraw(uid);
			break;
		}
		
	}
}
