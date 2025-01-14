<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>


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
			<h3>관리자페이지</h3>
		</div>

		
		<table class="table">
			<tr>
				<th>카테고리 아이디</th>
				<th>카테고리 명</th>
			</tr>

			<c:forEach items="${cateList}" var="dto">
				<tr>
					<td>${dto.cid}</td>
					<td>${dto.cname}<a href="/cate/delete/${dto.cname}">삭제</a></td>
				</tr>
			</c:forEach>
		</table>

		<a href="/admin/cateInsert">게시판등록</a>
	</div>



</body>
</html>