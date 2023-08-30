	/**
	 * 사용자 중복체크
	 */
	$(function(){
		// 아이디 중복체크
		const btnCheckUid =document.getElementById('btnCheckUid');
		const inputUid = document.getElementsByName('uid')[0];
		const uidResult = document.getElementsByClassName('uidResult')[0];
		
		if(btnCheckUid != null) {
			btnCheckUid.onclick = function(){
				const uid = inputUid.value;
				
				// 아이디 유효성 검증(중복 버튼을 눌렀을 때 서버로 보냄으로써 생기는 부하를 줄이기 위해 위치를 위로 올려준다.)
				if(!uid.match(reUid)){
					uidResult.innerText = '유효한 아이디가 아닙니다.';
					uidResult.style.color = 'red';
					isUidOk = false;
					return;
				}
				
				// 서버 전송
				const xhr = new XMLHttpRequest();
				xhr.open('GET', '/Jboard2/user/checkUid.do?uid='+uid);
				xhr.send();
				
				xhr.onreadystatechange = function(){
					if(xhr.readyState == XMLHttpRequest.DONE){
						if(xhr.status == 200) {
							const data = JSON.parse(xhr.response);
							if(data.result > 0){
								uidResult.innerText = '이미 사용중인 아이디 입니다.';
								uidResult.style.color = 'red';
								isUidOk = false;
							}else{
								uidResult.innerText = '사용가능한 아이디 입니다.';
								uidResult.style.color = 'green';
								isUidOk = true;
							}
						}	
					}// readyState end
				}//onreadystatechange end 
			}// btnCheckUidonclick end
		}
		
		
		// 닉네임 중복체크
		$('#btnCheckNick').click(function(){
			const nick = $('input[name=nick]').val();
			
			// 서버로 전송하기 전에 별명 유효성 검사
			if(!nick.match(reNick)){
				$('.nickResult').css('color', 'red').text('유효한 별명이 아닙니다.');
				isNickOk = false;
				return;
			}
			
			$.ajax({
				url:'/Jboard2/user/checkNick.do?nick'+nick,
				type:'get',
				dataType:'json',
				success: function(data){
					if(data.result > 0) {
						$('.nickResult').css('color', 'red').text('이미 사용중인 별명입니다.');
						isNickOk = false;
					}else{
						$('.nickResult').css('color', 'green').text('사용가능한 별명입니다.');
						isNickOk = true;
					}
				}
			});
		}); // btnCheckNick click end 
		
		// 휴대폰 중복체크
		$('input[name=hp]').focusout(function() {
			const hp = $(this).val();
			
			if(!hp.match(reHp)) {
				$('.hpResult').css('color', 'red').text('휴대폰 번호가 유효하지 않습니다.');
				isHpOk = false;
				return;
			}
			const url = '/Jboard2/user/checkHp.do?hp='+hp;
			$.get(url, function(result){
				const data = JSON.parse(result);
				
				if(data.result > 0) {
					$('.hpResult').css('color', 'red').text('이미 사용중인 휴대폰 번호입니다.');
					isHpOk = false;
				}else{
					$('.hpResult').css('color', 'green').text('사용가능한 휴대폰 번호입니다.');
					isHpOk = true;
				}
			});
			
		});
	}); // onload end
	