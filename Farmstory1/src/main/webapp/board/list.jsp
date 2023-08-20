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
	int total = 0; // 글 전체 개수 select count(*) from `article`where parent=0 and `cate`= ?;
	int lastPageNum = 0; // 마지막 페이지 번호
	int pageGroupCurrent = 1; // 10페이지씩 보이게 할 때 현재 페이지의 그룹 번호(1~10페이지는 1번 그룹, 11~20페이지는 2번 그룹)
	int pageGroupStart = 1; // 10페이지씩 보이게 할 때 페이지 그룹 시작페이지
	int pageGroupEnd = 0; // 10페이지씩 보이게 할 때 페이지 그룹 마지막페이지(10단위로 나눠짐. 나중에 lastPageNum와 함께 마지막 페이지 수 결정)
	int pageStartNum =0; // 페이지 당 시작하는 글 번호 => 위가 시작, 아래로갈수록 줄어든다. 
	
	// 현재 페이지 계산(처음 list.jsp를 클릭하면 pg파라미터를 받을 수 없기 때문에 currentPage는 1로 초기화되어 아래 계산식에 사용된다.)
	
	if(pg != null) { 
		currentPage = Integer.parseInt(pg); // pg 파라미터를 String으로 받기 때문에 int로 변환 
	}
	
	// Limit 시작값 계산(글을 10개씩 보여줄거니까 10으로 곱해줌 20개로 보여주고 싶으면 20으로 곱해주면 됨)
	start = (currentPage - 1)*10; 
	
	// 전체 게시물 개수 조회
	total = dao.selectCountTotal(cate);
	
	// 페이지 끝번호 계산
	if(total % 10 == 0) {
		lastPageNum = (total / 10);
	}else {
		lastPageNum = (total / 10) +1;
	}
	
	// 페이지 그룹 계산(보이는 페이지 그룹임 < [1]~[10]> 이렇게 보이는 게 한 그룹)
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0); // 정수인 currentPage를 10.0으로 나누어 올림, 실수이므로 int로 다시 변환
	pageGroupStart = (pageGroupCurrent - 1) * 10 +1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	} // pageGroupEnd는 10으로 나누어 떨어지기 때문에 페이지 끝 번호가 10으로 나누어지지않으면 
	  // 내용이 없어도 페이지 번호가 있게 된다. 그래서 lastPageNum이 더 작으면 lastPageNum으로 해야 함.
	
	// 페이지 시작번호 계산(글번호에 적히는 번호임 고유번호가 아니라 글개수로 정해진다. total에서 빼주는 이유는 10개씩 보여줄때 페이지 나오는 첫 글 번호를 정해주기 위해서)
	pageStartNum = total - start;
	
	// 현재 페이지 게시물 조회(ArticleDTO 객체로 이루어진 배열인 articles)	
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
						<td><%= pageStartNum-- %></td>  <!-- 아래행으로 갈수록 번호가 하나씩 줄어든다. -->
						<td><a href="/Farmstory1/board/view.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= article.getNo() %>&pg=<%= currentPage %>">
								<%= article.getTitle() %>
								</a>&nbsp;[<%=article.getComment() %>]</td>
						<td><%=article.getNick() %></td>
						<td><%=article.getRdate() %></td>
						<td><%=article.getHit() %></td>
					</tr>
					<% } %>
			</table>
		</article>
	
		<!-- 페이지 네비게이션 -->
		<div class="paging">
			<% if(pageGroupStart > 1) {%> <!-- 조건을 1보다 크게로 잡는 이유는 페이지 번호가 1일때는 이전링크를 보여줄 필요가 없기 때문이다.-->
			<a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pageGroupStart -1 %>" class="prev">이전</a>
			<!-- 위의 링크를 타고 가면 currentPage번호가 pageGroupStart -1이 된다. 
			즉 현재 페이지가 25p라면 pageGroupStart는 21p이므로 '이전' 링크를 눌리면 20p가 currentPage가 됨 -->
			<% } %>
			
			<%for(int i=pageGroupStart ; i <= pageGroupEnd ; i++) { %> 
			<!-- i가 페이지 그룹의 시작페이지부터 페이지그룹의 마지막페이지 번호만큼 아래 링크를 반복해서 보여줌 -->
			<a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= i %>" class="num <%= (currentPage == i)?"current":""%>">
				<%= i %>
			</a>
			<!-- pg파라미터가 i로 되는 이유는 링크를 눌렀을 때 i의 페이지로 이동을 해줘야 하기때문이다.21~30의 그룹이라 했을 때 링크에는  각 번호의 pg파라미터를 송신 -->
			<!-- currentPage가 i와 같으면 class에 current를 주고 아니면 공백을 준다. current를 줌으로써 css처리를 해줌 -->
			<% } %>
			
			<% if(pageGroupEnd < lastPageNum) { %>
			<!-- 조건을 'lastPageNum보다 pageGroupEnd 작다'로 잡는 이유는 크게 되면 lastPageNum이 pageGroupEnd가 되고 다음 페이지 그룹으로 넘어갈 필요가 없기 때문 -->
			<a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pageGroupEnd + 1 %>" class="next">다음</a>
			<!-- 위의 링크를 타고 가면 currentPage번호가 pageGroupStart + 1이 된다.
			 즉 현재 페이지가 25p라면 pageGroupEnd는 30p이므로 '다음' 링크를 눌리면 31p가 currentPage가 됨 -->
			<% } %>
		</div>
		<!-- 글쓰기 버튼 -->
		<a href="/Farmstory1/board/write.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= currentPage %>" class="btnWrite">글쓰기</a>
		
	</section>
          <!-- 내용 끝 -->

        </article>
    </section>

</div>
<%@ include file="../_footer.jsp" %>
