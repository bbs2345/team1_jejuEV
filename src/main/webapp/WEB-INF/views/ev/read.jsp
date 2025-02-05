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
<link href="/css/evread.css" rel="stylesheet">

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
    </style>

</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container">
	<h2>${stationEntity.statNm}</h2>
	<input type="hidden" name="statId" value="${stationEntity.statId}">
	<a id="backList">목록</a>
	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>충전기ID</th>
					<th>충전기타입</th>
					<th>충전기상태</th> 
					<th>상태갱신일시</th> 
					<th>마지막 충전시작일시</th> 
					<th>마지막 충전종료일시</th> 
					<th>충전중 시작일시</th> 
					<th>충전파워타입</th> 
					<th>충전용량</th> 
					<th>충전방식</th> 
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>

    <!-- 지도 표시 영역 -->
    <div id="map"></div>
    <input id="ev_read_hidden_lat" type="hidden" name="lat" value="${stationEntity.lat}">   
    <input id="ev_read_hidden_lng" type="hidden" name="lng" value="${stationEntity.lng}"> 
    <input id="ev_read_hidden_statNm" type="hidden" name="statNm" value="${stationEntity.statNm}"> 
    
</div>

<script type="text/javascript" src="/js/evchargerstation.js"></script>
<script type="text/javascript" src="/js/evcharger.js"></script>
</body>
</html>