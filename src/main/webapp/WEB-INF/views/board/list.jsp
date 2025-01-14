<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.list" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style>
    .language-switch {
        display: flex;
        justify-content: flex-end; /* 오른쪽 정렬 */
        margin-bottom: 10px; /* 간격 추가 */
    }
    .language-switch a {
        margin-left: 10px; /* 링크들 간의 간격 */
    }
    .header-container {
        display: flex;
        justify-content: space-between; /* 좌우 정렬 */
        align-items: center; /* 수직 중앙 정렬 */
        margin-bottom: 20px;
    }
</style>
</head>
<body>
		<div class="language-switch">
			<a href="?lang=ko">한국어</a>
			<a href="?lang=en">English</a>
		</div>

	<h3><spring:message code="board.list" /></h3>

	<table>
		<thead>
			<tr>
				<td><spring:message code="board.number" /></td>
				<td><spring:message code="board.title" /></td>
				<td><spring:message code="board.writer" /></td>
	
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardResponseList}" var="dto">
				<tr>
					<td>${dto.id}</td>
					<td><a href="/board/read/${dto.id}">${dto.title}</a></td>
					<td>${dto.writer}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/board/insert"><spring:message code="board.newPost" /></a>
</body>
</html>