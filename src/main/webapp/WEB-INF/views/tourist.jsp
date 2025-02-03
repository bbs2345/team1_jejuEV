<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제주도 관광지 | 전기차 충전소</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="/css/style.css">

<style>
    iframe {
        width: 100%;
        height: 90vh;
        border: none;
    }

    footer {
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100%;
        background-color: #f1f1f1; /* 배경색 */
        text-align: center;
        padding: 10px;
        z-index: 1000; /* 다른 콘텐츠 위에 표시 */
    }
</style>

</head>
<body>
<%@ include file="part/part_header.jsp" %>

<!-- iframe을 통해 외부 웹페이지를 표시 -->
<iframe src="https://data.ijto.or.kr/content/CO2023122815193501JLM74"></iframe>

<footer>
    <p>출처 : 제주관광 빅데이터서비스 플랫폼 (https://data.ijto.or.kr/content/CO2023122815193501JLM74) </p>
</footer>

</body>
</html> 