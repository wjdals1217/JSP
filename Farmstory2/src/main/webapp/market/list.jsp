<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>

        <div id="sub">
    <div><img src="${ctxPath}/images/sub_top_tit2.png" alt="MARKET"></div>
    <section class="market">
        <aside>
            <img src="${ctxPath}/images/sub_aside_cate2_tit.png" alt="장보기"/>

            <ul class="lnb">
                <li class="on"><a href="${ctxPath}/market/list.do">장보기</a></li>
            </ul>
        </aside>
        <article class="list">
            <nav>
                <img src="${ctxPath}/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>

            <!-- 내용 시작 -->
            <p class="sort">
                <a href="./list.do?type=0" class="${type==0 ? 'on':'off'}">전체<c:if test="${type==0}">(${total})</c:if>&nbsp;|</a>
                <a href="./list.do?type=1" class="${type==1 ? 'on':'off'}">과일<c:if test="${type==1}">(${total})</c:if>&nbsp;|</a>
                <a href="./list.do?type=2" class="${type==2 ? 'on':'off'}">야채<c:if test="${type==2}">(${total})</c:if>&nbsp;|</a>
                <a href="./list.do?type=3" class="${type==3 ? 'on':'off'}">곡류<c:if test="${type==3}">(${total})</c:if>&nbsp;</a>
            </p>
            <table border="0">
            	<c:forEach var="product" items="${products}">
	                <tr>
	                    <td>
	                        <a href="./view.do?pNo=${product.pNo}"><img src="${ctxPath}/thumb/${product.thumb1}" class="thumb" alt="${product.pName}"></a>
	                    </td>
	                    <td>
		                    <c:choose>
		                    	<c:when test="${product.type==1}">과일</c:when>
		                    	<c:when test="${product.type==2}">야채</c:when>
		                    	<c:when test="${product.type==3}">곡물</c:when>
							</c:choose>
						</td>
	                    <td><a href="./view.do?pNo=${product.pNo}">${product.pName}</a></td>
	                    <td><strong>${product.getPriceWithComma()}</strong>원</td>
	                </tr>
                </c:forEach>
            </table>

            <div class="paging">
	        	<c:if test="${pageGroupStart > 1}">
	            	<a href="${ctxPath}/market/list.do?type=${type}&pg=${pageGroupStart - 1}" class="prev">이전</a>
	            </c:if>
	            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
	            	<a href="${ctxPath}/market/list.do?type=${type}&pg=${i}" class="num ${currentPage == i?'current':'off'}">${i}</a>
	            </c:forEach>
	            <c:if test="${pageGroupEnd < lastPageNum}">
	            	<a href="${ctxPath}/market/list.do?type=${type}&pg=${pageGroupEnd + 1}" class="next">다음</a>
	            </c:if>
	        </div>
            <!-- 내용 끝 -->

        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>