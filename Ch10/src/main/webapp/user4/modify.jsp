<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User4::수정</title>
	</head>
	<body>
		<h3>User4 수정</h3>
		<a href="/Ch10">처음으로</a>
		<a href="/Ch10/user4/list.do">User4 목록</a>
		
		<form action="/user4/modify.do">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${user.name}"/></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<c:choose>
							<c:when test="${user.gender eq 1}">
								<label><input type="radio" name="gender" value="1" checked/>남자</label>
								<label><input type="radio" name="gender" value="2"/>여자</label>	
							</c:when>
							<c:otherwise>
								<label><input type="radio" name="gender" value="1"/>남자</label>
								<label><input type="radio" name="gender" value="2" checked/>여자</label>
							</c:otherwise>
						</c:choose>
						
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="${user.age}"/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="${user.addr}"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="수정"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>