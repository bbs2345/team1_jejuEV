<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.updatePost" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<h3><spring:message code="board.updatePost" /></h3>

	<form action="/board/update" method="post" id="myform">
		<div>
			<input type="hidden" id="id" name="id" value="${boardResponse.id}" />
		</div>	
		
		<div>
			<label for="writer"><spring:message code="board.writer" />: </label><input id="writer" name="writer" readonly="readonly" value="${boardResponse.writer}"/>
		</div>	
		
		<div>
			<label for="title"><spring:message code="board.title" />: </label><input id="title" name="title" readonly="readonly" value="${boardResponse.title}"/>
		</div>
		<div>
			<label for="content"><spring:message code="board.content" />: </label>
			<textarea id="content" name="content" row=7>${boardResponse.content}</textarea>
		</div>
		
		<input type="file" name="myfile"> <br>
		<button><spring:message code="board.updateComplete" /></button>
		
	</form>
		<a href="/board/list"><spring:message code="board.list" /></a>
</body>
</html>