<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:authorize access="isAuthenticated()">
	<s:authentication property="principal" var="principal"/>
</s:authorize>

<!DOCTYPE html>
<html>
<head>
  <title>RealEstate</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  </style>
</head>
<body>

<%-- 

<c:choose>
	<c:when test="${  }">
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<c:if test="${  }">
</c:if>		 

--%>

  <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/">부동산 온라인</a>
    	<c:choose>
		<c:when test="${ empty principal }">
			<div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
		      <ul class="navbar-nav center-block">
		        <li class="nav-item">
		          <a class="nav-link" href="/auth/loginForm">로그인</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/auth/joinForm">회원가입</a>
		        </li>
		      </ul>
		    </div>  
		</c:when>
		<c:otherwise>
			<div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
		      <ul class="navbar-nav center-block">
		        <li class="nav-item">
		          <a class="nav-link" href="/cart/cartForm">나의 찜</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/user/detailForm">나의 정보</a>
		        </li>
		        <c:if test="${ principal.user.role eq 'ADMIN' }">
		           <li class="nav-item">
			          <a class="nav-link" href="/admin/userList">회원 관리</a>
			        </li>
	  			</c:if>	
		        <li class="nav-item">
		          <a class="nav-link" href="/logout">로그아웃(${ principal.username }님)</a>
		        </li>
		      </ul>
		    </div>  
		</c:otherwise>
	</c:choose>
  </nav>
  
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
      <ul class="navbar-nav center-block">
        <li class="nav-item">
          <h5><a class="nav-link" href="/auth/maemoolView">매물</a></h5>
        </li>
        <li class="nav-item ml-5 mr-5">
          <h5><a class="nav-link" href="#">분양</a></h5>
        </li>
        <li class="nav-item">
          <h5><a class="nav-link" href="/auth/board">자유게시판</a></h5>
        </li>
      </ul>
    </div>  
  </nav>
  <hr>