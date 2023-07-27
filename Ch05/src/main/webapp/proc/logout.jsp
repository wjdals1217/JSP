<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 세션 해제
	session.invalidate();

	// 쿠키 해제
	Cookie cookie = new Cookie("cid", null);
	cookie.setMaxAge(0);
	response.addCookie(cookie);
	
	// 리다이렉트
	response.sendRedirect("./loginForm.jsp");

%>