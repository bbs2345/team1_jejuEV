$(function() {

    // 차트 생성 함수
    function createChart(chartId, labels, data, label, backgroundColor, borderColor, chartType = 'bar') {
        let ctx = $(chartId)[0].getContext('2d');
        new Chart(ctx, {
            type: chartType,
            data: {
                labels: labels,
                datasets: [{
                    label: label,
                    data: data,
                    backgroundColor: backgroundColor,
                    borderColor: borderColor,
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                plugins: {
                    datalabels: {
                        display: true,
                        color: '#000',
                        font: {
                            weight: 'bold',
                            size: 12
                        },
                        anchor: 'end',
                        align: 'top',
                        formatter: function(value) {
                            return value;
                        }
                    }
                }
            },
            plugins: [ChartDataLabels]
        });
    }

    // 행정구역별 충전소 갯수
    $("#btn_getStationCountByDistrict").click(function() {
        $.ajax({
            url: "/ev/getStationCountByDistrict",
            type: "GET",
            dataType: "text",
            success: function(result) {
                let map = JSON.parse(result);
                let districtNames = Object.keys(map);
                let stationCounts = Object.values(map);
                createChart('#stationChart', districtNames, stationCounts, '행정구역별 충전소 갯수', 'rgba(75, 192, 192, 0.2)', 'rgba(75, 192, 192, 1)');
            }
        });
    });

    // 운영기관별 충전소 갯수 상위 20군데
    $("#btn_getStationCountByBusiNm").click(function() {
        $.ajax({
            url: "/ev/getStationCountByBusiNm",
            type: "GET",
            dataType: "json",
            success: function(result) {
                let map = result;
                let busiNames = Object.keys(map);
                let stationCounts = Object.values(map);
                createChart('#stationChart2', busiNames, stationCounts, '운영기관별 충전소 갯수 top20', 'rgba(75, 192, 192, 0.2)', 'rgba(75, 192, 192, 1)');
            }
        });
    });

    // 급속, 완속 충전기 비율
    $("#btn_getChgerTypeCountByChgerType").click(function() {
        $.ajax({
            url: "/ev/getChgerTypeCountByChgerType",
            type: "GET",
            dataType: "json",
            success: function(result) {
                let slowCount = result.slowRatio;
                let fastCount = result.fastRatio;
                let slowRatio = Math.round(slowCount);
                let fastRatio = Math.round(fastCount);
                let ctx = $('#stationChart3')[0].getContext('2d');
                new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: ['완속', '급속'],
                        datasets: [{
                            data: [slowRatio, fastRatio],
                            backgroundColor: ['#FF6384', '#36A2EB'],
                            hoverBackgroundColor: ['#FF4C5B', '#2F8DC1']
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                position: 'top'
                            },
                            tooltip: {
                                callbacks: {
                                    label: function(tooltipItem) {
                                        return tooltipItem.label + ': ' + tooltipItem.raw + '%';
                                    }
                                }
                            },
                            datalabels: {
                                display: true,
                                color: '#000',
                                font: { weight: 'bold', size: 14 },
                                formatter: function(value) {
                                    return value + '%';
                                }
                            }
                        }
                    },
                    plugins: [ChartDataLabels]
                });
            }
        });
    });

    // 사용자 유형별 사용횟수 (도민, 관광객)
    $("#btn_getUserCateCountByUserCategory").click(function() {
        $.ajax({
            url: "/ev/getuserCateCountByUserCate",
            type: "GET",
            dataType: "json",
            success: function(result) {
                let domin = result.도민;
                let tourist = result.관광객;
                createChart('#stationChart4', ['도민', '관광객'], [domin, tourist], '사용자 수', ['#FF6384', '#36A2EB'], ['#FF6384', '#36A2EB']);
            }
        });
    });
	
	

    // 요일별 충전 횟수
    $("#btn_getYoilChgerCountByBaseDayOfTheWeek").click(function() {
        $.ajax({
            url: "/ev/getYoilChgerCountByBaseDayOfTheWeek",
            type: "GET",
            dataType: "json",
            success: function(result) {
                let chargingData = result;
                let days = Object.keys(chargingData);
                let counts = Object.values(chargingData);
                createChart('#stationChart5', days, counts, '요일별 충전 횟수', 'rgba(75, 192, 192, 0.2)', 'rgba(75, 192, 192, 1)');
            }
        });
    });

});
