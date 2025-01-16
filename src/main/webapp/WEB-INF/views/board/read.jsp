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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	type="text/javascript"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>

	<%@ include file="../part/part_header.jsp"%>

	<div class="container">
		<div>
			<h3>게시글 자세히 보기</h3>
		</div>

		<div>
			<form action="/board/delete" method="post" id="board_delete_service">
				<input type="hidden" name="id" value="${boardResponse.id}" /> <input
					type="hidden" name="username" value="${userEntity.username}">
				<table class="table table-bordered">
					<tr>
						<th>글번호</th>
						<td>${boardResponse.id}</td>
						<th>작성일</th>
						<td>${boardResponse.createDate}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${boardResponse.title}</td>
						<th>작성자</th>
						<td>${boardResponse.writer}</td>
					</tr>
					<tr>
						<td colspan="4">
							<p>${boardResponse.content}</p>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div class="images">
								<c:forEach items="${fileList}" var="dto">
									<img src="/board/imgDisplay?fullFileName=${dto.filename}"
										width="100px" height="100px">
								</c:forEach>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div>
			<a href="${criteria.page}" id="toBoardList">목록</a> <a
				href="/board/update/${boardResponse.id}">수정</a> <a
				id=delete_board_botton href="#">삭제</a>
		</div>

		<hr>
		<!-- 댓글 영역 -->
		<div>
			<h5>댓글 목록</h5>
			<div id="board_read_show_reply_list"></div>
		</div>

		<div class="container">
			<h5>댓글</h5>
			<!-- 댓글 작성 -->
			<form action="/replies/" method="post" id="replyForm"
				enctype="application/json">
				<input id="ttt" type="hidden" name="boardId"
					value="${boardResponse.id}" />

				<!-- 로그인해야만 댓글작성 할 수 있도록 바꾸기 -->
				<div>
					<c:if test="${empty userEntity}">
						<input type="text" id="replyWriter" class="form-control"
							placeholder="작성자" required />
					</c:if>
					<c:if test="${not empty userEntity}">
						<input type="text" id="replyWriter" class="form-control"
							readonly="readonly" value="${userEntity.username}" />
					</c:if>
				</div>

				<div>
					<textarea id="replyContent" class="form-control" rows="3"
						placeholder="댓글 내용을 입력해 주세요" required></textarea>
				</div>
				<button id="board_read_reply_insert" type="button"
					class="btn btn-primary">댓글 작성</button>
			</form>
		</div>
	</div>
<!-- ---------------------------------------------------- -->
		<div class=" d-flex justify-content-center" id="reply_pagenation">


		</div>
<!-- --------------------------------------------------------------------------- -->

	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/boardService.js"></script>
	<script type="text/javascript" src="/js/replyService.js"></script>
	<script type="text/javascript">

	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");
	
	// 글 목록 페이지로 이동
	   $("#toBoardList").click(function(event){
	      event.preventDefault();
	      let page = $(this).attr("href");
	      
	      let form = $("<form>").attr("action", "/board/list").append(getHiddenTag("page", page));
	      if (type != null && keyword != null) {
	         form.append(getHiddenTag("type", type));
	         form.append(getHiddenTag("keyword", keyword));
	      }
	      form.appendTo("body").submit();
	      
	   });
</script>
</body>
</html>
