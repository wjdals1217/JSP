<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	$(function() {
		let comment = '';
		// 댓글 수정
		$('.mod').click(function(e){
			e.preventDefault();
			const txt = $(this).text();
			
			if(txt == '수정'){
				comment = $(this).parent().prev().val();
				
				// 수정모드 전환
				$(this).parent().prev().addClass('modi');
				$(this).parent().prev().attr('readonly', false);
				$(this).parent().prev().focus();
				$(this).text('수정완료');
				$(this).prev().show();
				
			}else{
				// 수정 완료 클릭
				if(confirm('정말 수정하시겠습니까?')){
					// 수정 데이터 전송
					/* $(this).closest('form').submit(); */
					$(this).parent().parent().submit(); 
				}else{
					$(this).parent().prev().val(comment);
				}
				
				// 수정모드 해제
				$(this).parent().prev().removeClass('modi');
				$(this).parent().prev().attr('readonly', true);
				$(this).text('수정');
				$(this).prev().hide();
			}
		});
		
		// 댓글 수정 취소
		/* $('.can').click(function(e){
			e.preventDefault();
			// 수정모드 해제
			$(this).parent().prev().removeClass('modi');
			$(this).parent().prev().attr('readonly', true);
			$(this).hide();
			$(this).next().text('수정');
		}); */
		
		// 댓글 삭제
		$('.del').click(function(){
			const result = confirm('정말 삭제 하시겠습니까?');
			
			if(result){
				return true;
			}else{
				return false;
			}
		});
		
		// 댓글쓰기 취소
 		const commentContent = document.querySelector('form > textarea[name=content]');
		const btnCancel = document.querySelector('.btnCancel');
		btnCancel.onclick = function(e){
			e.preventDefault();
			commentContent.value = '';
		} 
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
	});


</script>

	<section class="view">
		<h3>글보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="제목" readonly /></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><a href="#">2020년 상반기 매출자료.xls</a> <span>7회 다운로드</span></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" readonly>내용</textarea></td>
			</tr>
		</table>
		<div>
			<a href="#" class="btnDelete">삭제</a> 
			<a href="#" class="btnModify">수정</a>
			<a href="/Jboard1/list.jsp" class="btnList">목록</a>
		</div>

		<!-- 댓글리스트 -->
		<section class="commentList">
			<h3>댓글목록</h3>
			<article class="comment">
				<form action="/Jboard1/proc/commentUpdate.jsp" method="post">
					<input type="hidden" name="no" value="번호">
					<input type="hidden" name="parent" value="원글">
					<span> 
						<span>별명</span>
						<span>날짜</span>
					</span>
					<textarea name="comment" readonly>댓글내용</textarea>
					<div>
							<a href="#"class="del">삭제</a> 
							<a href="#"  class="can">취소</a>
							<a href="#"  class="mod">수정</a>
					</div>
				</form>
				</article>
				<p class="empty">등록된 댓글이 없습니다.</p>
	
		</section>

		<!-- 댓글입력폼 -->
		<section class="commentForm">
			<h3>댓글쓰기</h3>
			<form action="/Jboard1/proc/commentProc.jsp" method="post">
				<input type="hidden" name="parent" value="원글"/>
				<input type="hidden" name="writer" value="아이디"/>
				<textarea name="content"></textarea>
				<div>
					<a href="#" class="btnCancel">취소</a> 
					<input type="submit" class="btnWrite" value="작성완료" />
				</div>
			</form>
		</section>

	</section>
