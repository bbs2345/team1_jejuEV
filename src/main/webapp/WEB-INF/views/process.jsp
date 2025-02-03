<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로젝트 개요</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
    .process-container {
        max-width: 1000px;
        margin: 50px auto;
        padding: 0 10px;
        background: rgba(219, 221, 221, 0.95);
    }

    .process-card {
        background: rgba(255, 255, 255, 0.95);
        border-radius: 20px;
        box-shadow: 0 10px 30px rgba(0, 102, 204, 0.1);
        overflow: hidden;
        margin-bottom: 30px;
        border: 1px solid rgba(0, 102, 204, 0.1);
    }

    .process-header {
        background: linear-gradient(135deg, #3DB7CC, #1CA4B8);
        color: white;
        padding: 50px;
        text-align: center;
        position: relative;
        background-image: url('/images/wave_up.png');
        background-size: cover;
        background-blend-mode: overlay;
        animation: waveAnimation 20s linear infinite;
    }

    .process-header::after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        height: 5px;
        background: linear-gradient(to right, #FF9F00, #FFB700);
    }

    .process-header h1 {
        font-size: 2.5em;
        margin-bottom: 20px;
        font-weight: 700;
        text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
    }

    .process-content {
        padding: 40px;
        line-height: 1.8;
    }

    .section {
        margin-bottom: 40px;
        padding: 30px;
        background: #fff;
        border-radius: 15px;
        border-left: 5px solid #1CA4B8;
        transition: all 0.3s ease;
        position: relative;
        overflow: hidden;
    }

    .section::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: url('/images/jeju-symbol.png') no-repeat right bottom;
        background-size: 100px;
        opacity: 0.1;
    }

    .section:hover {
        transform: translateX(10px);
        background: #f8ffff;
    }

    .section h2 {
        color: #1CA4B8;
        font-size: 1.8em;
        margin-bottom: 20px;
        font-weight: 600;
    }

    .point-list {
        list-style: none;
        padding: 0;
    }

    .point-list li {
        margin-bottom: 15px;
        padding-left: 25px;
        position: relative;
    }

    .point-list li::before {
        content: '🌊';
        color: #1CA4B8;
        font-size: 1.2em;
        position: absolute;
        left: 0;
        top: -2px;
    }

    .highlight-box {
        background: linear-gradient(135deg, #1CA4B8, #3DB7CC);
        color: white;
        padding: 40px;
        border-radius: 15px;
        margin: 30px 0;
        position: relative;
        overflow: hidden;
    }

    .highlight-box::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: url('/images/dolharubang.png') no-repeat right bottom;
        background-size: 150px;
        opacity: 0.1;
    }

    .highlight-box h3 {
        color: #FFB700;
        margin-bottom: 20px;
        font-size: 1.8em;
        text-shadow: 1px 1px 2px rgba(0,0,0,0.2);
    }

    .value-points {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 20px;
        margin-top: 30px;
    }

    .value-point {
        background: rgba(255, 255, 255, 0.15);
        padding: 20px;
        border-radius: 10px;
        backdrop-filter: blur(5px);
        transition: all 0.3s ease;
        border: 1px solid rgba(255, 255, 255, 0.2);
    }

    .value-point:hover {
        background: rgba(255, 255, 255, 0.25);
        transform: translateY(-5px);
    }

    @keyframes waveAnimation {
        0% { background-position: 0 0; }
        100% { background-position: 100% 0; }
    }

    @media (max-width: 768px) {
        .process-header {
            padding: 30px 20px;
        }

        .process-content {
            padding: 20px;
        }

        .section {
            padding: 20px;
        }
    }
    </style>
</head>
<body>
    <%@ include file="part/part_header.jsp" %>
    
    <div class="process-container">
        <div class="process-card">
            <div class="process-header">
                <h2>제주도 전기자동차 충전소 정보 시스템 개발 배경</h2>
            
            </div>
            
            <div class="process-content">
                <div class="section">
                    <p>제주도는 2030년까지 탄소 없는 섬을 목표로 하고 있으며, 
                    전기자동차 보급률이 전국에서 가장 높은 지역입니다. 하지만 이러한 발전 속에서도 
                    몇 가지 현실적인 문제점들이 존재합니다.</p>
                </div>

                <div class="section">
                    <h2>1. 증가하는 관광객과 전기차 수요</h2>
                    <ul class="point-list">
                        <li>연간 1,500만 명 이상이 방문하는 관광지로서, 렌터카 수요가 매우 높음</li>
                        <li>제주도의 전기차 렌터카 비중이 지속적으로 증가 추세</li>
                        <li>관광객들의 전기차 이용 경험 부족으로 인한 불편 발생</li>
                    </ul>
                </div>

                <div class="section">
                    <h2>2. 충전 인프라 정보 접근성 문제</h2>
                    <ul class="point-list">
                        <li>충전소 위치 정보가 여러 플랫폼에 분산되어 있음</li>
                        <li>실시간 충전기 상태 확인의 어려움</li>
                        <li>주변 관광지와 연계된 충전소 정보 부족</li>
                    </ul>
                </div>

                <div class="section">
                    <h2>3. 현지 특성을 반영한 서비스 필요성</h2>
                    <ul class="point-list">
                        <li>제주도의 독특한 지리적 특성과 관광 동선 고려 필요</li>
                        <li>도내 주요 관광지와 연계된 충전 계획 수립 필요</li>
                    </ul>
                </div>

                <div class="highlight-box">
                    <h3>차별화된 가치 제공</h3>
                    <div class="value-points">
                        <div class="value-point">관광지 연계 충전소 정보 확인 시스템</div>
                        <div class="value-point">실시간 충전기 상태 모니터링</div>
                        <div class="value-point">사용자 경험 기반의 리뷰 및 평가 시스템</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html> 