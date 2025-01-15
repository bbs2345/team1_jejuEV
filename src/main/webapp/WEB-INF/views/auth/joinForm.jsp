<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container">
	<div>
		<h3>회원가입하기</h3>
	</div>

	<div>
		<form:form modelAttribute="userForm" action="/auth/join" method="post" enctype="multipart/form-data">
			<form:errors path="*" cssClass="errorblock" element="div"></form:errors>
			<div>
				<div class="preview" style="width: 100px; height: 100px; border: 1px solid;"></div>
				<input type="file" name="profileImage">
			</div>
			<div>
				<form:label path="username">아이디</form:label>
				<form:input path="username"/>
				<button id="auth_joinForm_checkId" type="button">중복체크</button>
				<span></span>
			</div>
			<div>
				<form:label path="password">비밀번호</form:label>
				<form:input path="password" type="password"/>
				<span></span>
			</div>
			<div>
				<form:label path="password2">비밀번호 확인</form:label>
				<form:input path="password2" type="password"/>
				<span></span>
			</div>
			<div>
				<form:label path="name">이름</form:label>
				<form:input path="name"/>
			</div>
			<div>
				<button id="auth_joinForm_submit" type="button">회원등록</button>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript" src="/js/userService.js"></script>
</body>
</html>