package kr.co.farmstory2.controller.market;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.ProductService;
import kr.co.farmstory2.service.UserService;
import kr.co.farmstory2.utils.Utils;

@WebServlet("/market/order.do")
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = -5934561097967954475L;
	private Utils utils = new Utils();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String thumb2 = req.getParameter("thumb2");
		String pName = req.getParameter("pName");
		String pNo = req.getParameter("pNo");
		String delivery = req.getParameter("delivery");
		String price = req.getParameter("price");
		String count = req.getParameter("count");
		String total = req.getParameter("total");
		String finalPrice = req.getParameter("final");
		
		int totalInt = Integer.parseInt(total);
		
		if(totalInt >=30000) {
			delivery = "0";
			finalPrice = total;
		}
		
		UserDTO user = service.selectUser(uid);
		
		req.setAttribute("thumb2", thumb2);
		req.setAttribute("pName", pName);
		req.setAttribute("pNo", pNo);
		req.setAttribute("delivery", delivery);
		req.setAttribute("deliveryWithComma", utils.comma(delivery));
		req.setAttribute("price", price);
		req.setAttribute("priceWithComma", utils.comma(price));
		req.setAttribute("count", count);
		req.setAttribute("finalPrice", finalPrice);
		req.setAttribute("finalPriceWithComma", utils.comma(finalPrice));
		req.setAttribute("user", user);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/market/order.jsp");
		dispatcher.forward(req, resp);
	}
	
}
