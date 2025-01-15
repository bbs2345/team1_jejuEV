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
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container">
	<div>
		<h3>회원정보 수정화면</h3>
	</div>
	
	<div>
		<form action="/user/update" method="post" enctype="multipart/form-data">
			<div>
				<div class="preview">
					<c:if test="${userResponse.profileImage != null}">
						<img src="/user/imgDisplay?fullFileName=${userResponse.profileImage}" width="100" height="100">
					</c:if>
				</div>
				<div>
					<input type="file" name="profileImage">
				</div>
			</div>
			<div>
				아이디 : <input name="username" value="${userResponse.username}" readonly="readonly">
			</div>
			<div>
				이름 : <input name="name" value="${userResponse.name}">
			</div>
			<button>수정완료</button>
		</form>
	</div>
</div>

<script type="text/javascript" src="/js/userService.js"></script>
</body>
</html>