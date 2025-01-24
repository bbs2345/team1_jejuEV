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
<link href="/css/join.css" rel="stylesheet">
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

	<div class="container">

		<div class="container">
			<form:form id="member_join_insertForm" modelAttribute="userForm"
				action="/auth/join" method="post" enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div"></form:errors>
				
				<div class="row">
					<div class="col-12 col-md-4">
						<h2>프로필 이미지</h2>
						<!-- 그리드 크기 조정 -->
						<div class="preview"
							style="width: 100%; height: auto; background-color: #f0f0f0; border-radius: 8px; overflow: hidden; display: flex; justify-content: center; align-items: center;"></div>
						<div>
							<input type="file" name="profileImage" class="custom-style"
								style="width: 100%;">
						</div>
					</div>

					<div class="col-12 col-md-5 custom-style">
						<div class="row">
							<h2 class="join-title">회원가입</h2>

							<!-- 아이디 -->
							<div class="col-12 mb-3">
								<form:label path="username" class="form-label">아이디</form:label>
								<div class="input-group">
									<form:input path="username" class="form-control" />
									<button id="auth_joinForm_checkId" type="button"
										class="btn btn-outline-primary">중복체크</button>
								</div>
							</div>

							<!-- 비밀번호 -->
							<div class="col-12 mb-3">
								<form:label path="password" class="form-label">비밀번호</form:label>
								<form:input path="password" type="password" class="form-control" />
							</div>

							<!-- 비밀번호 확인 -->
							<div class="col-12 mb-3">
								<form:label path="password2" class="form-label">비밀번호 확인</form:label>
								<form:input path="password2" type="password"
									class="form-control" />
							</div>

							<!-- 이름 -->
							<div class="col-12 mb-3">
								<form:label path="name" class="form-label">이름</form:label>
								<form:input path="name" class="form-control" />
							</div>

							<!-- 회원등록 -->
							<div class="col-12">
								<button id="auth_joinForm_submit" type="button"
									class="btn btn-primary w-100">회원등록</button>
							</div>
						</div>
			</form:form>
		</div>
	</div>

	<script type="text/javascript" src="/js/userService.js"></script>
</body>
</html>