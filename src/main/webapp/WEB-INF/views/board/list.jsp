<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../part/part_header.jsp"%>

<div class="container">
	<div>
		<h3>글 목록</h3>
	</div>
	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
		
				</tr>
			</thead>
			<tbody class="board_list_boardList">
				<c:forEach items="${boardList}" var="dto">
					<tr>
						<td>${dto.id}</td>
						<td><a href="${dto.id}">${dto.title}</a>  [${dto.replyList.size()}]</td>
						<td>${dto.writer} | ${dto.user.username} | ${dto.user.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- 검색 -->
	<div class="d-flex justify-content-center">
		<form method="get">
			<select name="type">
				<option value="title" ${criteria.type == 'title' ? 'selected':''}>제목</option>
				<option value="writer" ${criteria.type == 'writer' ? 'selected':''}>작성자</option>
			</select>
			<input type="search" name="keyword" value="${criteria.keyword}" placeholder="키워드를 입력하세요.">
			<button>검색</button>
		</form>
	</div>
	
	<!-- 페이징 -->
	<input type="hidden" name="page" value="${criteria.page}">
	<div id="pagination" class="d-flex justify-content-center">
		<ul class="pagination">
			<c:if test="${pagination.prev}">
				<li class="page-item"><a class="page-link" href="${pagination.startPage - 1}">Prev</a></li>
			</c:if>
			
			<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="pageNum">
				<li class="page-item ${criteria.page == pageNum ? 'active' : ''}"><a class="page-link" href="${pageNum}">${pageNum}</a></li>
			</c:forEach>
			 
			 <c:if test="${pagination.next}">
				<li class="page-item"><a class="page-link" href="${pagination.endPage + 1}">Next</a></li>
			</c:if>
		</ul>
	</div>
	
	<div>
		<a href="/board/insert" class="btn btn-primary">글쓰기</a>
	</div>

</div>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">

	let page = $("input[name='page']");
	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");

	// 상세페이지로 이동
	$(".board_list_boardList").find("a").click(function(event) {
		event.preventDefault();
		let bId = $(this).attr("href");
	
		let form = $("<form>").attr("action", "/board/read/" + bId).attr("method", "get").append(getHiddenTag("page", page.val()));
	
		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
	
		form.appendTo("body").submit();
	
	});

	// 페이지 이동
	$("#pagination").find("a").click(function(event) {
		event.preventDefault();
	
		let form = $("<form>").attr("action", "/board/list").attr("method", "get").append(getHiddenTag("page", $(this).attr("href")));
	
		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
	
		form.appendTo("body").submit();
	
	});

</script>
</body>
</html>