<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
        <div id="sub">
            <div><img src="${ctxPath}/images/sub_top_tit3.png" alt="CROP TALK"></div>
            <section class="croptalk">
                <aside>
                    <img src="${ctxPath}/images/sub_aside_cate3_tit.png" alt="농작물이야기"/>

                    <ul class="lnb">
                        <li class="${cate eq 'story'?'on':'off'}"><a href="${ctxPath}/board/list.do?group=Croptalk&cate=story">농작물이야기</a></li>
		                <li class="${cate eq 'grow'?'on':'off'}"><a href="${ctxPath}/board/list.do?group=Croptalk&cate=grow">텃밭가꾸기</a></li>
		                <li class="${cate eq 'school'?'on':'off'}"><a href="${ctxPath}/board/list.do?group=Croptalk&cate=school">귀농학교</a></li>
                    </ul>

                </aside>
                <article>
                <c:choose>
                	<c:when test="${cate eq 'story'}">
                		<nav>
                        	<img src="${ctxPath}/images/sub_nav_tit_cate3_tit1.png" alt="농작물가꾸기"/>
                        	<p>
                        		<img src="${ctxPath}/images/sub_page_nav_ico.gif" alt=""/>
                           		 HOME > 농작물이야기 > <em>농작물이야기</em>
                        	</p>
                    	</nav>
                	</c:when>
                	<c:when test="${cate eq 'grow'}">
                		<nav>
                        	<img src="${ctxPath}/images/sub_nav_tit_cate3_tit2.png" alt="텃밭가꾸기"/>
                        	<p>
                        		<img src="${ctxPath}/images/sub_page_nav_ico.gif" alt=""/>
                           		 HOME > 농작물이야기 > <em>텃밭가꾸기</em>
                        	</p>
                    	</nav>
                	</c:when>
                	<c:when test="${cate eq 'school'}">
                		<nav>
                        	<img src="${ctxPath}/images/sub_nav_tit_cate3_tit3.png" alt="귀농학교"/>
                        	<p>
                        		<img src="${ctxPath}/images/sub_page_nav_ico.gif" alt=""/>
                           		 HOME > 농작물이야기 > <em>귀농학교</em>
                        	</p>
                    	</nav>
                	</c:when>
                    
                </c:choose>

                    <!-- 내용 시작 -->