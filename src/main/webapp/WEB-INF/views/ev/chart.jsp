<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>



<!-- Chart.js 라이브러리 추가 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<!-- Chart.js DataLabels Plugin -->
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>

</head>
<body>
<%@ include file="../part/part_header.jsp" %>

<div class="container">
   <div>
      <h3>충전소 갯수 그래프</h3>
   </div>
   
   <div>
      <button id="btn_getStationCountByDistrict">행정구역별 충전소 갯수</button>
      <button id="btn_getStationCountByBusiNm">운영기관별 충전소 갯수 top20</button>
      <button id="btn_getChgerTypeCountByChgerType">급속, 완속 충전기 비율</button>
      <button id="btn_getUserCateCountByUserCategory">사용자 유형별 사용횟수</button>
      <button id="btn_getYoilChgerCountByBaseDayOfTheWeek">요일별 충전 횟수</button>
   </div>
   
   <div>
      <!-- 그래프를 표시할 canvas 요소 -->
       <canvas id="stationChart" width="500" height="200"></canvas>
       <canvas id="stationChart2" width="500" height="200"></canvas>
       <canvas id="stationChart3" width="1000" height="600"></canvas>
       <canvas id="stationChart4" width="500" height="200"></canvas>
 	   <canvas id="stationChart5" width="500" height="200"></canvas>
       
   </div>
   
   



</div>


<script type="text/javascript" src="/js/chartService.js"></script>
</body>
</html>