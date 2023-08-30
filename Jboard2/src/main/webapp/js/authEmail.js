/**
 * 이메일 인증
 */
$(function(){

	let preventDoubleClick = false;
	
	$('#btnEmailCode').click(function(){
		
		const type = $('input[name=type]').val();
		const name = $('input[name=name]').val();
		const uid = $('input[name=uid]').val();
		const email = $('input[name=email]').val();
		
		const jsonData = {
									"type" : type,
									"name": name, 
									"uid": uid, 
									"email":email
									};
		
		if(preventDoubleClick){
			return;
		}
		preventDoubleClick = true;
		$('.resultEmail').css('color', 'black').text('잠시만 기다리세요...');
		$('.resultEmailForId').css('color', 'black').text('잠시만 기다리세요...');
		$('.resultEmailForPass').css('color', 'black').text('잠시만 기다리세요...');
		
		setTimeout(function(){
			$.ajax({
				url:'/Jboard2/user/authEmail.do',
				type:'GET',
				data: jsonData,
				dataType:'json',
				success:function(data){
					if(data.result > 0) {
						$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일입니다.');
						isEmaileOk =false;
						
						if(data.status > 0){
							$('.resultEmailForId').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForPass').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('input[name=auth]').prop('disabled', false);
						}else{
							$('.resultEmailForId').css('color', 'red').text('이메일 인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.');
							$('.resultEmailForPass').css('color', 'red').text('이메일 인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.');
							
						}
						
					}else {
						if(data.status > 0){
							$('.resultEmail').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.auth').show();
							$('input[name=email]').attr('readonly', true);
						}else{
							$('.resultEmail').css('color', 'red').text('이메일 인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.');
							$('.resultEmailForId').css('color', 'red').text('해당하는 사용자, 이메일이 일치하지 않습니다.');
							$('.resultEmailForPass').css('color', 'red').text('해당하는 아이디, 이메일이 일치하지 않습니다.');
						}
					}
					preventDoubleClick = false;						
				}
			});
		}, 1000);
	});
	$('#btnEmailAuth').click(function(){
		const code = $('input[name=auth]').val();
		const jsonData = {"code" : code};
		$.ajax({
			url:'/Jboard2/user/authEmail.do',
			type:'POST',
			data:jsonData,
			dataType:'json',
			success: function(data){
				if(data.result > 0) {
					$('.resultEmail').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					$('.resultEmailForId').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					$('.resultEmailForPass').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					isEmailOk = true;
				}else{
					$('.resultEmail').css('color', 'red').text('이메일 인증에 실패했습니다.');
					$('.resultEmailForId').css('color', 'red').text('이메일 인증에 실패했습니다.');
					$('.resultEmailForPass').css('color', 'red').text('이메일 인증에 실패했습니다.');
					isEmailOk = false;
				}
			}
		});
	});
});