<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<script src="/js/user.js"></script>

<!-- body -->
<div class="container">
  <h2>회원 정보</h2>
  <form>
	  <table class="table table-striped">
	      <tr>
	      	<td>아이디 : </td>
	        <td><input type="text" value="${ principal.user.username }" id="username" readonly></td>
	      </tr>
	      <tr>
	        <td>비밀번호 : </td>
	        <td><input type="password" id="password" required></td>
	      </tr>
	      <tr>
	        <td>주소 : </td>
	        <td><input type="text" value="${ principal.user.addr }" id="addr"></td>
	      </tr>
	      <tr>
	        <td>연락처: </td>
	        <td><input type="text" value="${ principal.user.tel }" id="tel"></td>
	      </tr>
	      <tr>
	      	<td colspan="2" align="center">&nbsp;
	      	</td>
	      </tr>
	      <tr>
	        <td colspan="2" align="center">
		        <button type="button" id="btn-userModify" type="button" class="btn btn-primary">수정하기</button>
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
