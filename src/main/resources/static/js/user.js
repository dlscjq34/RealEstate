
$(function() {
	
	// 회원 가입
	$("#btn-join").click(function() {
		
		//컨트롤러행 데이터
		let datas = {
			username: $("#username").val(),
			password: $("#password").val(),
			addr: $("#addr").val(),
			tel: $("#tel").val()
		}
		
		// 아이디 입력체크
		if(datas.username.length < 5) {
			alert("아이디를 5자리 이상 입력하세요");
	      	return false;
		}
		
		// 비밀번호 입력체크
		if(datas.password.length < 5) {
			alert("비밀번호 5자리 이상 입력하세요");
	      	return false;
		}
		
		//아이디 중복체크
		$.ajax({
			type: "post",
			url: "/auth/idCheck",
			data: JSON.stringify(datas.username),
			contentType:"application/json; charset=utf-8",
			dataType: "json"	// 서버에서 응답 줄때 json을 javascript로 변경	
		})
		.done(function(username) {
			
			if(username == true) {
				alert("사용 중인 아이디입니다.");
				return false;				
			}
		}) 
		.fail(function(error) {
				alert(error.responseJSON.message);
		});//ajax
			
		
		// 가입 처리
		$.ajax({
			type: "post",
			url: "/auth/joinPro",
			data: JSON.stringify(datas),
			contentType:"application/json; charset=utf-8",
			dataType: "json"	// 서버에서 응답 줄때 json을 javascript로 변경	
		})
		.done(function(user) {
			alert(user.username+"님, 반갑습니다. 로그인 해주십시오.");
			location.href="/auth/loginForm";
		}) 
		.fail(function(error) {
			alert(error.responseJSON.message);
		});//ajax
	});//btn-join
	
	
	
	
	
	
	
	
	
	
	// 회원 정보 수정 --------------------------------------
	$("#btn-userModify").click(function() {
		
		let datas = {
			username: $("#username").val(),
			password: $("#password").val(),
			addr: $("#addr").val(),
			tel: $("#tel").val()
		}
		
		$.ajax({
			type: "post",
			url: "/user/detailPro",
			data: JSON.stringify(datas),
			contentType:"application/json; charset=utf-8",
			dataType: "json"	// 서버에서 응답 줄때 json을 javascript로 변경	
		})
		.done(function(user) {
			alert(user.username+"님, 회원정보가 수정되었습니다.");
		}) 
		.fail(function(error) {
			alert("에러 메시지 : "+error.responseJSON.message);
		});//ajax
	});//btn-userModify 
	
});//ready
