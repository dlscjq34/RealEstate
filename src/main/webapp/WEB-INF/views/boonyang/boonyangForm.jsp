<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>


<!-- body -->
<div class="container">
	<h1>매물 등록</h1><hr>
 <form action="/maemool/maemoolPro" method="post" enctype="multipart/form-data">

	  <table class="table table-striped">
	      <tr>
	      	<td>제목 : </td>
	        <td><input type="text" placeholder="제목 입력 필수" name="title" required></td>
	      </tr>
	      <tr>
	        <td>내용 : </td>
	        <td><input type="text" placeholder="내용 입력 필수" name="content" required></td>
	      </tr>
	      <tr>
	        <td>첨부 파일 : </td>
	        <td><input type="file" name="fileUrl"></td>
	      </tr>
	      <tr>
	        <td>가격 : </td>
	        <td><input type="number" placeholder="가격 입력" name="price" required></td>
	      </tr>
	      <tr>
	      	<td colspan="2" align="center">
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
