<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<script src="/js/cart.js"></script>

<!-- body -->
<div class="container">
  <h2 align="center">나의 찜 보기</h2><hr>
  <c:choose>
  	<c:when test="${ empty cart }">
  		<h5 align="center">등록한 찜이 없습니다.</h5><hr>
  	</c:when>
  	
  	<c:otherwise>
  		<table class="table table-hover">
	    <thead class="thead-dark">
	      <tr>
	        <th>이미지</th>
	        <th>물건명</th>
	        <th>금액</th>
	        <th>삭제 &nbsp;<input type="checkbox" id="check-all"></th>
	      </tr>
	    </thead>
	    <tbody>
	    
	    <c:forEach items="${ cart }" var="row">
	    <tr>
	    	<td  width="40%" onclick="location.href='/cart/detailForm?maemoolId=${ row.maeMool.id }&cartId=${ row.id }'">
	    		<img src="${ row.maeMool.fileUrl }" width="50%" class="img-thumbnail" alt="img" style="cursor:pointer;">
	    	</td>
	    	<td>${ row.maeMool.title }</td>
	    	<td>${ row.maeMool.price }</td>
	    	<td><input type="checkbox" id="cartId" name="cartId" value="${ row.id }"></td>
	    </tr>
	    </c:forEach>
	      <tr>
	      	<td colspan="3"/>
	      	<td><button id="del-all-btn">삭제</button></td>
	      </tr>
	    </tbody>
	  </table>
  	</c:otherwise>
  </c:choose>
  
  
  
  <form>
	  <table class="table table-striped">
	      <tr>
	        <td colspan="2" align="center">
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
