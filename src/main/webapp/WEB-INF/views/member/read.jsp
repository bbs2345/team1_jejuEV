<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
	<div>
		<h3>회원정보 상세보기</h3>
	</div>
	
	<div>
		<div>
			<img src="/member/imgDisplay?fullFileName=${memberResponse.profileImage}" width="100" height="100">
		</div>
		<div>
			아이디 : ${memberResponse.username}
		</div>
		<div>
			이름 : ${memberResponse.name}
		</div>
	</div>
	<a href="/">메인페이지</a>
	<a href="/admin/memberList?page=${criteria.page}&type=${criteria.type}&keyword=${criteria.keyword}">목록</a>
	<a href="/member/updateForm/${memberResponse.username}">회원정보 수정</a>
	<a id="member_read_deleteMember" href="${memberResponse.username}">회원탈퇴</a>
</div>

<script type="text/javascript" src="/js/memberService.js"></script>
</body>
</html>