<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./_header.jsp" %>
<script>
	$(function(){
		
		let comment = '';
		
		// 댓글 수정
		$('.modify').click(function(e){
			e.preventDefault();
			const txt = $(this).text();
			
			if(txt == '수정'){
				comment = $(this).text();
				
				// 수정모드 전환
				$(this).parent().prev().addClass('modi');
				$(this).parent().prev().attr('readonly', false);
				$(this).parent().prev().focus();
				$(this).text('수정완료');
				$(this).prev().show();
			}else{
				// 수정완료 클릭
				if(confirm('정말 수정하시겠습니까?')) {
					// 수정 데이터 전송
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
		
		// 댓글 삭제(동적 생성 이벤트 구현)(.on(이벤트, 선택자, 함수))
		$(document).on( 'click', '.remove', function(e){
			e.preventDefault();
			
			if(confirm('정말 삭제하시겠습니까?')){
				//alert('클릭!');
				const no = $(this).data('no');
				const article = $(this).parent().parent();
				const parent = $(this).data('parent');
				console.log('no : '+no);
				
				const jsonData ={
						"kind":"REMOVE",
						"no":no,
						"parent":parent
				};
				console.log("jsonData"+jsonData);
				
				$.ajax({
					url : '/Jboard2/comment.do',
					type:'GET',
					data : jsonData,
					dataType: 'json',
					success:function(data){
						
						if(data.result > 0) {
							alert('댓글이 삭제 되었습니다.');
							
							// 화면 처리
							article.remove();
						}
					}
				});
			} // 확인버튼을 눌렀을 때
		});
		
		//댓글쓰기
		$('#btnComment').click(function(e){
			e.preventDefault();
			
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]');
			const writer = $('#formComment > input[name=writer]').val();
			const empty = $('.empty');			
			const nick = "${sessUser.nick}";
			const uid = "${sessUser.uid}";
			
			const jsonData = {
					"parent":parent,
					"content":content.val(),
					"writer":writer
			};
			console.log('jsonData : '+jsonData);
			
			
			$.ajax({
				url: '/Jboard2/comment.do', 
				type : 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log(data);
					
					if(data.result > 0){
						alert('댓글이 등록되었습니다.');
						
						// 동적 화면 생성
						const dt = new Date();
						const year = dt.getFullYear().toString().substr(2, 4);
						const month = dt.getMonth()+1;
						const date = dt.getDate();
						const now = year +"-"+month+"-"+date;

						
						let article = "<article>";
								 article += "<span class='nick'>"+nick+"</span>"; 								
								 article += "<span class='date'>"+now+"</span>";
								 article += "<p class='content'>"+content.val()+"</p>";
								 article += "<div>";
								 article += "<a href='#' class='remove'>삭제</a>";
								 article += "<a href='#' class='modify'>수정</a>";
								 article += "</div>";
								article += "</article>";
												
						$('.commentList').append(article);
						content.val('');
						empty.remove();
					}else{
						alert('댓글 등록이 실패했습니다.');
					}
				}
			});
			
		});
		
	});
</script>
        <main id="board">
            <section class="view">
                
                <table border="0">
                    <caption>글보기</caption>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" name="title" value="${article.title}" readonly/></td>
                    </tr>
                    <c:if test="${article.file > 0}">
		            <tr>
		                <th>파일</th>
		                <td><a href="/Jboard2/fileDownload.do?fno=${article.fileDTO.fno}">${article.fileDTO.oriName}</a>&nbsp;
		                <span>${article.fileDTO.download}</span>회 다운로드</td>
		            </tr>
		            </c:if>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="content" readonly>${article.content}</textarea>
                        </td>
                    </tr>                    
                </table>
                <div>
                <c:if test="${sessUser.uid eq article.writer}">
                	<a href="/Jboard2/delete.do?no=${article.no}" class="btn btnRemove">삭제</a>
                    <a href="./modify.do" class="btn btnModify">수정</a>
                </c:if>
                    <a href="./list.do" class="btn btnList">목록</a>
                </div>

                <!-- 댓글목록 -->
                <section class="commentList">
                    <h3>댓글목록</h3>                   

                    <c:forEach var="comment" items="${comments}">
			            <article>
			            <form action="/Jboard2/comment.do">
			                <span class="nick">${comment.nick}</span>
			                <span class="date">${comment.rdate}</span>
			                <textarea class="content" readonly>${comment.content}</textarea> 
							<c:if test="${sessUser.uid eq comment.writer }">
			                <div>			                
			                	<a href="#" class="remove" data-no ="${comment.no}" data-parent="${comment.parent}">삭제</a> <!-- data- : 사용자 정의 속성 -->
			                    <a href="#" class="can">취소</a>
			                    <a href="#" class="modify">수정</a>
			                </div>
			                </c:if>
			                </form>
			            </article>
					</c:forEach>
					<c:if test="${comments.size() == 0}">
		            	<p class="empty">등록된 댓글이 없습니다.</p>
					</c:if>
					
                </section>

                <!-- 댓글쓰기 -->
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
		            <form id="formComment" action="#" method="post">
		            	<input type="hidden" name="parent" value="${no}"/>
		            	<input type="hidden" name="writer" value="${sessUser.uid}"/>
		                <textarea name="content"></textarea>
		                <div>
		                    <a href="#" class="btn btnCancel">취소</a>
		                    <input type="submit" id="btnComment" class="btn btnComplete" value="작성완료"/>
		                </div>
		            </form>
                </section>

            </section>
        </main>
<%@ include file="./_footer.jsp" %>