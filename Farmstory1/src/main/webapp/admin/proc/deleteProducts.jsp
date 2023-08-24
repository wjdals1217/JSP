<%@page import="kr.farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String[] chks = request.getParameterValues("chk");
	ProductDAO dao = new ProductDAO();
	for(String chk : chks){
		dao.deleteProduct(chk);
	}
	response.sendRedirect("/Farmstory1/admin/productList.jsp?cate=product");
%>