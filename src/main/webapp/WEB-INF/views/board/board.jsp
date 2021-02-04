<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
  .container {
    height: 630px;
  }
  </style>
<script src="/js/board.js"></script>

<!-- body -->
<div class="container">
  <h2 align="center">자유 게시판</h2><hr>
  		<table class="table table-hover" >
	    <thead class="thead-dark">
	      <tr align="center">
	        <th>작성자</th>
	        <th>제목</th>
	        <th>작성일</th>
	        <c:if test="${ principal.user.role eq 'ADMIN' }">
	    		<th>삭제 &nbsp;<input type="checkbox" id="check-all"></th>
  			</c:if>	
	      </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="board" items="${ board.content }">
	    	<tr align="center"> 
	    		<td>${ board.user.username }
	    		<td align="left">
    			<c:choose>
					<c:when test="${ board.refLev gt 0 }">
						<div style="text-indent: ${ board.refLev * 20 }px;">
		    				<img src="/images/댓글.png" width="15px">
		    				<a href="/board/detailForm?id=${ board.id }">
		    					${ board.title }
		    				</a>
	    				</div>
					</c:when>
					<c:otherwise>
						<a href="/board/detailForm?id=${ board.id }">
	    					${ board.title }
	    				</a>
					</c:otherwise>
				</c:choose>
    			</td>
	    		<td><fmt:formatDate value="${ board.createDate }" pattern="yyyy-MM-dd"/> </td>
	    		<c:if test="${ principal.user.role eq 'ADMIN' }">
	    			<td><input type="checkbox" id="boardId" name="boardId" value="${ board.id }"></td>
  				</c:if>	
	    		
	    	</tr>
	    	</c:forEach> 
	    	<c:if test="${ principal.user.role eq 'ADMIN' }">
	    		<tr>
			      	<td colspan="3"/>
			      	<td align="center"><button id="del-all-btn">삭제</button></td>
		      	</tr>
  			</c:if>	
	    	
	    	<tr align="center">  
	    		<!-- 검색 -->
	    		<td colspan="4">
	    			<select id="sel">
					  	<option value="title">제목</option>
					    <option value="userId">아이디</option>
					  </select>
					  <input type="text" id="search">&nbsp;
					  <button class="btn btn-warning" 
					  		  onclick="location.href='/auth/board?sel='+$('#sel').val()+'&search='+$('#search').val()">
					  	검색
					  </button>
	    			<br><hr> 
	    			<!-- 글쓰기 -->
	    			<button class="btn btn-primary" onclick="location.href='/board/writeForm'">글 쓰기</button>
	    	</tr>
		</tbody>
		</table>
		
	<!-- 페이징(컨트롤러에서 페이징 세팅 다 해서 넘어온다.) -->
	<ul class="pagination justify-content-center">
		
		<!-- 페이징 기준(개수) -->
		<c:set value="3" var="indexSize"/>
		
	 <!-- 각 페이지 리스트의 첫페이지 -->
	    <c:set var="firstIndex" value="${ board.number - (board.number % indexSize) }"/>
		
						
		<!-- 각 페이지 리스트의 마지막페이지 -->
		<c:choose>
			<c:when test="${ (finalLastIndex - firstIndex) <= indexSize-1 }">
				<c:set var="lastIndex" value="${ finalLastIndex }"/>	
			</c:when>
			<c:otherwise>
				<c:set var="lastIndex" value="${ firstIndex + indexSize-1 }"/>
			</c:otherwise>
		</c:choose>	
	
	
		<!-- 이전 -->
	    <c:choose>
			<c:when test="${ firstIndex eq 0 }">
				<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${ firstIndex - indexSize }">Previous</a></li>
			</c:otherwise>
		</c:choose>				
			
	
		<!-- 페이지 리스트 (현재 페이지 식별 표시) -->
	<c:if test="${ lastIndex gt 0 }"><%-- 내용이 있을 때만 페이징 표시 --%>
		<c:forEach var="i" begin="${ firstIndex }" end="${ lastIndex }">
			<c:choose>
				<c:when test="${ i eq board.number }">
					<c:choose>
						<c:when test="${ empty search }">
							<li class="page-item active"><a class="page-link" href="?page=${ page + i }">${ page + i+1 }</a></li>	 
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link" href="?sel=${ sel }&search=${ search }&page=${ page + i }">${ page + i+1 }</a></li>
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
	</c:if>
	
		<!-- 다음 -->	
		 <c:choose>
			<c:when test="${ (finalLastIndex - firstIndex) < indexSize }">
				<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${ firstIndex + indexSize }">Next</a></li>
			</c:otherwise>
		</c:choose>	
	
	</ul>
		
	
</div>

<br>
<%-- 	
	페이지당 표시 list.size: ${ board.size }<br>
	현재 페이지 board.number : ${ board.number }<br>
	시작 페이지 firstIndex: ${ firstIndex  }<br>
	 끝 페이지 lastIndex: ${ lastIndex  }<br>
	totalCount :  ${ totalCount }<br>
	finalLastIndex :  ${ finalLastIndex }<br>
 --%>

<!-- footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>
