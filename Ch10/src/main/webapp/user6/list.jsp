<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User6::list</title>
	</head>
	<body>
		<h3>User6 목록</h3>
		<a href="/Ch10">처음으로</a>
		<a href="/Ch10/user6/register.do">User6 등록</a>
		<table border="1">
			<tr>
				<th>이름</th>
				<th>생년월일</th>
				<th>나이</th>
				<th>주소</th>
				<th>휴대폰</th>
				<th>관리</th>
			</tr>
			<c:forEach var="user" items="${requestScope.users}">
				<tr>
					<td>${user.name}</td>
					<td>${user.birth}</td>
					<td>${user.age}</td>
					<td>${user.address}</td>
					<td>${user.hp}</td>
					<td>
						<a href="/Ch10/user6/modify.do?hp=${user.hp}">수정</a>
						<a href="/Ch10/user6/delete.do?hp=${user.hp}">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>