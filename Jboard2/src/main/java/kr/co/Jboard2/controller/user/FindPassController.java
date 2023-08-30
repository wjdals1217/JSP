package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.Jboard2.service.UserService;

@WebServlet("/user/findPass.do")
public class FindPassController extends HttpServlet{

	private static final long serialVersionUID = -6189906770490141610L;
	private UserService service = UserService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPass.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		
		// findPassChange로 넘길 uid 정보
		//Session의 경우 client 내에 저장되기 때문에 redirect 시 필요한 정보들을 넘겨줄 필요가 없다. 대신 사라지지 않기 때문에 부하가 올 수 있음 
		//request의 경우 서버에 저장되는 것이므로 redirect 하여 서버 이동이 일어나면 이전에 받아왔던 정보들을 redirect 시에 전달해주지 않으면 안 된다.
		//현재 클래스에서는 리다이렉트 시 파라미터 형태로 보내준 다음 파라미터의 내용을 getParameter로 받아야함
		HttpSession session = req.getSession();
		session.setAttribute("uid", uid);
			
		
		// findPassChange.do에서 uid 정보를 출력해야 하므로 redirect 해준다.
		resp.sendRedirect("/Jboard2/user/findPassChange.do");
	}
}
