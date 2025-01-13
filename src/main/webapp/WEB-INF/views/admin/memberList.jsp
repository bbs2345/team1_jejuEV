<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
	<div>
		<h3>회원목록</h3>
	</div>
	
	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${memberList}" var="dto" varStatus="status">
					<tr>
						<td>${status.count + (criteria.page-1) * criteria.perPageContent}</td>
						<td><a href="${dto.username}">${dto.username}</a></td>
						<td>${dto.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- 검색 -->
	<div class="d-flex justify-content-center">
		<form method="get">
			<select name="type">
				<option value="username" ${criteria.type == 'username' ? 'selected':''}>아이디</option>
				<option value="name" ${criteria.type == 'name' ? 'selected':''}>이름</option>
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
	
</div>


<script type="text/javascript">
	/* perPageContent */

	function getSearchParam(key){
		return new URLSearchParams(location.search).get(key);
	}

	function getHiddenTag(name, value) {
	    return $("<input/>",{type:"hidden", "name":name, "value" : value});
	    
	}
	
	let page = $("input[name='page']");
	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");
	
	// 상세페이지로 이동
	$("tbody").find("a").click(function(event){
		event.preventDefault();
		let username = $(this).attr("href");
		
		let form = $("<form>").attr("action", "/member/read/"+username).attr("method", "get").append(getHiddenTag("page",page.val()));
		
		if(type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
		
		form.appendTo("body").submit();
		
	});
	
	// 페이지 이동
	$("#pagination").find("a").click(function(event){
		event.preventDefault();
		
		let form = $("<form>").attr("action", "/admin/memberList").attr("method", "get").append(getHiddenTag("page",$(this).attr("href")));
		
		if(type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
		
		form.appendTo("body").submit();
		
	});
</script>
</body>
</html>