<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
 	String uid = request.getParameter("uid");
	
 	// 데이터베이스처리
 	String host = "jdbc:mysql://localhost:3306/userdb";
 	String user = "root";
 	String pass = "1234";
 	
 	try{
 		Class.forName("com.mysql.cj.jdbc.Driver");
 		Connection conn = DriverManager.getConnection(host, user, pass);
 		PreparedStatement psmt =  conn.prepareStatement("DELETE FROM `user2` WHERE `uid`=?");
 		psmt.setString(1, uid);
 		psmt.executeUpdate();
 		
 		psmt.close();
 		conn.close();
 		
 	}catch(Exception e) {
 		e.printStackTrace();
 	}

 	response.sendRedirect("/Ch06/user2/list.jsp");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::delete</title>
	</head>
	<body>
		
	</body>
</html>