package controller.user5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User5Service;

@WebServlet("/user5/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -5429937399248363554L;
	private User5Service service = User5Service.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		
		service.deleteUser5(uid);
		
		resp.sendRedirect("/Ch10/user5/list.do");
	}
	
}
