<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
        <div id="sub">
            <div><img src="${ctxPath}/images/sub_top_tit4.png" alt="CROP TALK"></div>
            <section class="event">
                <aside>
                    <img src="${ctxPath}/images/sub_aside_cate4_tit.png" alt="이벤트"/>

                    <ul class="lnb">
                        <li class="on"><a href="${ctxPath}/board/list.do?group=Event&cate=event">이벤트</a></li>
                    </ul>

                </aside>
                <article>
                 <c:choose>
                	<c:when test="${cate eq 'event'}">
                		<nav>
                        	<img src="${ctxPath}/images/sub_nav_tit_cate4_tit1.png" alt="이벤트"/>
                        	<p>
                        		<img src="${ctxPath}/images/sub_page_nav_ico.gif" alt=""/>
                           		 HOME > 이벤트 > <em>이벤트</em>
                        	</p>
                    	</nav>
                	</c:when>
                </c:choose>

                    <!-- 내용 시작 -->