// 사용자 개인정보 중복체크
    	$(function(){
    		// 아이디 중복체크
    		$('#btnCheckUid').click(function(){
    			
    			
    			
    			const uid = $('input[name=uid]').val();
    			
    			if(uid == ''){
    				alert('아이디를 입력하세요.');
    				return;
    			}
    			
    			
    			const jsonData = {
    					"uid":uid
    			};
    		
    			$.ajax({
    				url:'/Jboard1/user/checkUid.jsp',
    				type:'GET',
    				data:jsonData,
    				dataType:'json',
    				success:function(data){
    					if(data.result >=1){
    						$('.resultId').css('color', 'red').text('이미 사용중인 아이디 입니다.');	
    					}else{
    						$('.resultId').css('color', 'green').text('사용할 수 있는 아이디 입니다.');
    					}
    				}
    			});
    		});// 아이디 중복체크
    		
    		// 닉네임 중복 체크
    		$('input[name=nick]').focusout(function(){
    			
    			// 입력 데이터 가져오기
    			const nick = $(this).val();
    			
    			//console.log('nick : '+nick);
    			
    			if(nick==''){
    				alert('별명을 입력하세요');
    				return;
    			}
    			const jsonData = {
    					"nick":nick
    			};
    			
    			$.get('/Jboard1/user/checkNick.jsp', jsonData, function(data){
    				if(data.result >=1){
    					$('.resultNick').css('color', 'red').text('이미 사용 중인 별명입니다.');
    				}else{
    					$('.resultNick').css('color', 'green').text('사용할 수 있는 별명입니다.');
    				}
    			});
    			
    		}); // 닉네임 중복체크
    		
    		// 이메일 중복 체크
    		const email = document.getElementsByName('email')[0];
    		email.onfocusout = function(){
    			
    			// 입력 데이터 가져오기
    			const email = this.value;
    			console.log('email : '+email);
    			
    			const xhr = new XMLHttpRequest();
    			xhr.open('GET', '/Jboard1/user/checkEmail.jsp?email='+email);
    			xhr.send();
    			xhr.onreadystatechange = function() {
    				if(xhr.readyState == XMLHttpRequest.DONE){
    					if(xhr.status ==200){
    						const data = JSON.parse(xhr.response);
    						console.log('data : '+data);
    						
    						const resultEmail = document.getElementsByClassName('resultEmail')[0];
    						
    						if(data.result >=1){
    							//alert('이미 사용중인 이메일 입니다.');
    							resultEmail.innerText = '이미 사용중인 이메일 입니다.';
    							resultEmail.style.color = 'red';
    						}else{
    							//alert('사용 가능한 이메일 입니다.');
    							resultEmail.innerText = '사용가능한 이메일 입니다.';
    							resultEmail.style.color = 'green';
    							
    						}
    					}
    				}
    			}// onreadystatechange end
    		};// email.onfocusout end
    		
    		// 휴대폰 중복체크
    		document.getElementsByName('hp')[0].addEventListener('focusout', function(){
    			
    			const url = '/Jboard1/user/checkHp.jsp?hp='+this.value;
    			fetch(url)
    						.then(response => response.json())
    						.then(data => {
    							console.log(data);
    							const resultHp = document.getElementsByClassName('resultHp')[0];
    							
    							if(data.result >=1){
        							//alert('이미 사용중인 이메일 입니다.');
        							resultHp.innerText = '이미 사용중인 휴대폰 번호 입니다.';
        							resultHp.style.color = 'red';
        						}else{
        							//alert('사용 가능한 이메일 입니다.');
        							resultHp.innerText = '사용가능한 휴대폰번호 입니다.';
        							resultHp.style.color = 'green';
        							
        						}
    						});
    			
    		});// 휴대폰 중복체크
    		
    	});