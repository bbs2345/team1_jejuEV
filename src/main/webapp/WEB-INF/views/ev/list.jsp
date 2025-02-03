<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="/css/evlist.css" rel="stylesheet">

<meta charset="UTF-8">
    <title>지도 표시</title>
    <!-- Leaflet CSS -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
    <!-- Leaflet JS -->
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
    <style>
        #map {
            width: 100%;
            height: 500px;
        }
        
		.si {
	    	float: right; /* 오른쪽으로 띄움 */
		}
	
		.si a {
	    	display: inline-block; /* 링크가 한 줄에 나란히 나오게 함 */
	    	margin-left: 10px; /* 링크 간 간격을 조정 */
		}
    </style>

</head>
<body>
<%@ include file="../part/part_header.jsp" %>
<div class="container">
	<div class="si">
		<a id="list_ten_btn" href="/ev/list?perPageContent=10">10개</a>
		<a id="list_ten_btn" href="/ev/list?perPageContent=20">20개</a>
		<a id="list_twn_btn" href="/ev/list?perPageContent=30">30개</a>
		<a href="제주시">제주시</a>
		<a href="서귀포시">서귀포시</a>
	</div>
	
	<div>
		<h3>전기차 충전소 목록</h3>  
	</div>

	        <div id="map"></div>
		    <c:forEach items="${stList}" var="modal">
			    <input type="hidden" name="lat" value="${modal.lat}">   
			    <input type="hidden" name="lng" value="${modal.lng}"> 
			    <input type="hidden" name="statNm" value="${modal.statNm}">
			    <input type="hidden" name="statId" value="${modal.statId}">
	        </c:forEach>
	<div>
		
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>충전소ID</td>
					<td>충전소 이름</td> 
					<td>주소</td> 
					<td>이용가능시간</td> 
					<td>주차료 무료</td> 
		
				</tr>
			</thead>
			<tbody class="board_list_boardList">
				<c:forEach items="${stList}" var="dto">
					<tr>
						<td>${dto.statId}</td>
						<td><a href="/ev/read/${dto.statId}">${dto.statNm}</a></td>
						<td>${dto.addr}</td>
						<td>${dto.useTime}</td>
						<td>
							<c:choose>
								<c:when test="${dto.parkingFree == 'Y'}">무료</c:when>
								<c:when test="${dto.parkingFree == 'N'}">유료</c:when>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- 검색 -->
	<div class="d-flex justify-content-center">
		<form id="ev_searchForm" method="get">
			<input type="hidden" name="page" value="${criteria.page}">
			<input type="hidden" name="perPageContent" value="${criteria.perPageContent}">
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
	<input type="hidden" name="perPageContent" value="${criteria.perPageContent}">
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
	
	
	<!-- 지도 표시 영역 -->
<!-- 	<div id="map"></div> -->
<%-- 		<c:forEach items="${evList}" var="modal"> --%>
<%-- 			<input type="hidden" name="lat" value="${modal.lat}">    --%>
<%-- 			<input type="hidden" name="lng" value="${modal.lng}">  --%>
<%-- 			<input type="hidden" name="statNm" value="${modal.statNm}"> --%>
<%-- 	    </c:forEach> --%>
	
	
	<!-- Button trigger modal -->
<!-- 	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> -->
<!-- 	  지도 보기 -->
<!-- 	</button> -->
	
<!-- 	<!-- Modal --> 
<!-- 	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 	  <div class="modal-dialog modal-lg"> -->
<!-- 	    <div class="modal-content"> -->
<!-- 	      <div class="modal-header"> -->
<!-- 	        <h1 class="modal-title fs-5" id="exampleModalLabel">지도 보기</h1> -->
<!-- 	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
<!-- 	      </div> -->
<!-- 	      <div class="modal-body"> -->
	        
<!-- 	      </div> -->
<!-- 	    </div> -->
<!-- 	  </div> -->
<!-- 	</div> -->
	

</div>



<!-- <div> -->
<!-- 	<button id="getChargerInfo" type="button">충전소정보 가져오기</button> -->
<!-- </div> -->

<script type="text/javascript" src="/js/evchargerstation.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">

	
	$(".si").find("a").eq("3").click(function(event){
		event.preventDefault();
		let keyword = $(this).attr("href");
		$("#ev_searchForm").find("select[name='type']").val("addr");
		$("#ev_searchForm").find("input[type='search']").val(keyword);
		$("#ev_searchForm").submit();
		
	});
	$(".si").find("a").eq("4").click(function(event){
		event.preventDefault();
		let keyword = $(this).attr("href");
		$("#ev_searchForm").find("select[name='type']").val("addr");
		$("#ev_searchForm").find("input[type='search']").val(keyword);
		$("#ev_searchForm").submit();
	});
	
	let page = $("input[name='page']");
	let perPageContent = $("input[name='perPageContent']").val();
	//let perPageContent = getSearchParam("perPageContent");
	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");

	//페이지 이동
	$("#pagination").find("a").click(function(event) {
		event.preventDefault();
	
		let form = $("<form>").attr("action", "/ev/list").attr("method", "get").append(getHiddenTag("page", $(this).attr("href"))).append(getHiddenTag("perPageContent", perPageContent));
		
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