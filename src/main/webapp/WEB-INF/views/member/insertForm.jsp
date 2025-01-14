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

	<div class="container">
		<div class="language-switch">
			<a href="?lang=ko">한국어</a>
			<a href="?lang=en">English</a>
		</div>
	
		<div>
			<h3><spring:message code="join"/></h3>  
		</div>

		<div>
			<form:form modelAttribute="memberForm" action="/member/insert" method="post" enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div"></form:errors>
				<div>
					<div class="preview" style="width: 100px; height: 100px; border: 1px solid;"></div>
					<spring:message code="member.profileImg"/> : <input type="file" name="profileImage">
				</div>
				<div>
					<form:label path="username"><spring:message code="member.username"/></form:label>
					<form:input path="username"/>
					<button id="member_insertForm_checkId" type="button"><spring:message code="member.chackID"/></button>
					<span></span>
				</div>
				<div>
					<form:label path="password"><spring:message code="member.password"/></form:label>
					<form:input path="password" type="password"/>
					<span></span>
				</div>
				<div>
					<form:label path="password2"><spring:message code="member.password2"/></form:label>
					<form:input path="password2" type="password"/>
					<span></span>
				</div>
				<div>
					<form:label path="name"><spring:message code="member.name"/></form:label>
					<form:input path="name"/>
				</div>
				<div>
					<button id="member_insertForm_submit" type="button">회원등록</button>
					<!-- <button>등록</button> -->
				</div>
			</form:form>
		</div>
	</div>

<script type="text/javascript" src="/js/memberService.js"></script>
</body>
</html>