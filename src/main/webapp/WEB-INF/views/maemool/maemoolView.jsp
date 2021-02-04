<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<script src="/js/maemool.js"></script>


<!-- body -->
<div class="row text-center">
    <div class="col-sm-10" style="width: 30%; margin:0 auto"><!-- 전체 컨테이너의 절반 크기로 띄우게 됨 -->
    	<h1>매물</h1><hr>
        <table class="table table-bordered">
		      
      
      <!-- 테이블 3열 3행 처리 -->
      <c:set var="i" value="0" />
	  <c:set var="j" value="2" /> 
      <c:forEach items="${ list.content }" var="row">
	      <c:if test="${ i % j eq 0}">
		      <tr>
	      </c:if>
		       <td width="50%">
		        	<a href="/maemool/detailForm?id=${ row.id }">
			        	<img src="${ row.fileUrl }" style="max-width: 50%; height: 150px;" class="img-thumbnail" alt="img"><br>
		        	</a>
		        	<hr>
		        	<h2>${ row.title }</h2>
		        	<hr>
		        	${ row.content }
	        	</td>
	       <c:if test="${ i eq j - 1}">
		      <tr></tr>
	      </c:if>
       	  <c:set var="i" value="${ i + 1 }" />
      </c:forEach>
  </table>
  
  <select id="sel">
  	<option value="title">제목</option>
    <option value="addr">위치</option>
  </select>
  <input type="text" id="search">&nbsp;
  
  <button class="btn btn-warning" 
  		  onclick="location.href='/auth/maemoolView?sel='+$('#sel').val()+'&search='+$('#search').val()">
  	매물 검색
  </button><hr>
  
  
  
		
  <c:if test="${ principal.user.role eq 'ADMIN' }">
	  <button class="btn btn-primary" onclick="location.href='/maemool/maemoolForm'">매물 등록</button>
  </c:if>	
  
  
  
  
  <c:if test="${ totalCount > 0 }">
  
	  <!-- 페이징 -->
	  <hr>
	    <ul class="pagination justify-content-center">
	    
	    <!-- 각 페이지 리스트의 첫페이지 -->
	    <c:set var="firstIndex" value="${ list.number - (list.number % 3) }"/>
		
						
		<!-- 각 페이지 리스트의 마지막페이지 -->
		<c:choose>
			<c:when test="${ (finalLastIndex - firstIndex) <= 2 }">
				<c:set var="lastIndex" value="${ finalLastIndex }"/>	
			</c:when>
			<c:otherwise>
				<c:set var="lastIndex" value="${ firstIndex + 2 }"/>
			</c:otherwise>
		</c:choose>	
		
		
		
		
	    <!-- 이전 -->
	    <c:choose>
			<c:when test="${ firstIndex eq 0 }">
				<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${ firstIndex - 3 }">Previous</a></li>
			</c:otherwise>
		</c:choose>				
			
			
			
		
	    
		
		
		<!-- 페이지 리스트 (현재 페이지 식별 표시) -->
		<c:forEach var="i" begin="${ firstIndex }" end="${ lastIndex }">
			<c:choose>
				<c:when test="${ i eq list.number }">
					<c:choose>
						<c:when test="${ empty search }">
							<li class="page-item active"><a class="page-link" href="?page=${ page + i }">${ page + i+1 }</a></li>	 
						</c:when>
						<c:otherwise>
							<li class="page-item       "><a class="page-link" href="?sel=${ sel }&search=${ search }&page=${ page + i }">${ page + i+1 }</a></li>
						</c:otherwise>
					</c:choose>		
					
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${ empty search }">
							<li class="page-item       "><a class="page-link" href="?page=${ page + i }">${ page + i+1 }</a></li>	 
						</c:when>
						<c:otherwise>
							<li class="page-item       "><a class="page-link" href="?sel=${ sel }&search=${ search }&page=${ page + i }">${ page + i+1 }</a></li>
						</c:otherwise>
					</c:choose>		
				</c:otherwise>
			</c:choose>		
		</c:forEach>
			
			
			
			
			
		<!-- 다음 -->	
		 <c:choose>
			<c:when test="${ (finalLastIndex - firstIndex) < 3 }">
				<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${ firstIndex + 3 }">Next</a></li>
			</c:otherwise>
		</c:choose>	
		</ul>
		
	</c:if>
    </div>
</div>

  

<!-- footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>
