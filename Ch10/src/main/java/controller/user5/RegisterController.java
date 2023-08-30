package controller.user5;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User5DTO;
import service.User5Service;

@WebServlet("/user5/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 8312637816853853119L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private User5Service service = User5Service.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user5/register.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		String address = req.getParameter("address");
		String hp = req.getParameter("hp");
		
		User5DTO dto = new User5DTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setBirth(birth);
		dto.setGender(gender);
		dto.setAge(age);
		dto.setAddress(address);
		dto.setHp(hp);
		
		service.insertUser5(dto);
		
		resp.sendRedirect("/user5/list.do");
	}
	
}
