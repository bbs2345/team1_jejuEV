<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제주도 전기차 충전소</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
body {
    background: linear-gradient(rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 0.2)),
                url('/images/jeju-landscape.jpg') center/cover fixed;
    color: #333;
    font-family: 'Noto Sans KR', sans-serif;
}

.main-container {
    padding: 50px 20px;
    margin-top: 30px;
}

.welcome-section {
    background: rgba(255, 255, 255, 0.9);
    border-radius: 15px;
    padding: 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    margin-bottom: 30px;
    border-left: 5px solid #FF9F00;
}

.welcome-section h2 {
    color: #0066cc;
    font-weight: 700;
    margin-bottom: 20px;
    font-size: 2.5em;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
}

.greeting-text {
    font-size: 1.2em;
    line-height: 1.6;
    color: #555;
    padding: 20px;
    background: rgba(101, 199, 247, 0.1);
    border-radius: 10px;
    margin-top: 20px;
}

.feature-section {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
    margin-top: 40px;
}

.feature-card {
    background: white;
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
    transition: transform 0.3s ease;
}

.feature-card:hover {
    transform: translateY(-5px);
}

.feature-card h3 {
    color: #FF9F00;
    margin-bottom: 15px;
    font-size: 1.5em;
}

.feature-card p {
    color: #666;
    line-height: 1.5;
}

.cta-button {
    display: inline-block;
    padding: 12px 30px;
    background: #FF9F00;
    color: white;
    border-radius: 25px;
    text-decoration: none;
    margin-top: 20px;
    transition: all 0.3s ease;
}

.cta-button:hover {
    background: #0066cc;
    transform: scale(1.05);
    color: white;
}

@media (max-width: 768px) {
    .welcome-section h2 {
        font-size: 2em;
    }
    
    .greeting-text {
        font-size: 1.1em;
    }
    
    .feature-section {
        grid-template-columns: 1fr;
    }
}
</style>

</head>
<body>
<%@ include file="part/part_header.jsp" %>

<div class="container main-container">
    <div class="welcome-section">
        <h2><spring:message code="main" text="제주도 전기차 충전소"/></h2>
        <div class="greeting-text">
            <spring:message code="main.greeting" arguments="${name}" text="쉽고 빠르게 전기차 충전소를 찾고, 제주 여행을 더욱 편리하게 즐겨보세요! 실시간 정보와 유용한 팁을 제공합니다. ${name}!"/>
        </div>
    </div>

    <div class="feature-section">
        <div class="feature-card">
            <h3>전기차 충전소 찾기</h3>
            <p>제주도 내 모든 전기차 충전소의 실시간 위치와 사용 가능 여부를 확인하세요.</p>
            <a href="/ev/list" class="cta-button">충전소 찾기</a>
        </div>
        
        <div class="feature-card">
            <h3>관광 정보</h3>
            <p>제주도의 아름다운 관광지와 맛집 정보를 확인하고 여행 계획을 세워보세요.</p>
            <a href="/tourist" class="cta-button">더 알아보기</a>
        </div>
        
        <div class="feature-card">
            <h3>커뮤니티</h3>
            <p>다른 여행자들과 정보를 공유하고 소통하세요.</p>
            <a href="/board/list" class="cta-button">게시판 가기</a>
        </div>
    </div>
</div>

</body>
</html>