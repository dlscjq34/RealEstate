<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
  <style>
  .container {
    height: 630px;
  }
  </style>
  
<script src="/js/user.js"></script>

<!-- body -->
<div class="container">
  <h2>회원 가입</h2>
  <form>
	  <table class="table table-striped">
	      <tr>
	      	<td>아이디 : </td>
	        <td><input type="text" placeholder="아이디 입력 필수" id="username" required></td>
	      </tr>
	      <tr>
	        <td>비밀번호 : </td>
	        <td><input type="password" placeholder="비밀번호 입력 필수" id="password" required></td>
	      </tr>
	      <tr>
	        <td>주소 : </td>
	        <td><input type="text" placeholder="주소 입력" id="addr"></td>
	      </tr>
	      <tr>
	        <td>연락처: </td>
	        <td><input type="text" placeholder="연락처 입력" id="tel"></td>
	      </tr>
	      <tr>
	      	<td colspan="2" align="center">
	      </tr>
	      <tr>
	        <td colspan="2" align="center">
		        <button type="button" id="btn-join" type="button" class="btn btn-primary">가입신청</button>
				<button type="reset" class="btn btn-danger">초기화</button>
				<button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
			</td>
	      </tr>
	  </table>
  </form>
</div>

	



<!-- footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>
