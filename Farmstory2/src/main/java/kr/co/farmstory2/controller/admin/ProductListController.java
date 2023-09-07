package kr.co.farmstory2.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productList.do")
public class ProductListController extends HttpServlet{

	private static final long serialVersionUID = 1901764000550809004L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = new ArticleService();
	private ProductService pService = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		
		int currentPage = aService.getCurrentPage(pg);
		int start = aService.getStartNum(currentPage);
		int total = pService.selectCountProductsTotal();
		int lastPageNum = aService.getLastPageNum(total);
		int[] result = aService.getPageGroupNum(currentPage, lastPageNum);
		int pageStartNum = aService.getPageStartNum(total, currentPage);
		
		List<ProductDTO> products = pService.selectProducts(start);
		
		logger.debug(products.toString());
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("products", products);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productList.jsp");
		dispatcher.forward(req, resp);
	}
}
