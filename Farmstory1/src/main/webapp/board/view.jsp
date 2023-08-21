<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%

	request.setCharacterEncoding("UTF-8");

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	
	//로그인 여부 확인
		if(sessUser == null){
			response.sendRedirect("/Farmstory1/user/login.jsp?success=101&target=view&group="+group+"&cate="+cate+"&no="+no);
		return;
		}
	
	ArticleDAO dao = new ArticleDAO();
	
	// 원글 조회
	ArticleDTO dto  = dao.selectArticle(no);
	// 댓글 조회
	List<ArticleDTO> comments = dao.selectComments(no);
	
	pageContext.include("./_aside"+group+".jsp");
		
%>
<script>
	$(function(){
		let comment=""; // 댓글에 있는 content를 담아줄 변수 선언
		
		// 댓글 수정 .mod= 수정 링크 클래스
		$('.mod').click(function(e){
			e.preventDefault(); // 링크의 기능 삭제
			const txt = $(this).text(); // txt에 링크에 적힌 텍스트 저장
			
			if(txt == '수정') {
				comment =$(this).parent().prev().val();
				// a태그의 부모태그(div)의 앞의 태그(textarea)의 값을 comment 변수에 저장
				// 즉 수정 전 댓글을 저장
				
				// 수정모드 전환(수정버튼을 클릭하면 텍스트가 수정완료로 바뀌어야함)
				$(this).parent().prev().addClass('modi'); // textarea에 클래스 추가
				$(this).parent().prev().attr('readonly', false); // textarea에 readonly 해제
				$(this).parent().prev().focus(); // textarea에 커서 주기
				$(this).text('수정완료'); // a 텍스트 '수정'에서 수정완료로 바꿔줌
				$(this).prev().show(); // 취소 링크 보이게 만들어주기
				
			}else{ // '수정'이 아닐때, 즉 수정환료 버튼이 나와있을 떄				
				// 수정완료 클릭 시
				// confirm 함수 사용 시 if 사용해 true(확인버튼) false(취소) 선택하는 확인 메세지 창을 띄운다
				
				
				
				if(confirm('정말 수정하시겠습니까?')) {
					// 수정 데이터 전송
					// $(this).closest('fom').submit(); 
					//=> this 태그의 위에 있는 태그 중 가장 가까운 form 태그를 찾아서 전송(submit)
					$(this).parent().parent().submit(); // a 태그의 부모태그(div)의 부모태그(form)를 전송
				
				}else{
					// 취소를 눌렀을 때 comment 값을 textarea에 넣어줌 => 수정 전 댓글로 돌아가게 함
					$(this).parent().prev().val(comment);	
					// 이런 식으로 취소 링크 눌렀을 때도 똑같이 만들어줄 수 있음 
					// 근데 지금은 취소링크를 눌렸을 때 view.jsp로 가게끔 만들어놨음 
				
				}
				
				// 수정모드 해제 => '수정완료' 링크를 '수정'으로 바꿔주고 '취소'는 사라지게
				$(this).parent().prev().removeClass('modi'); // textarea 클래스 삭제
				$(this).parent().prev().attr('readonly', true); // readonly 기능 활성화
				$(this).text('수정'); // '수정완료'를 '수정'으로 바꿈
				$(this).prev().hide(); // 취소 안 보이게 만듦
				
			}
		}); // 댓글 수정 끝
		
		// 댓글 삭제
		$('.del').click(function(){
			const result = confirm('정말 삭제하시겠습니까?');
			
			if(result) {
				return true;
			}else{
				return false;
			}
		}); // 댓글 삭제 끝
		
		// 댓글쓰기 취소
		const commentContent = document.querySelector('form > textarea[name=content]');
		const btnCancel = document.querySelector('.btnCancel');
		btnCancel.onclick = function(e){
			e.preventDefault();
			commentContent.value = '';
			
		} // 댓글쓰기 취소 끝
		
		// jQuery 방식
		/* $('.btnCanel').click(function(e) {
			e.preventDefault();
			$('form > textarea[name=content]').val('');
		}) ; */
		
		// 원글 삭제
		const btnDelete = document.getElementsByClassName('btnDelete')[0];
		btnDelete.onclick = function(){
			if(confirm('정말 삭제 하시겠습니까?')){
				return true;
			}else{
				return false;
			}
		}
		
	}); // 제이쿼리 문서불러오기 끝
	
	
</script>

	<section class="view">
		<h3>글보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="<%= dto.getTitle() %>" readonly /></td>
			</tr>
			<%if (dto.getFile() > 0) { %>
			<tr>
				<td>첨부파일</td>
				<td><a href="#">2020년 상반기 매출자료.xls</a> <span>7회 다운로드</span></td>
			</tr>
			<% } %>
			<tr>
				<td>내용</td>
				<td><textarea name="content" readonly><%= dto.getContent() %></textarea></td>
			</tr>
		</table>
		
		<div>
			<%if(sessUser.getUid().equals(dto.getWriter())) { %>
			<a href="./delete.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= no %>" class="btnDelete">삭제</a> 
			<a href="./modify.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= no %>&pg=<%= pg %>" class="btnModify">수정</a>
			<% } %>
			<a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pg %>" class="btnList">목록</a>
		</div>
		

		<!-- 댓글리스트 -->
		<section class="commentList">
			<h3>댓글목록</h3>
			<% for(ArticleDTO comment : comments) { %>
			<article class="comment">
				<form action="/Farmstory1/board/proc/commentUpdate.jsp" method="post">
					<input type="hidden" name="group" value="<%= group %>">
					<input type="hidden" name="cate" value="<%= cate %>">
					<input type="hidden" name="no" value="<%= comment.getNo() %>">
					<input type="hidden" name="parent" value="<%= comment.getParent() %>">
					<input type="hidden" name="pg" value="<%= pg %>">
					<span> 
						<span><%= comment.getNick() %></span>
						<span><%= comment.getRdate() %></span>
					</span>
					<textarea name="comment" readonly><%= comment.getContent() %></textarea>
					
					<% if(sessUser.getUid().equals(comment.getWriter())) {%>
					<div>
							<a href="/Farmstory1/board/proc/commentDelete.jsp
											?group=<%= group%>
											&cate=<%= cate %>
											&no=<%= comment.getNo() %>
											&parent=<%= comment.getParent() %>
											&pg=<%= pg %>"class="del">삭제</a> 
											
							<a href="/Farmstory1/board/view.jsp?group=<%= group %>&cate=<%= cate %>&no=<%=no %>&pg=<%= pg %>"  class="can">취소</a>
							<a href="#"  class="mod">수정</a>
					</div>
					<% } %>
				</form>
				</article>
			<% } %>
				
				<% if(comments.isEmpty()){ %>
		        <p class="empty">등록된 댓글이 없습니다.</p>
		        <% } %>
	
		</section>

		<!-- 댓글입력폼 -->
		<section class="commentForm">
			<h3>댓글쓰기</h3>
			<form action="./proc/commentInsert.jsp" method="post">
				<input type="hidden" name="pg" value="<%= pg %>"/>
				<input type="hidden" name="group" value="<%= group %>"/>
				<input type="hidden" name="cate" value="<%= cate %>"/>
				<input type="hidden" name="parent" value="<%= no %>"/>
				<input type="hidden" name="writer" value="<%= sessUser.getUid() %>"/>
				<textarea name="content"></textarea>
				<div>
					<a href="#" class="btnCancel">취소</a> 
					<input type="submit" class="btnWrite" value="작성완료" />
				</div>
			</form>
		</section>

	</section>
	<!-- 내용 끝 -->

    </article>
 </section>

</div>
<%@ include file="../_footer.jsp" %>