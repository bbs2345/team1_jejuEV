<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="?lang=ko">한국어</a>
		<a href="?lang=en">English</a>
	</div>
	
	<a href="/member/insertForm"><spring:message code="join"></spring:message></a>
	
	<a href="#"><spring:message code="login"/></a>
	

	

</body>
</html>