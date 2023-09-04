<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>
	$(function(){
		
		$('.btnNext').click(function(){
			
			const chk1 = $('input[name=chk1]').is(':checked');
			const chk2 = $('input[name=chk2]').is(':checked');
			
			if(!chk1){
				alert('사이트 이용약관에 동의체크 하시기 바랍니다.');
				return false;
			}
			
			if(!chk2){
				alert('개인정보 취급방침 약관에 동의체크 하시기 바랍니다.');
				return false;
			}	
			
			return true;
		});
	});

</script>

<div id="user">
    <section class="userTerms">
        <table border="1">
            <caption>사이트 이용약관</caption>
            <tr>
                <td>
                    <textarea name="terms">${terms.getTerms()}</textarea>
                    <label><input type="checkbox" name="chk1" class="terms">&nbsp;동의합니다.</label>
                </td>
            </tr>
        </table>
        <table border="1">
            <caption>개인정보 취급방침</caption>
            <tr>
                <td>
                    <textarea name="privacy">${terms.getPrivacy()}</textarea>
                    <label><input type="checkbox"  name="chk2" class="privacy">&nbsp;동의합니다.</label>
                </td>
            </tr>
        </table>
        
        <div>
            <!-- 
				ctxPath
				 - Context Root 경로 전역변수 
				 - _header.jsp 5줄 참고
			-->
            <a href="${ctxPath}/user/login.do" class="btnCancel">취소</a>
            <a href="${ctxPath}/user/register.do" class="btnNext">다음</a>
        </div>
        </div>

    </section>
</div>
<%@ include file="../_footer.jsp" %>