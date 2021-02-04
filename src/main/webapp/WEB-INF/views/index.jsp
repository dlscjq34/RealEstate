<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container" style="margin-top:30px">
  <div class="row justify-content-center">
   
    <div class="col-sm-10">
      <table>
      <tr> 
      	<td width="50%"><h2>최신 매물 TOP 5</h2></td>
      	<td width="10%"/>
      	<td><h2>최근 게시글</h2></td>
      </tr>
      	<tr style="vertical-align:middle;">
      		<td>
   			 	<table class="table table-hover">
			      <c:forEach items="${ list.content }" var="row" varStatus="status">
				      <tr>
				       <td width="50%" height="100em">
				       		${ status.index + 1 }.&nbsp;&nbsp;
				        	<a href="/maemool/detailForm?id=${ row.id }">
					        	<img src="${ row.fileUrl }" width="60%" class="img-thumbnail" alt="img"><br>
				        	</a>
				        </td>
				        <td  style="vertical-align:middle;">
				        	<h5>${ row.title }</h5>
			        	</td>
			       	</tr>	
			      </c:forEach>
			     </table>
      		</td>
      		<td/>
      		<td>
      		 <table class="table table-hover">
      		  <c:set value="0" var="status"/>
		      <c:forEach items="${ board.content }" var="board">
		      
			      <tr>
			        <td>
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
								<c:set value="${ status + 1 }" var="status"/>
								${ status }.&nbsp;&nbsp;
								<a href="/board/detailForm?id=${ board.id }">
			    					${ board.title }
			    				</a>
							</c:otherwise>
						</c:choose>
		        	</td>
		       	</tr>	
		      </c:forEach>
		     </table>
      		</td>  
      	</tr>
      	<tr align="right"> 
      	<td><a class="nav-link" href="/auth/maemoolView">더보기...</a></td>
      	<td></td>
      	<td><a class="nav-link" href="/auth/board">더보기...</a></td>
      	</tr>
      </table>
    </div>
  </div>
</div>
<hr>

<!-- footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>
