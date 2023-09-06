<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
        <main id="user">
            <section class="find findPassChange">
                <form id="formFindPassChange" action="${ctxPath}/user/findPassChange.do" method="post">
                	<input type="hidden" name="uid" value="${sessionScope.uid}"/>
                    <table border="0">
                        <caption>비밀번호 변경</caption>                        
                        <tr>
                            <td>아이디</td>
                            <td>${uid}</td>
                        </tr>
                        <tr>
                            <td>새 비밀번호</td>
                            <td>
                                <input type="password" name="pass1" placeholder="새 비밀번호 입력"/>
                            </td>
                        </tr>
                        <tr>
                            <td>새 비밀번호 확인</td>
                            <td>
                                <input type="password" name="pass2" placeholder="새 비밀번호 확인"/>
                                <span class="passResult"></span>
                            </td>
                        </tr>
                    </table>                                        
                </form>
                
                <p>
                    비밀번호를 변경해 주세요.<br>
                    영문, 숫자, 특수문자를 사용하여 8자 이상 입력해 주세요.                    
                </p>

                <div class="btns">
                    <a href="${ctxPath}/user/login.do" class="btn btnCancel">취소</a>
                    <a href="#" id="btnPassChange" class="btn btnNext">완료</a>
                </div>
            </section>
        </main>
<%@ include file="../_footer.jsp" %>