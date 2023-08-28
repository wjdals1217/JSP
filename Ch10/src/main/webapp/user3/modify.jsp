<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User3::modify</title>
	</head>
	<body>
		<h3>User3 수정</h3>
		<a href="/Ch10">처음으로</a>
		<a href="/Ch10/user3/list.do">목록</a>
		<form action="/Ch10/user3/modify.do" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" value="${user.uid}"/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp"/></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"/></td>
				</tr>
				<tr >
					<td colspan="2" align="right"><input type="submit" value="수정"/></td>
				</tr>
			</table>
		</form>
		
	</body>
</html>