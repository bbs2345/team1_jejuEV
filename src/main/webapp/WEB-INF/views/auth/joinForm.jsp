<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container">
	<div>
		<h2>회원가입하기</h2>
	</div>
	
	<div class="container">
		<form:form id="member_join_insertForm" modelAttribute="userForm" action="/auth/join" method="post" enctype="multipart/form-data">
			<form:errors path="*" cssClass="errorblock" element="div"></form:errors>
			<div class="row">
				<div class="col-12 col-md-4"> <!-- 그리드 크기 조정 -->
					<div class="preview" style="width: 100%; height: 200px; background-color: #f0f0f0; border-radius: 8px;"></div>
					<div>
						<input type="file" name="profileImage" class="custom-style" style="width: 100%;">
					</div>
				</div>

				<div class="col-12 col-md-8 custom-style">
					<div class="row">
						<div class="col-3">
							<form:label path="username">아이디</form:label>
						</div>
						<div class="col-6">
							<form:input path="username" class="custom-style" style="width: 100%;"/>
							<span></span>
						</div>
						<div class="col-3">
							<button id="auth_joinForm_checkId" type="button" id="member_join_btn_checkId" class="custom-style">중복체크</button>
						</div>
					</div>
					
					<div class="row">
						<div class="col-3">
							<form:label path="password">비밀번호</form:label>
						</div>
						<div class="col-6">
							<form:input path="password" type="password" class="custom-style" style="width: 100%;"/>
						</div>
						<div class="col-3">
							<span></span>
						</div>
					</div>
					
					<div class="row">
						<div class="col-3">
							<form:label path="password2">비밀번호 확인</form:label>
						</div>
						<div class="col-6">
							<form:input path="password2" type="password" class="custom-style" style="width: 100%;"/>
						</div>
						<div class="col-3">
							<span></span>
						</div>
					</div>
					
					<div class="row">
						<div class="col-3">
							<form:label path="name">이름</form:label>
						</div>
						<div class="col-6">
							<form:input path="name" class="custom-style" style="width: 100%;"/>
						</div>
						<div class="col-3">
						</div>
					</div>
					
					<div class="row">
						<button id="auth_joinForm_submit" type="button" class="custom-style">회원등록</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript" src="/js/userService.js"></script>
</body>
</html>