<%@page import="vo.User5VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	String gender = request.getParameter("gender");
	String age = request.getParameter("age");
	String address = request.getParameter("address");
	String hp = request.getParameter("hp");

	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	List<User5VO> users = new ArrayList<>();
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user5`");
		
		while(rs.next()) {
			User5VO vo = new User5VO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirth(rs.getString(3));
			vo.setGender(rs.getString(4));
			vo.setAge(rs.getString(5));
			vo.setAddress(rs.getString(6));
			vo.setHp(rs.getString(7));
			
			users.add(vo);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}


%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User5::list</title>
	</head>
	<body>
	<h3>User5 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user5/register.jsp">User5 등록</a>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>생년월일</th>
				<th>성별</th>
				<th>나이</th>
				<th>주소</th>
				<th>휴대폰</th>
				<th>관리</th>
			</tr>
			<%for(User5VO vo : users) { %>
				<tr>
					<td><%= vo.getUid() %></td>
					<td><%= vo.getName() %></td>
					<td><%= vo.getBirth() %></td>
					<td><%= vo.getGender() %></td>
					<td><%= vo.getAge() %></td>
					<td><%= vo.getAddress() %></td>
					<td><%= vo.getHp() %></td>
					<td>
						<a href="/Ch06/user5/modify.jsp?uid=<%=vo.getUid()%>">수정</a>
						<a href="/Ch06/user5/delete.jsp?uid=<%=vo.getUid()%>">삭제</a>
					</td>
				</tr>
			<% } %>
		</table>
		
	</body>
</html>