package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/findIdResult.do")
public class FindIdResultController extends HttpServlet{

	private static final long serialVersionUID = 1211601750307609032L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findIdResult.jsp");
		dispatcher.forward(req, resp);				
	}
}
