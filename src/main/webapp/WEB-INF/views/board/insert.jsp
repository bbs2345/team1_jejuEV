<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board.editor" /></title>
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
		
<h3><spring:message code="board.editor" /></h3>


<form action="/board/insert" method="post" enctype="multipart/form-data">
	<div>
		<label><spring:message code="board.title" /></label>
		<input name="title">
	</div>
	<div>
		<label><spring:message code="board.writer" /></label>
		<input name="writer">
	</div>
	<div>
		<label><spring:message code="board.content" /></label>
		<textarea rows="5" name="content"></textarea>
	</div>
	<div>
	<input type="File" name="myfile">
	</div>
	<div>
		<button type="submit"><spring:message code="board.input" /></button>
	</div>
	<a href="/board/list"><spring:message code="board.list" /></a>
</form>
</body>
</html>