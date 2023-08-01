<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
 	String uid = request.getParameter("uid");
	

 	
 	try{
 		Context initCtx = new InitialContext();
 		Context ctx = (Context) initCtx.lookup("java:comp/env");
 		
 		DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
 		Connection conn = ds.getConnection();
 		
 		PreparedStatement psmt =  conn.prepareStatement("DELETE FROM `user7` WHERE `uid`=?");
 		psmt.setString(1, uid);
 		psmt.executeUpdate();
 		
 		psmt.close();
 		conn.close();
 		
 	}catch(Exception e) {
 		e.printStackTrace();
 	}

 	response.sendRedirect("./list.jsp");
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