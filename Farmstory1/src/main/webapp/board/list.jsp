<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	
	pageContext.include("./_aside"+group+".jsp");
	
	ArticleDAO dao = new ArticleDAO();
	dao.selectArticles();
%>

	<section class="list">
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
					<tr>
						<td>1</td>
						<td><a href="/Farmstory1/board/view.jsp?group=<%= group %>&cate=<%= cate %>">제목</a>&nbsp;[3]</td>
						<td>글쓴이</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
			</table>
		</article>
	
		<!-- 페이지 네비게이션 -->
		<div class="paging">
			<a href="#" class="prev">이전</a>
			<a href="#" class="num current">1</a>
			<a href="#" class="next">다음</a>
		</div>
	
		<!-- 글쓰기 버튼 -->
		<a href="/Farmstory1/board/write.jsp?group=<%= group %>&cate=<%= cate %>" class="btnWrite">글쓰기</a>
	</section>
          <!-- 내용 끝 -->

        </article>
    </section>

</div>
<%@ include file="../_footer.jsp" %>
