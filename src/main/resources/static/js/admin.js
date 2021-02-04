/*contentType:"application/json; charset=utf-8",
			dataType: "json"	// 서버에서 응답 줄때 json을 javascript로 변경*/
			
			
			
$(function() {
	
	
	// 선택한 회원 삭제(여러개, 회원 목록에서)
	var selected = [];	
	$("#del-all-btn").click(function() {
		$("input[type='checkbox']:checked").each(function() { 
		       selected.push($(this).val());
		});
		console.log(selected);   // 맵으로 중복제거  
		
		$.ajax({
			type: "post",
			url: "/admin/userMultiDelPro/",
			data: JSON.stringify(selected),
			contentType:"application/json; charset=utf-8"
		})
		.done(function() {
			
			alert("삭제 완료");
			location.replace("/admin/userList");
		}) 
		.fail(function(request, error) {
			alert("오류다.");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		});//ajax   
		
		
	});//del-all-btn
		
		
	
	
	
	
	// 선택한 나의찜 삭제(1개, 찜 상세화면에서)
	/*$("#del-btn").click(function() {
		let id = $("#cartId").val();
		
		$.ajax({
			type: "post",
			url: "/cart/cartDelPro/"+id,	
		})
		.done(function() {
			
			alert("삭제 완료");
			history.back();
		}) 
		.fail(function(request,status,error) {
			alert("오류다.");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		});//ajax
	});//del-btn */
	
	
	
	//회원 삭제 전체 선택
	$("#check-all").click(function() {
		if ($("#check-all").is(':checked')) {
            $("input[type=checkbox]").prop("checked", true);
        } else {
            $("input[type=checkbox]").prop("checked", false);
        }
	});
	
	
});//ready
