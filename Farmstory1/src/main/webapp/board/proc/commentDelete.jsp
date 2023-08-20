<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("UTF-8");

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	String parent = request.getParameter("parent");
	String pg = request.getParameter("pg");
	
	ArticleDAO dao = new ArticleDAO();
	dao.deleteComment(no);
	
	// 댓글 카운트 수정
	dao.updateCommentCountMinus (parent);
	
	response.sendRedirect("/Farmstory1/board/view.jsp?group="+group+"&cate="+cate+"&no="+parent+"&pg="+pg);
		
%>