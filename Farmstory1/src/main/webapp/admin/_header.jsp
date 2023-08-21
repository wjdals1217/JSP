<%@page import="kr.farmstory1.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String success = request.getParameter("success");	

	UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
	
	if(sessUser == null) {
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Farmstory1/admin/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script src="/Farmstory1/admin/js/popup.js"></script>
    <script>
    	const success = "<%= success %>";
    	if(success == 'register'){
    		alert('등록 완료');
    	}
    </script>

</head>
<body>
    <div id="container">
        <header>
            <a href="./index.jsp" class="logo"><img src="./images/admin_logo.jpg" alt="로고"/></a>
            <p>
                <a href="/Farmstory1">HOME |</a>
                <a href="/Farmstory1/user/logout.jsp">로그아웃 |</a>
                <a href="/Farmstory1/board/list.jsp?group=Community&cate=qna">고객센터</a>
            </p>
        </header>
