<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	MemberVO vo = null;
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `member` WHERE `uid`=?");
		psmt.setString(1, uid);
		ResultSet rs =  psmt.executeQuery();
		
		if(rs.next()) {
			vo = new MemberVO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setPos(rs.getString(4));
			vo.setDep(rs.getString(5));
			vo.setRdate(rs.getString(6));
		}
		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Member::modify</title>
	</head>
	<body>
		<h3>Member 수정</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/list.jsp">Member 목록</a>
		
		<form action="/Ch06/member/modifyProc.jsp">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value="<%= vo.getUid() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%= vo.getName() %>"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="<%= vo.getHp() %>"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<option value="<%= vo.getPos() %>">사원</option>
							<option value="<%= vo.getPos() %>">부장</option>
							<option value="<%= vo.getPos() %>">차장</option>
							<option value="<%= vo.getPos() %>">대리</option>
							<option value="<%= vo.getPos() %>">과장</option>
							<option value="<%= vo.getPos() %>">사장</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep" value="<%= vo.getDep() %>">
							<option value="101">영업1부</option>
							<option value="102">영업2부</option>
							<option value="103">영업3부</option>
							<option value="104">영업4부</option>
							<option value="105">영업5부</option>
							<option value="106">영업지원부</option>
							<option value="107">인사부</option>
						</select>
					</td>
				</tr>
				<tr>
				<tr>
					<td>입사일</td>
					<td><input type="datetime-local" name="rdate" value="<%= vo.getRdate() %>"></td>
				</tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>