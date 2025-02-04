<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 | 제주도 전기차 충전소</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../part/part_header.jsp" %>

   <div class="container">
      <div class="login-wrapper">
         <div class="login-image">
            <div class="image-overlay">
               <div class="image-text">
                  <h3>제주도 전기차 충전소</h3>
                  <p>친환경 제주도 여행의 시작<br>편리한 전기차 충전소 찾기</p>
               </div>
            </div>
         </div>
         
         <div class="login-container">
            <h2 class="login-title">로그인</h2>
            <form action="/auth/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            
               <div class="form-group">
                  <label for="id">아이디</label>
                  <input type="text" class="form-control" id="id" name="username" required>
               </div>
               <div class="form-group">
                  <label for="pw">비밀번호</label>
                  <input type="password" class="form-control" id="pw" name="password" required>
               </div>
               <button type="submit" class="login-btn">로그인</button>
               
               <div class="login-links">
                  <a href="/auth/joinForm">회원가입</a>
               </div>
            </form>
            
            <a href="/oauth2/authorization/naver">
			    <button type="button" class="login-btn">네이버 로그인</button>
			</a>
			
         </div>
      </div>
   </div>

</body>
</html>