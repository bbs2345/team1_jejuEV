<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../part/part_header.jsp" %>
<div class="container">
	<div>
		<h3>전기차 충전소 목록</h3>
	</div>

	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>번호</td>
					<td>충전소 이름</td> 
					<td>주소</td> 
					<td>이용가능시간</td> 
					<td>주차료 무료</td> 
		
				</tr>
			</thead>
			<tbody class="board_list_boardList">
				<c:forEach items="${evList}" var="dto">
					<tr>
						<td>${dto.id}</td>
						<td>${dto.statNm}</td>
						<td>${dto.addr}</td>
						<td>${dto.useTime}</td>
						<td>${dto.parkingFree}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- 검색 -->
	<div class="d-flex justify-content-center">
		<form method="get">
			<select name="type">
				<option value="stat_nm" ${criteria.type == 'stat_nm' ? 'selected':''}>이름</option>
				<option value="addr" ${criteria.type == 'addr' ? 'selected':''}>주소</option>
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


<!-- <div> -->
<!-- 	<button id="getChargerInfo" type="button">충전소정보 가져오기</button> -->
<!-- </div> -->

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
	
	let page = $("input[name='page']");
	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");

	//페이지 이동
	$("#pagination").find("a").click(function(event) {
		event.preventDefault();
	
		let form = $("<form>").attr("action", "/evcharger/list").attr("method", "get").append(getHiddenTag("page", $(this).attr("href")));
	
		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
	
		form.appendTo("body").submit();
	
	});

// 	$("#getChargerInfo").click(function(){
// 		console.log("클릭!");
		
// 		let apiKey = "phPpD0+9/vesTTLZXECmqPTFD3L6f9Rmpnshwb+PHJL/2leup2J2OEFtIEVrnuhoh5RRd2KJ6oKVsa0e1reGhA=="
// 		let apiKey2 = "phPpD0%2B9%2FvesTTLZXECmqPTFD3L6f9Rmpnshwb%2BPHJL%2F2leup2J2OEFtIEVrnuhoh5RRd2KJ6oKVsa0e1reGhA%3D%3D"
		
// 		$.ajax({
// 			url : "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo",
// 			type : "get",
// 			data : {
// 				"serviceKey" : apiKey,
// 				"pageNo" : 1,
// 				"numOfRows" : 10,
// 				"zcode" : 50,
// 				"dataType" : "JSON"
// 			},
// 			dataType : "text",
// 			success : function(result){
// 				let obj = JSON.parse(result);
// 				let items = obj["items"];
// 				let item = items["item"];
				
// 				console.log(item);
				
// 				let tag = "";
// 				for(i of item) {
// 					tag += "<tr><td>"+i["statNm"]+"</td></tr>";
// 				};
// 				console.log(tag);
// 				$("tbody").html(tag);
// 			}
// 		});
		
// 	});
</script>
</body>
</html>