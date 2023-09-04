<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>
	window.onload = function(){
		const chk1 = document.getElementsByName('chk1')[0];
		const chk2 = document.getElementsByName('chk2')[0];
		const btnNext = document.getElementsByClassName('btnNext')[0];
		
		btnNext.addEventListener('click', function(e){
			e.preventDefault();
			
			
			if(!chk1.checked) {
				alert('이용약관에 동의하셔야 합니다.');
				return;
			}else if(!chk2.checked) {
				alert('개인정보 취급방침에 동의하셔야 합니다.');
				return;
			}else{
				location.href='/Farmstory2/user/register.do';
			}
		});
		
	}
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
            <a href="/Farmstory2/user/login.do" class="btn btnCancel">취소</a>
            <a href="#" class="btn btnNext">다음</a>
        </div>

    </section>
</div>
<%@ include file="../_footer.jsp" %>