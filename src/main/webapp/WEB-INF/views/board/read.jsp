<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 자세히 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<div>
			<h3>게시글 자세히 보기</h3>
		</div>
		<form action="/board/delete" method="post" id="board_delete_service">
			<input type="hidden" name="id" value="${boardResponse.id}" />

			<div>번호: ${boardResponse.id}</div>
			<div>작성자: ${boardResponse.writer}</div>
			<div>제목: ${boardResponse.title}</div>
			<div>내용</div>
			<div>
				<p>${boardResponse.content}</p>
			</div>



			<div class="images">
				<c:forEach items="${fileList}" var="dto">
					<img src="/board/imgDisplay?fullFileName=${dto.filename}"
						width="100px" height="100px">
				</c:forEach>
			</div>

			<div>
				<a href="/board/list">목록</a> <a
					href="/board/update/${boardResponse.id}">수정</a> <a
					id=delete_board_botton href="#">삭제</a>
			</div>
		</form>
		<hr>
	</div>

	
	
	<!-- 댓글 영역 -->

	<div class="container">
		<h5>댓글 목록</h5>
		<div id="board_read_show_reply_list">
	
		
		</div>
	</div>
	
	<div class="container">
		<h5>댓글</h5>
		<!-- 댓글 작성 -->
		<form action="/replies/" method="post" id="replyForm"
			enctype="application/json">
			<input id="ttt" type="hidden" name="boardId" value="${boardResponse.id}" />
			<div>
				<input type="text" id="replyWriter" class="form-control"
					placeholder="작성자" required />
			</div>
			<div>
				<textarea id="replyContent" class="form-control" rows="3"
					placeholder="댓글 내용을 입력해 주세요" required></textarea>
			</div>
			<button id="board_read_reply_insert" type="button">댓글 작성</button>
		</form>
	</div>

	

	<script type="text/javascript" src="/js/boardService.js"></script>
	<script type="text/javascript" src="/js/test1.js"></script>
 	<script type="text/javascript" src="/js/replyService.js"></script>



	
<script type="text/javascript">
	let count = 1;

	$("#qqq").click(function(){
		$(".dddd").append("<input name='test"+count+"' value='"+${boardResponse.id}+"'>");
		count = count +1;
	});


</script>
</body>
</html>
