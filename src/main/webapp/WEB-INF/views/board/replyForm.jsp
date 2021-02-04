<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<script src="/js/board.js"></script>


<!-- body -->
<div class="container">
	
	<!-- 주글 정보 --------------------------------------------------------->
	<h1 class="text-primary">댓글 쓰기</h1><hr>
	 	<form action="/board/boardReplyPro" method="post" enctype="multipart/form-data">
	 <table class="table table-hover">
	  <tr>
        <td colspan="2" class="btn-secondary"><h3>주글 정보</h3></td>
      </tr>
      <tr>
        <td>제목 : </td>
        <td>${ board.title }</td>
      </tr>
      <tr>
        <td>작성자 : </td>
        <td>${ username }</td>
      </tr>
      <tr>
        <td>내 용 : </td>
        <td><textarea rows="5" cols="60" readonly>${ board.content }</textarea>
      </tr>
      <tr>
        <td>작성일 : </td>
        <td><fmt:formatDate value="${ board.createDate }" pattern="yyyy-MM-dd kk:mm:ss"/></td>
      </tr>
      <tr>
        <td>첨부파일 : </td>					
        <td>
			<a href="${ board.fileUrl }">${fn:substring(board.fileUrl, 45, fn:length(board.fileUrl)) }<!-- SUBSTRING : UUID 제거  --></a>
        </td>
      </tr>
      <!-- 댓글 작성 --------------------------------------------------------->
      <tr>
        <td colspan="2" class="btn-warning"><h3>댓글 작성</h3></td>
      </tr>
     <tr>
        <td>제목 : </td>
        <td><input type="text" name="title" id="title"></td>
      </tr>
      <tr>
        <td>내 용 : </td>
        <td><textarea name="content" id="content" required rows="5" cols="60" ></textarea>
      </tr>
      <tr>
      <tr>
        <td>첨부파일 : </td>					
        <td>
			<input type="file" name="fileUrl">
        </td>
      </tr>
      
     <tr>
      	<td colspan="2" align="center">
	      	<button type="submit" class="btn btn-primary">등록</button>
			<button type="reset" class="btn btn-warning">초기화</button>
			<button type="button" class="btn btn-danger" id="del-btn">삭제</button>
			<button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		</td>
      </tr>
  	</table>
  	
	<input type="hidden" name="refLev" value="${ board.refLev }">
	<input type="hidden" name="refSeq" value="${ board.refSeq }">
	<input type="hidden" name="refNo" value="${ board.refNo }">
	<input type="hidden" name="oldFileUrl" value="${ board.fileUrl }">
	<input type="hidden" name="boardId" id="boardId" value="${ board.id }">
	<input type="hidden" name="userId" value="${ principal.user.id }">
  </form>
</div>
${ board.refLev }
<!-- footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>




