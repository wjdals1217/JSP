package kr.co.famrstory2.controller.introduction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/introduction/direction.do")
public class DirectionController extends HttpServlet {

	private static final long serialVersionUID = 1271998289565608186L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/introduction/direction.jsp");
		dispatcher.forward(req, resp);
	}
}
