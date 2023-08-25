package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 2053973570967237161L;
	private MemberService service = MemberService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
		System.out.println("ListController init()...1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ListController doGet()...1");
		
		List<MemberDTO> members = service.selectMembers();
		
		// View에서 members를 참조하기 위해 requestScope 저장
		req.setAttribute("members", members);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
