<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!--
	- group 값을 참조하기 위해 include 태그를 사용
	- include 지시자는 표현언어 참조 안됨
-->
<jsp:include page="./_aside${group}.jsp"/>
<script>
	$(window).on("pageshow", function (event) {
	    if (event.originalEvent.persisted || (window.performance && window.performance.navigation.type == 2)) {
	        console.log('BFCahe로부터 복원됨');
	        location.reload();
	     } else {
	        console.log('새로 열린 페이지');
	     }
	});
</script>
		<div id="board">
			<section class="list">
				<form id="search" action="${ctxPath}/board/list.do" method="get">
					<input type="hidden" name="group" value="${group}"/>
					<input type="hidden" name="cate" value="${cate}"/>
					<input type="text" name="search" placeholder="제목 키워드 검색">
                    <input class="btnSubmit" type="submit" value="검색">
				</form>
			    <h3>글목록</h3>
			    <article>
			        <table border="0">
			            <tr>
			                <th>번호</th>
			                <th>제목</th>
			                <th>글쓴이</th>
			                <th>날짜</th>
			                <th>조회</th>
			            </tr>
			            <c:forEach var="article" items="${requestScope.articles}">
			            	<tr>
			                <td>${pageStartNum = pageStartNum - 1}</td>
			                <td><a href="${ctxPath}/board/view.do?group=${group}&cate=${cate}&no=${article.no}">${article.title}</a>&nbsp;[${article.comment}]</td>
			                <td>${article.nick}</td>
			                <td>${article.rdate}</td>
			                <td>${article.hit}</td>
			            </tr>
			            </c:forEach>
			            
			        </table>
			    </article>
			
			    <!-- 페이지 네비게이션 -->
		        <div class="paging">
		            <c:if test="${pageGroupStart > 1}">
		            	<a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupStart - 1}&search=${search}" class="prev">이전</a>
		            </c:if>
		            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		            	<a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}&pg=${i}&search=${search}" class="num ${currentPage == i?'current':'off'}">${i}</a>
		            </c:forEach>
		            <c:if test="${pageGroupEnd < lastPageNum}">
		            	<a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupEnd + 1}&search=${search}" class="next">다음</a>
		            </c:if>
		        </div>
			
			    <!-- 글쓰기 버튼 -->
			    <a href="${ctxPath}/board/write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>
			    <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
			</section>
		</div>
			<!-- 내용 끝 -->

        </article>
    </section>
</div>	
<%@ include file="../_footer.jsp"%>