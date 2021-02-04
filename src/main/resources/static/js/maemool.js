
$(function() {
	
	
	
	
	
	
	
	
	//매물 찜
	$("#btn-jjim").click(function() {
		data= {
			username: $("#username").val(),
			password: $("#password").val(),
			id: $("#maemoolId").val(),
			title: $("#title").val(),
			content: $("#content") .val(),
			addr: $("#addr").val(),
			fileUrl: $("#fileUrl").val(),
			price: $("#price").val()
		}
		
		console.log(data);
		
		$.ajax({
			type: "post",
			url: "/cart/cartPro/",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			//dataType: "json"	// 서버에서 응답 줄때 json을 javascript로 변경	
		})
		.done(function() {
			
			alert("찜하기 완료");
			location.href="/cart/cartForm";
		}) 
		.fail(function(request, error) {
			alert("오류다.");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		});//ajax
	
		
	});//btn-jjim
	
	
	
	
	// 매물 정보 삭제
	$("#del-btn").click(function() {
		let id = $("#id").text();
		
		$.ajax({
			type: "post",
			url: "/maemool/maemoolDelPro/"+id,
			/*contentType:"application/json; charset=utf-8",*/
			/*dataType: "json"	// 서버에서 응답 줄때 json을 javascript로 변경*/	
		})
		.done(function() {
			
			alert("삭제 완료");
			history.back();
		}) 
		.fail(function(request,status,error) {
			alert("오류다.");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		});//ajax
	});//del-btn 
	
	
	
	
	// 실시간 이미지 표시
	function readURL(input) {
	 if (input.files && input.files[0]) {
	  var reader = new FileReader();
	  
	  reader.onload = function (e) {
	   $('#foo').attr('src', e.target.result);  
	  }
	  
	  reader.readAsDataURL(input.files[0]);
	  }
	}
	 
	// 이벤트를 바인딩해서 input에 파일이 올라올때 위의 함수를 this context로 실행.
	$("#imgInp").change(function(){
	   readURL(this);
	});
	
});//ready
