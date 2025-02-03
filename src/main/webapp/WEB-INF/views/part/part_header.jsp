<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set value="${sessionScope.userEntity}" var="userEntity"/>
<c:set value="${applicationScope.cateList}" var="cateList"/>

 <sec:authorize access="isAuthenticated( )">
  <sec:authentication property="principal" var="principal"/>
</sec:authorize> 
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>


<div class="container-fluid">
	
	<!-- 로그인 -->
	<div class="d-flex justify-content-between">
		
		
		<div>
			<c:if test="${principal.role == 'ROLE_ADMIN'}">
			<a href="/admin/userList">회원목록</a>
			<a href="/admin/home">관리자페이지</a>
			</c:if>
		</div>
		
		
		<div>
			<c:if test="${empty principal}">
				<a href="/auth/loginForm">로그인</a>
				<a href="/auth/joinForm">회원가입</a>
			</c:if>
			<c:if test="${not empty principal}">
				<span>${principal.name}님 환영합니다.</span>
				<a href="/auth/logout">로그아웃</a>
				<a href="/user/read/${principal.username}">회원정보</a>
			</c:if>
		</div>
	</div>

	<!-- 메뉴 -->
	<nav class="navbar" style="background-color: #e3f2fd;">
		<ul class="nav">
			<li class="nav-item"><a class="nav-link" href="/">메인</a></li>
			<li class="nav-item"><a class="nav-link" href="/ev/list">전기차 충전소</a></li>
			<li class="nav-item"><a class="nav-link" href="/board/list">전체글보기</a></li>
			<c:forEach items="${cateList}" var="cate">
				<li class="nav-item"><a class="nav-link" href="/board/${cate.cid}/list">${cate.cname}</a></li>
			</c:forEach>
		</ul>
	</nav>

</div>

