<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<script src="/js/admin.js"></script>
<!-- body -->
<div class="container">
  <h2 align="center">회원 목록</h2><hr>
  	
  		<table class="table table-hover">
	    <thead class="thead-light">
	      <tr align="center">
	        <th>이름</th>
	        <th>주소</th>
	        <th>연락처</th>
	        <th>관심매물</th>
	        <th>가입일</th>
	        <th>삭제 &nbsp;<input type="checkbox" id="check-all"></th>
	      </tr>
	    </thead>
	    <tbody>
	
		<!-- 회원 목록 나열 -->
	    <c:forEach items="${ userData.users }" var="data" varStatus="i">
	     <c:set var="maemoolTitle" value="${userData.maemoolTitle}"/><!-- 대표 찜제목 -->
	     <c:set var="userCartCount" value="${userData.userCartCount}"/><!-- 대표 찜 외 찜한 개수 -->
	    <tr align="center">
	    	<td>${ data.username }</td>
	    	<td>${ data.addr }</td>
	    	<td>${ data.tel }</td>
	    	<td>
	    		<!-- 찜한 매물 유무에 따른 화면 표시 -->
	    		<c:choose>
					<c:when test="${ userCartCount[i.index] eq 0 }">
						없음
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${ userCartCount[i.index] eq 1 }">
								${ userCartCount[i.index] }건
							</c:when>
							<c:otherwise>
								${ maemoolTitle[i.index] } 외 ${ userCartCount[i.index] - 1}건	
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
	    		
	    	</td>
	    	<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${ data.createDate }"/></td>
	    	<td><input type="checkbox" id="userId" name="userId" value="${ data.id }"></td>
	    </tr>
	    
	    </c:forEach>
	    <tr>
	      	<td colspan="5"/>
	      	<td align="center"><button id="del-all-btn">삭제</button></td>
	      </tr>
	    </tbody>
	  </table>
  
  
  
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
