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
	<h1>글 쓰기</h1><hr>
 <form action="/board/writePro" method="post" enctype="multipart/form-data">

	  <table class="table table-striped">
	      <tr>
	      	<td  width="10%">제목 : </td>
	        <td><input type="text" placeholder="제목 입력 필수" name="title" size="24" required></td>
	      </tr>
	      <tr>
	        <td>내용 : </td>
	        <td>
		        <textarea name="content" required rows="5" cols="60"></textarea>
			</td>
	      </tr>
	      <tr>
	        <td>첨부 파일 : </td>
	        <td><input type="file" name="fileUrl" id="imgInp"><br><br>
        		<img id="foo" src="#" width="30%" class="img-thumbnail" alt="사진을 등록하세요"><br><br>
       		</td>
	      </tr>
	      <tr>
	      	<td colspan="2" align="center">
	      		<input type="hidden" name="userId" value="${ principal.user.id }"> 
	      	</td>
	      </tr>
	      <tr>
	        <td colspan="2" align="center">
		        <button type="submit" id="btn-upload" type="button" class="btn btn-primary">등록</button>
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
