<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modify</title>
	</head>
	<body>
		<h3>Member 수정</h3>
		<a href="/Ch11">처음으로</a>
		<a href="/Ch11/list.do">Member 목록</a>
		
		<form action="/Ch11/modify.do" method="post">
			<table border="1">
				<!-- 표현어에서는 private이 적용이 되지 않아서 바로 호출이 가능 -->
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value="${member.getUid()}"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"value="${member.name}"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="${member.hp}"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<c:choose>
								<c:when test="${member.pos eq '사원'}"><option selected>사원</option></c:when>
								<c:otherwise><option>사원</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '대리'}"><option selected>대리</option></c:when>
								<c:otherwise><option>사원</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '차장'}"><option selected>차장</option></c:when>
								<c:otherwise><option>사원</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '부장'}"><option selected>부장</option></c:when>
								<c:otherwise><option>사원</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '과장'}"><option selected>과장</option></c:when>
								<c:otherwise><option>사원</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '사장'}"><option selected>사장</option></c:when>
								<c:otherwise><option>사원</option></c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep">
							<c:choose>
								<c:when test="${member.dep eq '101'}"><option selected value="101">영업1부</option></c:when>
								<c:otherwise><option value="101">영업1부</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '102'}"><option selected value="102">영업2부</option></c:when>
								<c:otherwise><option value="102">영업2부</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '103'}"><option selected value="103">영업3부</option></c:when>
								<c:otherwise><option value="103">영업3부</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '104'}"><option selected value="104">영업4부</option></c:when>
								<c:otherwise><option value="104">영업4부</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '105'}"><option selected value="105">영업5부</option></c:when>
								<c:otherwise><option value="105">영업5부</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '106'}"><option selected value="106">영업지원부</option></c:when>
								<c:otherwise><option value="106">영업지원부</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '107'}"><option selected value="107">인사부</option></c:when>
								<c:otherwise><option value="107">인사부</option></c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="등록">
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>