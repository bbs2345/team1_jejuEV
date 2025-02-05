<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<style type="text/css">






label {
    font-weight: bold;
    color: #FF9F00;
    display: block;
    margin-bottom: 8px;
}


input[readonly] {
    background-color: #f9f9f9;
    color: #999;
}


button {
    display: inline-block;
    background: linear-gradient(90deg, #FF9F00, #FFA726);
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 8px;
    font-weight: bold;
    cursor: pointer;
    transition: background 0.3s ease;
}

button:hover {
    background: linear-gradient(90deg, #FFB74D, #FFCC80);
}

a {
    display: inline-block;
    color: #FF6F00;
    text-decoration: none;
    font-weight: bold;
    margin-top: 20px;
    transition: color 0.3s ease;
}

a:hover {
    color: #FF8A00;
}

.preview {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    margin-bottom: 20px;
}

.image-item {
    position: relative;
    display: inline-block;
}

.image-item img {
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

.image-item button {
    position: absolute;
    top: 5px;
    right: 5px;
    padding: 5px;
    font-size: 0.8rem;
    border-radius: 5px;
    background: rgba(255, 0, 0, 0.8);
    color: white;
    border: none;
    cursor: pointer;
    opacity: 0.8;
    transition: opacity 0.3s ease;
}

.image-item button:hover {
    opacity: 1;
}


</style>

   <link href="/css/boardStyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container" id="board_update_css">
	<div>
		<h3>게시물 수정</h3>
	</div>

	<form:form modelAttribute="boardForm" action="/board/update" method="post" enctype="multipart/form-data">
		<form:errors path="*" cssClass="errorblock" element="div"></form:errors>
		<input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		
		<div>
			<input type="hidden" id="id" name="id" value="${boardResponse.id}" />
		</div>

		<div>
			<label for="writer">작성자: </label>
			<input id="writer" name="writer" readonly="readonly" value="${boardResponse.writer}" />
		</div>

		<div>
			<label for="title">제목: </label>
			<input id="title" name="title" readonly="readonly" value="${boardResponse.title}" />
		</div>
		<div>
			<label for="content">내용: </label>
			<textarea id="content" name="content" row=7>${boardResponse.content}</textarea>
		</div>
		<div>
			<label for="fileUpload">이미지: </label>
			<div class="preview">
				<c:if test="${boardResponse.attachList != null}">
					<c:forEach items="${boardResponse.attachList}" var="dto">
						<div class="image-item">
							<img src="/board/imgDisplay?fullFileName=${dto.filename}" width="100" height="100">
							<button type="button" class="btn btn-danger btn-sm" data-filename="${dto.filename}" id="deleteImage">삭제</button>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<div>
				<input type="file" name="myfile" id="fileInput"/><img id="filePreview" width="100" height="100" style="display: none;" />
			</div>
		</div>

		<button>수정완료</button>

	</form:form>
	<a href="/board/list">목록</a>
</div>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/boardService.js"></script>
</body>
</html>
