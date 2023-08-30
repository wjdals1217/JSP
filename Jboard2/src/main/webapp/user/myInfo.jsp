<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="/Jboard2/js/validation.js"></script>
<script>
	window.onload = function(){
		
		const inputUid = document.getElementsByName('uid')[0];
		
		// 비밀번호 변경
		const btnUpdatePass =document.getElementById('btnUpdatePass');
		btnUpdatePass.addEventListener('click', function(){
			
			fetch('/Jboard2/user/myInfo.do')
					.then((response)=>response.json())
					.then((data)=>{
							
					});
			
		});
		
		// 탈퇴하기
		const btnWithdraw = document.getElementById('btnWithdraw');
		btnWithdraw.addEventListener('click', function(){
			
			const jsonData = {
					"kind":"WITHDRAW", 
					"uid":inputUid.value
			};
			
			/* console.log('jsonData : '+jsonData);
			
			fetch('/Jboard2/user/myInfo.do', {
					method: 'POST', 
					body: JSON.stringify(jsonData)
				})
			.then((response)=>response.json())
			.then((data)=>{
					console.log('data : '+data);
			}); */
			
			$.ajax({
				url: '/Jboard2/user/myInfo.do',
				type : 'POST',
				data : jsonData,
				dataType: 'json',
				success: function(data){
					console.log('data : '+data);
					if(data.result > 0){
						alert('회원탈퇴처리가 완료되었습니다. 이용해 주셔서 감사합니다.');
						location.href='/Jboard2/user/logout.do';
					}
				}
			});
		});
		
	}
</script>
        <main id="user">
            <section class="myInfo">
                <form action="#" method="post">
                	<input type="hidden" name="uid" value="${sessUser.uid}"/>
                    <table border="1">
                        <caption>회원정보 설정</caption>
                        <tr>
                            <td>아이디</td>
                            <td>${sessionScope.sessUser.uid}</td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td>
                                <input type="password" name="pass1" placeholder="비밀번호 입력"/>
                                <span class="passResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호 확인</td>
                            <td>
                                <input type="password" name="pass2" placeholder="비밀번호 입력 확인"/>
                                <button type="button" id="btnUpdatePass" class="btnUpdatePass">비밀번호 수정</button>
                            </td>
                        </tr>
                        <tr>
                            <td>회원가입날짜</td>
                            <td>${sessUser.regDate}</td>
                        </tr>
                    </table>
        
                    <table border="1">
                        <caption>개인정보 수정</caption>
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" name="name" value="${sessUser.name}"/>
                                <span class="nameResult"></span>                     
                            </td>
                        </tr>
                        <tr>
                            <td>별명</td>
                            <td>
                                <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                                <input type="text" name="nick" placeholder="별명 입력" value="${sessUser.nick}"/>
                                <button type="button" id="btnNickCheck"><img src="../img/chk_id.gif" alt="중복확인"/></button>
                                <span class="nickResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                
                                <input type="email" name="email" placeholder="이메일 입력" value="${sessUser.email}"/>
                                <span class="emailResult"></span>
                                <button type="button" id="btnEmailAuth"><img src="../img/chk_auth.gif" alt="인증번호 받기"/></button>
                                <div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력"/>
                                    <button type="button" id="btnEmailConfirm"><img src="../img/chk_confirm.gif" alt="확인"/></button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td>
                                <input type="text" name="hp" placeholder="휴대폰 입력" value="${sessUser.hp}"/>
                                <span class="hpResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <input type="text" name="zip" id="zip" readonly="readonly" placeholder="우편번호" value="${sessUser.zip}"/>
                                <button type="button"><img src="../img/chk_post.gif" alt="우편번호찾기"/></button>
                                <input type="text" name="addr1" id="addr1" placeholder="주소 검색"  value="${sessUser.addr1}"/>
                                <input type="text" name="addr2" id="addr2" placeholder="상세주소 입력"  value="${sessUser.addr2}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>회원탈퇴</td>
                            <td>
                                <button type="button" id="btnWithdraw" class="btnWithdraw">탈퇴하기</button>
                            </td>
                        </tr>
                    </table>
        
                    <div>
                        <a href="/Jboard2/user/login.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="회원수정" class="btn btnRegister"/>
                    </div>
        
                </form>
        
            </section>
        </main>
<%@ include file="./_footer.jsp" %>