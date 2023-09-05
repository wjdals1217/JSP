<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<jsp:include page="./_aside${group}.jsp"/>
<script>
	$(function(){
		// 다운로드 횟수 증가
		$('#download').click(function(e){
			const countDownload = $('#countDownload');
			let number = countDownload.text();
			number = parseInt(number)+1;
			countDownload.text(number); 
		});
	});
	
</script>
			<section class="view">
			    <h3>글보기</h3>
			    <table>
			        <tr>
			            <td>제목</td>
			            <td><input type="text" name="title" value="${article.title}" readonly/></td>
			        </tr>
			        <c:if test="${article.file > 0}">
			        	<tr>
			            	<td>첨부파일</td>
			            	<td>
			                	<a id="download" href="${ctxPath}/fileDownload.do?fno=${article.fileDTO.fno}">${article.fileDTO.oriName}</a>
			                	<span id="countDownload">${article.fileDTO.download}</span>회 다운로드
			            	</td>
			        	</tr>
			        </c:if>
			        
			        <tr>
			            <td>내용</td>
			            <td>
			                <textarea name="content" readonly>${article.content}</textarea>
			            </td>
			        </tr>
			    </table>
			    
			    <div>
			    	<c:if test="${sessUser.uid eq article.writer}">
			    		<a href="#" class="btnDelete">삭제</a>
			        	<a href="#" class="btnModify">수정</a>
			    	</c:if>
			        <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
			    </div>
			    
			    <!-- 댓글리스트 -->
			    <section class="commentList">
			        <h3>댓글목록</h3>
			        <article class="comment">
			        	<form action="#" method="post">
							<span>
								<span>닉네임</span>
								<span>23-09-04</span>
							</span>
							<textarea name="comment" readonly>댓글내용</textarea>
			             
							<div>
								<a href="#" class="del">삭제</a>
								<a href="./list.do?group=${group}&cate=${cate}" class="can">취소</a>
								<a href="./modify.do?group=${group}&cate=${cate}" class="mod">수정</a>
							</div>                
			            </form>
			        </article>
			        <p class="empty">등록된 댓글이 없습니다.</p>
			    </section>
			
			    <!-- 댓글입력폼 -->
			    <section class="commentForm">
			        <h3>댓글쓰기</h3>
			        <form action="#" method="post">
			            <textarea name="content"></textarea>
			            <div>
			                <a href="#" class="btnCancel">취소</a>
			                <input type="submit" class="btnWrite" value="작성완료"/>
			            </div>
			        </form>
			    </section>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp"%>