<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 입력 화면</title>
</head>
<body>
<h3>게시글 입력</h3>

<form method="post">
	<div>
		<label>제목</label>
		<input name="title">
	</div>
	<div>
		<label>작성자</label>
		<input name="writer">
	</div>
	<div>
		<label>내용</label>
		<textarea rows="5" name="content"></textarea>
	</div>
	<div>
	<input type="File">
	</div>
	<div>
		<button type="submit">글 입력 완료</button>
	</div>
	<a href="/board/list">목록</a>
</form>
</body>
</html>