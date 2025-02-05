<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 입력 화면</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../part/part_header.jsp" %>


<!-- 	날려도 되는 jsp     -->

	<div class="container">
		<div>
			<h3>공지사항 입력</h3>
		</div>
		<div>
			<form:form modelAttribute="boardForm" action="/admin/noticeInsert" method="post" enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div"></form:errors>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

				<div>
					<label>제목</label> <input name="title">
				</div>
				<div>
					<label>작성자</label> <input name="writer" value="${userEntity.username}">
				</div>
				<div>
					<label>내용</label>
					<textarea rows="5" name="content"></textarea>
				</div>
				<div>
					<div class="preview"
						style="width: 100px; height: 100px; border: 1px solid;"></div>
					<input type="file" name="myfile">
				</div>
				<div>
					<button type="submit">글 입력 완료</button>
				</div>
				<a href="/board/${cateEntity.cid}/list">목록</a>
			</form:form>
		</div>
	</div>
	<script type="text/javascript" src="/js/boardService.js"></script>
</body>
</html>
