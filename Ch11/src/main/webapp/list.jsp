<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>직원 목록</title>
	</head>
	<body>
		<h3>Member 목록</h3>
		<a href="/Ch11">처음으로</a>
		<a href="/Ch11/register.do">Member 등록</a>
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
			<c:forEach var="member" items="${requestScope.members}">
				<tr>
					<td>${member.getUid()}</td>
					<td>${member.getName()}</td>
					<td>${member.getHp()}</td>
					<td>${member.getPos()}</td>
					<c:choose>
						<c:when test="${member.getDep() eq '101' }"><td>영업1부</td></c:when>
						<c:when test="${member.getDep() eq '102' }"><td>영업2부</td></c:when>
						<c:when test="${member.getDep() eq '103' }"><td>영업3부</td></c:when>
						<c:when test="${member.getDep() eq '104' }"><td>영업4부</td></c:when>
						<c:when test="${member.getDep() eq '105' }"><td>영업5부</td></c:when>
						<c:when test="${member.getDep() eq '106' }"><td>영업지원부</td></c:when>
						<c:otherwise><td>인사부</td></c:otherwise>
					</c:choose>
					<td>${member.getRdate()}</td>
					<td>
						<a href="/Ch11/modify.do?uid=${member.getUid()}">수정</a>
						<a href="/Ch11/delete.do?uid=${member.getUid()}">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</body>
</html>