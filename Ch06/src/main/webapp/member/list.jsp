<%@page import="vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String name = request.getParameter("name");	
	String hp = request.getParameter("hp");
	String pos = request.getParameter("pos");
	String dep = request.getParameter("dep");

	String host = "jdbc:mysql://13.124.41.119:3306/UserDB";
	String user = "userdb";
	String pass = "UuuS1235789@";
	
	List<MemberVO> members = new ArrayList<>();
 	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `member`");
	
		while(rs.next()) {
			MemberVO vo = new MemberVO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setPos(rs.getString(4));
			vo.setDep(rs.getString(5));
			vo.setRdate(rs.getString(6));
			
			members.add(vo);
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Member::list</title>
	</head>
	<body>
		<h3>Member 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/register.jsp">Member 등록</a>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>직급</th>
				<th>부서</th>
				<th>입사일</th>
				<th>관리</th>
			</tr>
			<%for(MemberVO vo : members) { %>
				<tr>
					<td><%= vo.getUid() %></td>
					<td><%= vo.getName() %></td>
					<td><%= vo.getHp() %></td>
					<td><%= vo.getPos() %></td>
					<td>
					<%
					if(vo.getDep().equals("101")){ 
						out.print("영업1부");
					}else if(vo.getDep().equals("102")){
						out.print("영업2부");
					}else if(vo.getDep().equals("103")){
						out.print("영업3부");
					}else if(vo.getDep().equals("104")){
						out.print("영업4부");
					}else if(vo.getDep().equals("105")){
						out.print("영업5부");
					}else if(vo.getDep().equals("106")){
						out.print("영업지원부");
					}else if(vo.getDep().equals("107")){
						out.print("인사부");
					}
					%>
					</td>
					<td><%= vo.getRdate().substring(0, 10) %></td>
					<td>
						<a href="/Ch06/member/modify.jsp?uid=<%=vo.getUid()%>">수정</a>
						<a href="/Ch06/member/delete.jsp?uid=<%=vo.getUid()%>">삭제</a>
					</td>
				</tr>
			<% } %>
		</table>
		
	</body>
</html>