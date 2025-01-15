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
<div>
	<h3>전기차 충전소 목록</h3>
</div>

<div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>충전소 이름</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

</div>

<div>
	<button id="getChargerInfo" type="button">충전소정보 가져오기</button>
<!-- 	<button id="test" type="button">테스트</button> -->
</div>

<script type="text/javascript">

// $("#test").click(function(){
// 	console.log("클릭!");
	
// 	let apiKey = "phPpD0+9/vesTTLZXECmqPTFD3L6f9Rmpnshwb+PHJL/2leup2J2OEFtIEVrnuhoh5RRd2KJ6oKVsa0e1reGhA=="
// 	let apiKey2 = "phPpD0%2B9%2FvesTTLZXECmqPTFD3L6f9Rmpnshwb%2BPHJL%2F2leup2J2OEFtIEVrnuhoh5RRd2KJ6oKVsa0e1reGhA%3D%3D"
	
// 	$.ajax({
// 		url : "http://apis.data.go.kr/B552061/frequentzoneOldman/getRestFrequentzoneOldman",
// 		type : "get",
// 		data : {
// 			"serviceKey" : apiKey2,
// 			"searchYearCd" : 2022,
// 			"siDo" : 11,
// 			"guGun" : 680,
// 			"numOfRows" : 10,
// 			"pageNo" : 1
// 		},
// 		dataType : "text",
// 		success : function(result){
// 			let obj = JSON.parse(result);
// 			console.log(obj);
			
// 			/*
// 			let items = obj["items"];
// 			let item = items["item"];
			
// 			console.log(item);
			
// 			let tag = "";
// 			for(i of item) {
// 				tag += "<tr><td>"+i["statNm"]+"</td></tr>";
// 			};
// 			console.log(tag);
// 			$("tbody").html(tag);
// 			*/
// 		}
// 	});
	
// });

$("#getChargerInfo").click(function(){
	console.log("클릭!");
	
	let apiKey = "phPpD0+9/vesTTLZXECmqPTFD3L6f9Rmpnshwb+PHJL/2leup2J2OEFtIEVrnuhoh5RRd2KJ6oKVsa0e1reGhA=="
	let apiKey2 = "phPpD0%2B9%2FvesTTLZXECmqPTFD3L6f9Rmpnshwb%2BPHJL%2F2leup2J2OEFtIEVrnuhoh5RRd2KJ6oKVsa0e1reGhA%3D%3D"
	
	$.ajax({
		url : "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo",
		type : "get",
		data : {
			"serviceKey" : apiKey,
			"pageNo" : 1,
			"numOfRows" : 10,
			"zcode" : 50,
			"dataType" : "JSON"
		},
		dataType : "text",
		success : function(result){
			let obj = JSON.parse(result);
			let items = obj["items"];
			let item = items["item"];
			
			console.log(item);
			
			let tag = "";
			for(i of item) {
				tag += "<tr><td>"+i["statNm"]+"</td></tr>";
			};
			console.log(tag);
			$("tbody").html(tag);
		}
	});
	
});
</script>
</body>
</html>