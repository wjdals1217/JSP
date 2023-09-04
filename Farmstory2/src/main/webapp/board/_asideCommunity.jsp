<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sub">
    <div><img src="../images/sub_top_tit5.png" alt="COMMUNITY"></div>
    <section class="community">
        <aside>
            <img src="../images/sub_aside_cate5_tit.png" alt="커뮤니티"/>

            <ul class="lnb">
                <li class="${cate eq 'notice'?'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=notice">농작물이야기</a></li>
                <li class="${cate eq 'menu'?'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=menu">오늘의식단</a></li>
                <li class="${cate eq 'chef'?'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=chef">나도요리사</a></li>
                <li class="${cate eq 'qna'?'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=qna">1:1고객문의</a></li>
                <li class="${cate eq 'faq'?'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=faq">자주묻는 질문</a></li>
            </ul>

        </aside>
        <article>
            <c:choose>
                	<c:when test="${cate eq 'notice'}">
                		<nav>
                        	<img src="../images/sub_nav_tit_cate5_tit1.png" alt="공지사항"/>
                        	<p>
                           		 HOME > 커뮤니티 > <em>공지사항</em>
                        	</p>
                    	</nav>
                	</c:when>
                	<c:when test="${cate eq 'menu'}">
                		<nav>
                        	<img src="../images/sub_nav_tit_cate5_tit2.png" alt="오늘의식단"/>
                        	<p>
                           		 HOME > 커뮤니티 > <em>오늘의식단</em>
                        	</p>
                    	</nav>
                	</c:when>
                	<c:when test="${cate eq 'chef'}">
                		<nav>
                        	<img src="../images/sub_nav_tit_cate5_tit3.png" alt="나도요리사"/>
                        	<p>
                           		 HOME > 커뮤니티 > <em>나도요리사</em>
                        	</p>
                    	</nav>
                	</c:when>
                	<c:when test="${cate eq 'qna'}">
                		<nav>
                        	<img src="../images/sub_nav_tit_cate5_tit4.png" alt="1:1고객문의"/>
                        	<p>
                           		 HOME > 커뮤니티 > <em>1:1고객문의</em>
                        	</p>
                    	</nav>
                	</c:when>
                	<c:when test="${cate eq 'faq'}">
                		<nav>
                        	<img src="../images/sub_nav_tit_cate5_tit5.png" alt="자주묻는질문"/>
                        	<p>
                           		 HOME > 커뮤니티 > <em>자주묻는질문</em>
                        	</p>
                    	</nav>
                	</c:when>
                </c:choose>
            <!-- 내용시작 -->