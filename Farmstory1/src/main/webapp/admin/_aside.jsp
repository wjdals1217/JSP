<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cate = request.getParameter("cate");
%>
<aside>
    <h3>주요기능</h3>
    <ul>
        <li class="<%=cate.equals("product")?"on":""%>"><a href="./productList.jsp?cate=product">상품관리</a></li>
        <li class="<%=cate.equals("order")?"on":""%>"><a href="./orderList.jsp?cate=order">주문관리</a></li>
        <li class="<%=cate.equals("user")?"on":""%>"><a href="./userList.jsp?cate=user">회원관리</a></li>                    
    </ul>
    
</aside>
