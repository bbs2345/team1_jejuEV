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
		<h3><spring:message code="memberList" /></h3>
	</div>
	
	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th><spring:message code="member.number" /></th>
					<th><spring:message code="member.username" /></th>
					<th><spring:message code="member.name" /></th>
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
				<option value="username" ${criteria.type == 'username' ? 'selected':''}><spring:message code="member.username" /></option>
				<option value="name" ${criteria.type == 'name' ? 'selected':''}><spring:message code="member.name" /></option>
			</select>
			<input type="search" name="keyword" value="${criteria.keyword}" placeholder="<spring:message code="keyword" />">
			<button><spring:message code="search" /></button>
		</form>
	</div>
	
	<!-- 페이징 -->
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
	
	let page = getSearchParam("page");
	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");
	
	$("tbody").find("a").click(function(event){
		event.preventDefault();
		let username = $(this).attr("href");
		
		let form = $("<form>").attr("action", "/member/read/"+username).attr("method", "get").append(getHiddenTag("page", page));
		
		if(type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
		
		form.appendTo("body").submit();
		
	});
	
	$("#pagination").find("a").click(function(event){
		event.preventDefault();
		
		let form = $("<form>").attr("action", "/admin/memberList").attr("method", "get").append(getHiddenTag("page", $(this).attr("href")));
		
		if(type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
		
		form.appendTo("body").submit();
		
	});
</script>
</body>
</html>