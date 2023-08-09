$(function(){
	
	$('.del').click(function(){
		const result = confirm('정말 삭제 하시겠습니까?');
		
		if(result == true){
			return true;
		}else{
			return false;
		}
		
	});
});