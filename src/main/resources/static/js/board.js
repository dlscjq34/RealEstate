
$(function() {
	
	
	
	// 댓글 쓰기 페이지 갈 때 같이 갈 주글 데이터
	$("#btn-reply").click(function() {
		form1.action="/board/replyForm";
		form1.method="post";
		form1.submit();
	});	
		


	//게시글 체크박스 전체 선택
	$("#check-all").click(function() {
		if ($("#check-all").is(':checked')) {
            $("input[type=checkbox]").prop("checked", true);
        } else {
            $("input[type=checkbox]").prop("checked", false);
        }
	});
	
	
	// 선택한 게시글 삭제(여러개, 게시판목록에서)
	var selected = [];	
	$("#del-all-btn").click(function() {
		$("input[type='checkbox']:checked").each(function() { 
		       selected.push($(this).val());
		});
		console.log(selected);  
		
		$.ajax({
			type: "post",
			url: "/board/boardMultiDelPro",
			data: JSON.stringify(selected),
			contentType:"application/json; charset=utf-8"
			
		})
		.done(function() {
			alert("삭제 완료");
			location.replace("/auth/board");
		}) 
		.fail(function(request, error) {
			alert("오류다.");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		});//ajax   
		
		
	});//del-all-btn
		
	
	
	
	
	// 게시글 삭제
	$("#del-btn").click(function() {
		let boardId = $("#boardId").val();
		
		
		$.ajax({
			type: "post",
			url: "/board/boardDelPro/",
			data: JSON.stringify(boardId),
			contentType:"application/json; charset=utf-8"
		})
		.done(function() {
			
			alert("삭제 완료");
			location.replace("/auth/board");
		}) 
		.fail(function(request, error) {
			alert("오류다.");
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		});//ajax
	});//del-btn
	
	
	
	
	
	// 상세 페이지 파일 처리
	$(".fileInputArea").hide();
	
	$("#btn-delFile").click(function() {
		// 삭제 버튼 누르면 기존 파일 숨기고 새파일저장버튼 생성, 기존파일삭제 히든 트루
		$(".oldFileArea").hide();
		$("#btn-delFile").hide();
		$(".fileInputArea").toggle();
		$("#DelOldFile").val('true');
	});
	$("#btn-fileBack").click(function() {
		// 되돌리기 버튼 누르면 기존 파일 보이고 새파일저장버튼 숨기기, 기존파일삭제 히든 폴스
		$(".oldFileArea").show();
		$("#btn-delFile").show();
		$(".fileInputArea").toggle();
		$("#DelOldFile").val('false');
	});
	

		
	
	
	//매물 찜
	/*$("#btn-upload").click(function() {
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
	
		
	});//btn-jjim*/
	
	
	
	
	
	
	
	
	
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
