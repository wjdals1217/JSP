<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<jsp:include page="./_aside${group}.jsp"/>
			<section class="modify">
			    <h3>글수정</h3>
			    <article>
			        <form action="${ctxPath}/board/modify.do" method="post" enctype="multipart/form-data">
			        <input type="text" name="group" value="${group}"/>
			        <input type="text" name="cate" value="${cate}"/>
			        <input type="text" name="no" value="${article.no}"/>
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
			                    	<span>${article.fileDTO.oriName}</span>
			                    	<a href="#" class="btnfileDelete">삭제</a> <!-- 삭제하거나 수정하면 span이 사라지게 해야한다. -->
			                    	<input type="file" name="fileModi"/>
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