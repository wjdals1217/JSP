<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User4::등록</title>
	</head>
	<body>
		<h3>User4 등록</h3>
		<a href="/Ch10">처음으로</a>
		<a href="/Ch10/user4/list.do">User4 목록</a>
		<form action="/Ch10/user4/register.do" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<label><input type="radio" name="gender" value="1"/>남자</label>
						<label><input type="radio" name="gender" value="2"/>여자</label>
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>