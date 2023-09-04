	// 사용자 중복 체크
	$(function(){
		//아이디 중복체크
		// 아이디 유효성 검사
		$('#btnCheckUid').click(function(){
			const uid = $('input[name=uid]').val();
			
			if(!uid.match(reUid)){
				$('.uidResult').css('color', 'red').text('유효한 아이디가 아닙니다.');
				isUidOk = false;
				return; // 종료
			}
			
			const jsonData = {
					"uid":uid
			};
			
			$.ajax({
				url:'/Farmstory2/user/checkUid.do',
				type: 'GET',
				data: jsonData,
				dataType: 'json',
				success:function(data){
					console.log(data);
					if(data.result >=1){
						$('.uidResult').css('color', 'red').text('이미 사용중인 아이디입니다.');
						isUidOk =false;
					}else{
						$('.uidResult').css('color', 'green').text('사용 가능한 아이디입니다.');
						isUidOk = true; // 왜 여기선 return 안해주는지
					}
				}
			});
		});// 아이디 중복체크 및 아이디 유효성 검사 끝
		
		// 닉네임 중복체크
		$('input[name=nick]').focusout(function(){
			// 입력 데이터 가져오기
			const nic = $(this).val();
			
			// 유효성 검사
			if(!nick.match(reNick)){
				$('.nickResult').css('color', 'red').text('유효한 닉네임이 아닙니다.');
				isNickOk = false;
				return;
			}
			// JSON 생성
			const jsonData ={
				"nick":nick	
			};
			
			// 데이터 전송
			$.get('/Farmstory2/user/checkNick.do', jsonData, function(){
				
				if(data.result >=1){
					$('.nickResult').css('color', 'red').text('이미 사용중인 별명입니다.');
					isNickOk = false;
				}else{
					$('.nickResult').css('color', 'green').text('사용 가능한 별명입니다.');
					isNickOk = true;
				}
			});
		});
		// 닉네임 중복체크 및 닉네임 유효성 끝
		
		// 이메일 중복체크
		document.getElementsByName('email')[0].onfocusout = function(){
		
		const resultEmail = document.getElementById('resultEmail');
		
		// 입력 데이터 가져오기
		const email = this.value;
		
		if(!email.match(reEmail)){
			resultEmail.innerText = '유효한 이메일이 아닙니다.';
			resultEmail.style.color = 'red';
			isEmailOk = false;
			return;
		}

		// 데이터 전송
		const xhr = new XMLHttpRequest();
		xhr.open('GET', '/Farmstory2/user/checkEmail.do?email='+email);
		xhr.send();
		
		// 응답 결과
		xhr.onreadystatechange = function(){    				
			if(xhr.readyState == XMLHttpRequest.DONE){						
				if(xhr.status == 200){
					const data = JSON.parse(xhr.response);
					console.log('data : ' + data);
					
					if(data.result >= 1){
						resultEmail.innerText = '이미 사용중인 이메일 입니다.';
						resultEmail.style.color = 'red';
						isEmailOk = false;
					}else{
						resultEmail.innerText = '사용 가능한 이메일 입니다.';
						resultEmail.style.color = 'green';
						isEmailOk = true;
					}
				}
			}    				
		}// onreadystatechange end
	} // 이메일 중복체크 끝
	
	// 휴대폰 중복체크
	document.getElementsByName('hp')[0].addEventListener('focusout', function(){
		
		const hpResult = document.getElementById('hpResult');
		const hp = this.value;
		
		if(!hp.match(reHp)){
			hpResult.innerText = '유효한 휴대폰번호가 아닙니다.';
			hpResult.style.color = 'red';
			isHpOk = false;
			return;
		}
		
		const url = '/Farmstory2/user/checkHp.do?hp='+this.value;
		
		fetch(url)
			.then(response => response.json())
			.then(data => {
				console.log(data);
				
				if(data.result >= 1){
					hpResult.innerText = '이미 사용중인 휴대폰번호 입니다.';
					hpResult.style.color = 'red';
					isHpOk = false;
				}else{
					hpResult.innerText = '사용 가능한 휴대폰번호 입니다.';
					hpResult.style.color = 'green';
					isHpOk = true;
				}
			});
		
	}); // 휴대폰 중복체크 끝
	}); // 사용자 개인정보 중복체크 끝
	