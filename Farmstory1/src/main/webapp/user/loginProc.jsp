<%@page import="kr.farmstory1.dto.UserDTO"%>
<%@page import="kr.farmstory1.dao.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	UserDAO dao = UserDAO.getInstance();
	
	UserDTO dto = dao.selectUser(uid, pass);
	
	if(dto != null) {
		session.setAttribute("sessUser", dto);
		response.sendRedirect("/Farmstory1");
	}else{
		response.sendRedirect("/Farmstory1");
		
	}
%>