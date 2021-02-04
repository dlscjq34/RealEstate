<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<script src="/js/maemool.js"></script>


<!-- body -->
<div class="container">
	(매물 번호 <span id="id"><i>${ maemool.id }</i></span> )<!-- ajax로 hidden이 안되어서 억지로.. -->
	<h1>매물 정보 상세</h1><hr>
 <form action="/maemool/maemoolDetailPro" method="post" enctype="multipart/form-data">
	
	<input type="hidden" name="oldFileUrl" value="${ maemool.fileUrl }">
	<input type="hidden" name="id" value="${ maemool.id }">
	  <table class="table table-striped">
	      <tr>
	      	<td>제목 : </td>
	        <td><input type="text" name="title" value="${ maemool.title }" required></td>
	      </tr>
	      <tr>
	        <td>내용 : </td>
	        <td><input type="text" name="content" value="${ maemool.content }" required></td>
	      </tr>
	      <tr>
	        <td width="30%">첨부 파일 : </td>
	        <td>변경 전 : <img src="${ maemool.fileUrl }" width="30%" class="img-thumbnail" alt="img">
	        	변경 후 : <img id="foo" src="#" width="30%" class="img-thumbnail" alt="사진을 등록하세요"><br><br>
	        	<input type="file" name="fileUrl" id="imgInp">
        	</td>
	      </tr>
	      <tr>
	        <td>가격 : </td>
	        <td><input type="number" name="price" value="${ maemool.price }"required></td>
	      </tr>
	      <tr>
	      	<td colspan="2" align="center">
	      </tr>
	      <tr>
	        <td colspan="2" align="center">
		        <button type="submit" id="btn-upload" type="button" class="btn btn-primary">수정</button>
				<button type="reset" class="btn btn-warning">초기화</button>
				<button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
				<button type="button" class="btn btn-danger justify-content-center" id="del-btn">삭제</button>
			</td>
	      </tr>
	  </table>
  </form>
  
</div>

  
<!-- footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>




