package kr.co.Jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.Jboard2.dto.UserDTO;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = -7102841903919237058L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 하지 않고 list를 볼 수 없게끔
		//현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		if(sessUser != null) {
			RequestDispatcher dispatcher= req.getRequestDispatcher("/list.jsp");
			dispatcher.forward(req, resp);
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
		
		
		
	}
}
