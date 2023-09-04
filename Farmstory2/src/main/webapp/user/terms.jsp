<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
        <div id="user">
            <section class="terms">
                <table border="1">
                    <caption>사이트 이용약관</caption>
                    <tr>
                        <td>
                            <textarea name="terms">${requestScope.terms.getTerms()}</textarea>
                            <label><input type="checkbox" name="chk1" class="terms">&nbsp;동의합니다.</label>
                        </td>
                    </tr>
                </table>

                <table border="1">
                    <caption>개인정보 취급방침</caption>
                    <tr>
                        <td>
                            <textarea name="privacy">${terms.privacy}</textarea>
                            <label><input type="checkbox"  name="chk2" class="privacy">&nbsp;동의합니다.</label>
                        </td>
                    </tr>
                </table>
                
                <div>
                    <a href="/Farmstory2/user/login.do" class="btn btnCancel">취소</a>
                    <a href="/Farmstory2/user/register.do" class="btn btnNext">다음</a>
                </div>

            </section>
        </div>
<%@ include file="../_footer.jsp" %>