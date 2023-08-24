<%@page import="kr.farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String[] chks = request.getParameterValues("chk");
	OrderDAO dao = new OrderDAO();
	
	for(String chk : chks) {
		dao.deleteOrder(chk);		
	}
	
	response.sendRedirect("/Farmstory1/admin/orderList.jsp?cate=order");
%>