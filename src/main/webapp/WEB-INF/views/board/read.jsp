<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.details" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<h3><spring:message code="board.details" /></h3>
<form action="/board/delete" method="post" >
<input type="hidden" name="id" value="${boardResponse.id}"/>

	<div><spring:message code="board.number" />: ${boardResponse.id}</div>
	<div><spring:message code="board.writer" />: ${boardResponse.writer}</div>
	<div><spring:message code="board.title" />: ${boardResponse.title}</div>
	<div><spring:message code="board.content" /></div>
	<div>
		<p>${boardResponse.content}</p>
	</div>
	
	
	
	<div class="images">
		<c:forEach items="${fileList}" var="dto">
			<img src="/board/imgDisplay?fullFileName=${dto.filename}" width="100px" height="100px">
		</c:forEach>
	</div>
	
	<div>
	<a href="/board/list"><spring:message code="board.list" /></a>
	<a href="/board/update/${boardResponse.id}"><spring:message code="board.update" /></a>
	<a id=delete_board_botton href="#"><spring:message code="board.delete" /></a>
	</div>
</form>	
	
<script type="text/javascript">

$("#delete_board_botton").click(function(){
	$("form").submit();
	
	
	
	
});

</script>
</body>
</html>