<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="/css/userUpdateForm.css" rel="stylesheet">
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container">

		<div>
			<h3 class="jeju-title">회원정보 수정화면</h3>
		</div>

		<div class="jeju-form-container">
			<form action="/user/update" method="post" enctype="multipart/form-data" class="jeju-form">
			<div class="jeju-image-upload">
				<div class="preview">
					<c:if test="${userResponse.profileImage != null}">
						<img src="/user/imgDisplay?fullFileName=${userResponse.profileImage}" class="profile-img">
					</c:if>
				</div>
				<div class="file-upload">
					<input type="file" name="profileImage" class="file-input">
				</div>
			</div>
			<div class="form-group">
				<label for="username">아이디:</label>
				<input name="username" value="${userResponse.username}" readonly="readonly" class="form-control" id="username">
			</div>
			<div class="form-group">
				<label for="name">이름:</label>
				<input name="name" value="${userResponse.name}" class="form-control" id="name">
			</div>
			<button type="submit" class="jeju-button">수정완료</button>
		</form>
	</div>
</div>

<script type="text/javascript" src="/js/userService.js"></script>
</body>
</html>