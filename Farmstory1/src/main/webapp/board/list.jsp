<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
	
	ArticleDAO dao = new ArticleDAO();
	
	// 페이지 관련 변수 선언
	int start = 0; // LIMIT 쿼리 인덱스 번호
	int currentPage = 1; // 현재 페이지번호
	int total = 0; // 글 전체 개수 select count(*) from `article`where parent=0;
	int lastPageNum = 0; // 마지막 페이지 번호
	int pageGroupCurrent = 1; // 10페이지씩 보이게 할 때 현재 페이지 그룹 번호(1~10페이지는 1번 그룹, 11~20페이지는 2번 그룹)
	int pageGroupStart = 1; // 10페이지씩 보이게 할 때 페이지 그룹 시작페이지
	int pageGroupEnd = 0; // 10페이지씩 보이게 할 때 페이지 그룹 마지막페이지(10단위로 나눠짐. 나중에 lastPageNum와 함께 마지막 페이지 수 결정)
	int pageStartNum =0; // 페이지 당 시작하는 글 번호 => 위가 시작, 아래로갈수록 줄어든다. 
	
	// 현재 페이지 계산(처음 list.jsp를 클릭하면 pg파라미터를 받을 수 없기 때문에 currentPage는 1로 초기화되어 아래 계산식에 사용된다.)
	
	if(pg != null) { 
		currentPage = Integer.parseInt(pg); // pg 파라미터를 String으로 받기 때문에 int로 변환 
	}
	
	// Limit 시작값 계산(글을 10개씩 보여줄거니까 10으로 나눠줌 20개로 보여주고 싶으면 20으로 나누면 됨)
	start = (currentPage - 1)*10; 
	
	// 전체 게시물 개수 조회
	total = dao.selectCountTotal(cate);
	
	// 페이지 번호 계산
	if(total % 10 == 0) {
		lastPageNum = (total / 10);
	}else {
		lastPageNum = (total / 10) +1;
	}
	
	// 
	
	List<ArticleDTO> articles = dao.selectArticles(cate, start);
	
	pageContext.include("./_aside"+group+".jsp");
	
	
	
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
				<% for(ArticleDTO article : articles) { %>
					<tr>
						<td><%= article.getNo() %></td>
						<td><a href="/Farmstory1/board/view.jsp?group=<%= group %>&cate=<%= cate %>"><%= article.getTitle() %></a>&nbsp;[<%=article.getComment() %>]</td>
						<td><%=article.getNick() %></td>
						<td><%=article.getRdate() %></td>
						<td><%=article.getHit() %></td>
					</tr>
					<% } %>
			</table>
		</article>
	
		<!-- 페이지 네비게이션 -->
		<div class="paging">
			
			<a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= %>" class="prev">이전</a>
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
