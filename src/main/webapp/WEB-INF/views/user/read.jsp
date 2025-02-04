<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="isAuthenticated( )">
   <sec:authentication property="principal" var="principal"/>
</sec:authorize> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="/css/userRead.css" rel="stylesheet">
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container">
	<div>
		<h3 class="jeju-header">회원정보 상세보기</h3>
	</div>
	
	<div class="jeju-info">
		<div class="profile-image">
			<img src="/user/imgDisplay?fullFileName=${userResponse.profileImage}" class="profile-img">
		</div>
		<div class="info-item">
			아이디 : ${userResponse.username}
		</div>
		<div class="info-item">
			이름 : ${userResponse.name}
		</div>
		<div class="info-item">
			가입일 : ${userResponse.createDate}
		</div>
	</div>
	
	<div class="jeju-links">
		<a href="/" class="jeju-link">메인페이지</a>
		
		<c:if test="${principal != null and principal.role == 'ROLE_ADMIN'}">
			<a id="toUserList" href="${criteria.page}" class="jeju-link">목록</a>
		</c:if>
		
		
		<a href="/user/updateForm/${userResponse.username}" class="jeju-link">회원정보 수정</a>
		<a id="user_read_deleteuser" href="${userResponse.username}" class="jeju-link">회원탈퇴</a>
		<input id="csrf_value" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</div>
</div>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/userService.js"></script>
<script type="text/javascript" src="/js/adminService.js"></script>
</body>
</html>