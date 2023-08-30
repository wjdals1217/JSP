package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.Jboard2.dto.UserDTO;
import kr.co.Jboard2.service.UserService;

@WebServlet("/user/findPassChange.do")
public class FindPassChangeController extends HttpServlet{

	private static final long serialVersionUID = -4307944619787172691L;
	private UserService service = UserService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uid = (String) session.getAttribute("uid");
		
		if(uid == null) {
			resp.sendRedirect("/Jboard2/user/findPass.do");
		}else {
		// redirect 요청은 GET 방식으로 받음
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPassChange.jsp");
		dispatcher.forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pass = req.getParameter("pass1");
		String uid = req.getParameter("uid");
		
		service.updateUserPass(pass, uid);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=300");
	}
}
