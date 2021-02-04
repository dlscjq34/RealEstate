<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
  .container {
    height: 1100px;
  }
  </style>
<script src="/js/cart.js"></script>



<!-- body -->

<div class="container">
	(매물 번호 <span id="id"><i>${ maemool.id }</i></span> )<!-- ajax로 hidden이 안되어서 억지로.. -->
	<h1>찜한 매물 상세</h1><hr>
 	<form action="/maemool/maemoolDetailPro" method="post" enctype="multipart/form-data">
	 <table class="table table-hover">
      <tr>
        <td rowspan="5" width="50%">
       		<img src="${ maemool.fileUrl }" width="100%" class="img-thumbnail" alt="img">
      <tr>
        <td>제목 : <input type="text" name="title" id="title" value="${ maemool.title }" required></td>
      </tr>
      <tr>
        <td>내용 : <textarea name="content" id="content" required rows="5" cols="23" >${ maemool.content }</textarea>
      </tr>
      <tr>
        <td>위치 : <input type="text" name="addr" id="addr" value="${ maemool.addr }"required></td>
      </tr>
      <tr>
        <td>가격 : <input type="number" name="price" id="price" value="${ maemool.price }"required></td>
      </tr>
      <tr>
      	<td colspan="2" align="center">
      		<input type="hidden" id="cartId" value="${ cartId }">
			<button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
			<button type="button" class="btn btn-danger justify-content-center" id="del-btn">삭제</button>
		</td>
      </tr>
  	</table>
  	
	<input type="hidden" id="maemoolId" value="${ maemool.id }">
  </form>
  
  <p style="margin-top:-12px">
	    <em class="link">
	        <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
	            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
	        </a>
	    </em>
	</p>
   <h3><a href="https://map.kakao.com/link/to/${ maemool.addr },37.402056,127.108212" target="_blank">[ 길 찾기 바로가기 ]</a></h3>
   <div id="map" style="width:100%;height:400px;" class="img-thumbnail col-sm-30"></div>
	   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c7bdb165ac014e858b88e2f0b2f77b00&libraries=services"></script>
	   <script>
	   
	   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
	
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
	
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${ maemool.addr }', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		    	 var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
	
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">매물 위치</div>'
		        });
		        infowindow.open(map, marker);
	
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
	});    
		</script>
		<hr>
    </div>
  
</div>

<!-- footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>




