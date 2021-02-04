<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
  .container {
    height: 630px;
  }
  </style>

<!-- body -->
<div class="container">
  <h2>로그인</h2>
  
  <form action="/auth/loginPro" method="post">
	  <table class="table table-striped">
	      <tr>
	      	<td>아이디 : </td>
	        <td><input type="text" placeholder="아이디 입력" name="username" required></td>
	      </tr>
	      <tr>
	        <td>비밀번호 : </td>
	        <td><input type="password" placeholder="비밀번호 입력" name="password" required></td>
	      </tr>
	      <tr>
	      	<td colspan="2" align="center">
	      		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				    <font color="red">
				        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
				    </font>
				</c:if>
	      	<td>
	      </tr>
	      <tr>
	        <td colspan="2" align="center">
		        <button class="btn btn-primary">로그인</button>
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
