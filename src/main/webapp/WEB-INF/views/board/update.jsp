<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<div>
			<h3>게시물 수정</h3>
		</div>

		<form:form modelAttribute="boardForm" action="/board/update"
			method="post" enctype="multipart/form-data">
			<form:errors path="*" cssClass="errorblock" element="div"></form:errors>

			<div>
				<input type="hidden" id="id" name="id" value="${boardResponse.id}" />
			</div>

			<div>
				<label for="writer">작성자: </label><input id="writer" name="writer"
					readonly="readonly" value="${boardResponse.writer}" />
			</div>

			<div>
				<label for="title">제목: </label><input id="title" name="title"
					readonly="readonly" value="${boardResponse.title}" />
			</div>
			<div>
				<label for="content">내용: </label>
				<textarea id="content" name="content" row=7>${boardResponse.content}</textarea>
			</div>
			<div>
				<label for="fileUpload">이미지: </label>
				<div class="preview">
					<c:if test="${fileList != null}">
						<c:forEach items="${fileList}" var="dto">
							<div class="image-item">
								<img src="/board/imgDisplay?fullFileName=${dto.filename}"
									width="100" height="100">
								<button type="button" class="btn btn-danger btn-sm" data-filename="${dto.filename}" id="deleteImage">삭제</button>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div>
					<input type="file" name="myfile" id="fileInput"
						onchange="previewFile()" /> <img id="filePreview" width="100"
						height="100" style="display: none;" />
				</div>
			</div>

			<button>수정완료</button>

		</form:form>
		<a href="/board/list">목록</a>
	</div>

	<script type="text/javascript" src="/js/boardService.js"></script>
</body>
</html>
