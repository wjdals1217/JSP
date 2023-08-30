package controller.user6;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User6DTO;
import service.User6Service;

@WebServlet("/user6/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 9050726340890586234L;
	private User6Service service = User6Service.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String hp = req.getParameter("hp");
		
		User6DTO user = service.selectUser6(hp);
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user6/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String age = req.getParameter("age");
		String address = req.getParameter("address");
		String hp = req.getParameter("hp");
		
		User6DTO dto = new User6DTO();
		dto.setName(name);
		dto.setBirth(birth);
		dto.setAge(age);
		dto.setAddress(address);
		dto.setHp(hp);
		
		service.updateUser6(dto);
		
		resp.sendRedirect("/Ch10/user6/list.do");
	}
}
