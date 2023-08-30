package kr.co.Jboard2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Jboard2.service.UserService;

@WebServlet("/user/authEmail.do")
public class AuthEmailController extends HttpServlet {

	private static final long serialVersionUID = 1415131563025867896L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		int result = 0;
		int status= 0;
		 	
		if(type.equals("REGISTER")) {
			// 회원가입할 때 이메일 인증
			result = service.selectCountEmail(email);
			status = service.sendCodeByEmail(email);

		}else if(type.equals("FIND_ID")) {
			// 아이디 찾기 할 때 이메일 인증
			result = service.selectCountNameAndEmail(name, email);
			if(result == 1) {
				status = service.sendCodeByEmail(email);
			}
		}
		
		
		logger.info("result : "+result);
		logger.info("status : "+status);
		
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("status", status);
		json.addProperty("result", result);		
		
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		int result = service.confirmCodeByEmail(code);
		
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}
