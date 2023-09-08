<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<jsp:include page="./_aside${group}.jsp"/>
<script>
	$(function(){
		const btnfileDelete = $('.btnfileDelete');
		const prevOriName = $('.prevOriName');
		const prevFiledelete = $('input[name=prevFiledelete]');
		const fileModi = $('.fileModi');
		
			btnfileDelete.click(function(){
				prevOriName.text('');
				$(this).hide();
				prevFiledelete.val('DELETE');
			});	
	});
	 
</script>
			<section class="modify">
			    <h3>글수정</h3>
			    <article>
			        <form action="${ctxPath}/board/modify.do" method="post" enctype="multipart/form-data">
			        <input type="hidden" name="group" value="${group}"/>
			        <input type="hidden" name="cate" value="${cate}"/>
			        <input type="hidden" name="no" value="${article.no}"/>
			        <input type="hidden" name="prevFile" value="${article.fileDTO.newName}"/>
			        <input type="hidden" name="prevFiledelete" value="remain"/>
			            <table>
			                <tr>
			                    <td>제목</td>
			                    <td><input type="text" name="title" value="${article.title}" placeholder="제목을 입력하세요."/></td>
			                </tr>
			                <tr>
			                    <td>내용</td>
			                    <td>
			                        <textarea name="content">${article.content}</textarea>
			                    </td>
			                </tr>
			                <tr>
			                    <td>첨부</td>
			                    <td>
			                    	<span class="prevOriName">${article.fileDTO.oriName}</span>
			                    	<c:if test="${article.fileDTO.oriName ne null}">
			                    	<a href="#" class="btnfileDelete">삭제</a><br> <!-- 삭제하거나 수정하면 span이 사라지게 해야한다. -->
									</c:if>
			                    	<input type="file" name="fileModi" class="fileModi"/>
			                    </td>
			                </tr>
			            </table>
			            <div>
			                <a href="./view.do?group=${group}&cate=${cate}&no=${article.no}" class="btnCancel">취소</a>
			                <input type="submit"  class="btnWrite" value="수정완료">
			            </div>
			        </form>
			    </article>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp"%>