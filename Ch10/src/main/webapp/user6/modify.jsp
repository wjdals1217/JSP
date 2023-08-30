<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User6::수정</title>
	</head>
	<body>
		<h3>User6 수정</h3>
		<a href="/Ch10">처음으로</a>
		<a href="/Ch10/user6/list.do">User6 목록</a>
		<form action="/Ch10/user6/modify.do" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${user.name}"/></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="birth" value="${user.birth}"/></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value="${user.age}"/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" value="${user.address}"/></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="${user.hp}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="수정"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>