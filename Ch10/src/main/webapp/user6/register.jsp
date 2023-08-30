<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User6::등록</title>
	</head>
	<body>
		<h3>User6 등록</h3>
		<a href="/Ch10">처음으로</a>
		<a href="/Ch10/user6/list.do">User6 목록</a>
		<form action="/Ch10/user6/register.do" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="birth"/></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address"/></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" name="등록"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>