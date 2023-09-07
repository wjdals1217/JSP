<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Farmstory2/admin/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script>
    	const success = ${success};
    	console.log(success);
    	
    	if(success == 200){
    		alert('상품등록이 완료되었습니다.');
    	}
    </script>
    
</head>
<body>
    <div id="container">
        <header>
            <a href="/Farmstory2/admin" class="logo"><img src="/Farmstory2/admin/images/admin_logo.jpg" alt="로고"/></a>
            <p>
                <a href="/Farmstory2">HOME |</a>
                <a href="${ctxPath}/user/logout.do">로그아웃 |</a>
                <a href="${ctxPath}/board/list.do?group=Community&cate=qna">고객센터</a>
            </p>
        </header>