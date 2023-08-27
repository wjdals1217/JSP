package controller.user2;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.User2Service;
@WebServlet("/user2/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -6559824777220358499L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	User2Service service = new User2Service();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger.info("DeleteController init()...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		service.deleteUser2(uid);
		resp.sendRedirect("/Ch10/user2/list.do");
	}

}
