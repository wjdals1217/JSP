package controller.user6;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User6Service;

@WebServlet("/user6/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 2003100772449737340L;
	private User6Service service = User6Service.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hp = req.getParameter("hp");
		
		service.deleteUser6(hp);
		
		resp.sendRedirect("/Ch10/user6/list.do");
	}
	
}
