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
	<h1>게시글 보기</h1>
 	<form action="/board/boardDetailPro" name="form1" method="post" enctype="multipart/form-data">
	 <table class="table table-hover">
      <tr>
        <td>작성자 : </td>
        <td><input type="text" name="username" id="username" value="${ board.user.username }" size="24" readonly></td>
      </tr>
      <tr>
        <td>제목 : </td>
        <td><input type="text" name="title" id="title" value="${ board.title }" size="57"></td>
      </tr>
      <tr>
        <td>내 용 : </td>
        <td><textarea name="content" id="content" rows="5" cols="60">${ board.content }</textarea>
      </tr>
      <tr>
        <td>작성일 : </td>
        <td><fmt:formatDate value="${ board.createDate }" pattern="yyyy-MM-dd kk:mm:ss"/></td>
      </tr>
      <tr>
        <td>첨부파일 : </td>					
        <td>
        	<c:choose>
        		<%-- 첨부파일이 업다면 --%>
				<c:when test="${ empty board.fileUrl }">
					<input type="file" name="newfileUrl">
				</c:when>
				<%-- 첨부파일이 있다면 .. 원래대로 클릭 시 원복, 파일 전송 체크 --%>
				<c:otherwise>					
					 <div class="oldFileArea">
					 	<a href="${ board.fileUrl }">${fn:substring(board.fileUrl, 45, fn:length(board.fileUrl)) }<!-- SUBSTRING : UUID 제거  --></a><br><br>
					 </div>
					 <div class="fileInputArea">
						<input type="file" name="newfileUrl" id="newfileUrl"><br><br><button type="button" class="btn btn-secondary" id="btn-fileBack">원래대로</button>
					 </div>
					<c:if test="${ principal.user.username eq board.user.username }">
			      		<button type="button" class="btn btn-info" id="btn-delFile">파일 삭제</button>
					</c:if> 
					 <input type="hidden" name="DelOldFile" id="DelOldFile" value="false">
				</c:otherwise>
			</c:choose> 
	       
        </td>
      </tr>
     <tr>
      	<td colspan="2" align="center">
      		<input type="hidden" name="oldFileUrl" value="${ board.fileUrl }">
      		<input type="hidden" name="fileUrl" value="${ board.fileUrl }">
			<input type="hidden" name="boardId" id="boardId" value="${ board.id }">
			<input type="hidden" name="createDate" value="${ board.createDate }">
			<input type="hidden" name="refNo" value="${ board.refNo }">
			<input type="hidden" name="refLev" value="${ board.refLev }">
			<input type="hidden" name="refSeq" value="${ board.refSeq }">
	
	      	<button type="button" class="btn btn-success" id="btn-reply">댓글</button>
	      	<c:if test="${ principal.user.username eq board.user.username }">
				<button type="reset" class="btn btn-warning">초기화</button>
	      		<button type="submit" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-danger" id="del-btn">삭제</button>
			</c:if>	
	      	
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




