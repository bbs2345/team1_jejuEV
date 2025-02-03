<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set value="${sessionScope.userEntity}" var="userEntity"/>
<c:set value="${applicationScope.cateList}" var="cateList"/>

 <sec:authorize access="isAuthenticated( )">
  <sec:authentication property="principal" var="principal"/>
</sec:authorize>

 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>


<div class="container-fluid">
	<!-- 로그인 -->
	<div class="d-flex justify-content-between">
		
		
		
		
	</div>
	<!-- 로그인 -->

	<div class="d-flex">
	
<<<<<<< HEAD
		<!-- 로고 영역 -->
		<div class="p-2 logo-container">
			<a href="/"><img src="/images/logo2.png" width="100px" height="100px" alt="로고"></a>
		</div>
		
		<!-- 메뉴 -->
		<div class="flex-grow-1">
			<ul class="d-flex justify-content-center main-menu">
			
				<li class="nav-item"><a class="nav-link" href="/">메인</a></li>


				<li class="nav-item"><a href="/process">개요</a> 
				<!-- 서브메뉴 -->
					<div class="submenu">
						<a class="nav-link" href="/process">개발 과정</a>
					</div>
				</li>
				
				<li class="nav-item"><a href="/tourist">제주 관광지</a>
					<div class="submenu">
						<a href="/tourist">제주 관광지</a>
					</div>
				</li>

				<li class="nav-item"><a class="nav-link" href="/ev/list">전기차 충전소</a>
				<!-- 서브메뉴 -->
					<div class="submenu">
						<a href="#">서브메뉴 5-1</a> 
						<a href="#">서브메뉴 5-2</a>
					</div></li>

				<li class="nav-item"><a class="nav-link" href="/board/list">게시판</a>
				<!-- 서브메뉴 -->
					<div class="submenu">
						<c:forEach items="${cateList}" var="cate">
							<a class="nav-link" href="/board/${cate.cid}/list">${cate.cname}</a>
						</c:forEach>
					</div>
				</li>
				
				<c:if test="${principal.role == 'ROLE_ADMIN'}">
					<li class="nav-item"><a class="nav-link" href="/admin/home">관리자</a>
						<div  class="submenu">
								<a href="/admin/userList">회원목록</a>
								<a href="/admin/home">관리자페이지</a>
						</div>
					</li>
				</c:if>
			</ul>
		</div>

		<!-- 로그인/회원가입 버튼 영역 -->
		
		<div class="p-2 d-flex justify-content-center align-items-center auth-buttons ml-auto">
			<c:if test="${empty principal}">
				<a class="btn btn-outline-primary mx-2" href="/auth/loginForm">로그인</a>
				<a class="btn btn-outline-primary mx-2" href="/auth/joinForm">회원가입</a>
			</c:if>
			<c:if test="${not empty principal}">
				<span>${principal.name}님 환영합니다.</span>
				<a class="btn btn-outline-primary mx-2" href="/auth/logout">로그아웃</a>
				<a class="btn btn-outline-primary mx-2" href="/user/read/${principal.username}">회원정보</a>
			</c:if>
				
		</div>
		
		
<!-- 		<div class="p-2 d-flex justify-content-center align-items-center auth-buttons ml-auto"> -->
<%-- 			<c:if test="${empty userEntity}"> --%>
<!-- 				<a class="btn btn-outline-primary mx-2" href="/auth/loginForm">로그인</a> -->
<!-- 				<a class="btn btn-outline-primary mx-2" href="/auth/joinForm">회원가입</a> -->
<%-- 			</c:if> --%>
<%-- 			<c:if test="${not empty userEntity}"> --%>
<%-- 				<span>${userEntity.username}님 환영합니다.</span> --%>
<!-- 				<a class="btn btn-outline-primary mx-2" href="/auth/logout">로그아웃</a> -->
<!-- 				<a class="btn btn-outline-primary mx-2" -->
<%-- 					href="/user/read/${userEntity.username}">회원정보</a> --%>
<%-- 			</c:if> --%>
<!-- 		</div> -->
		
	</div>


<!-- 	<!-- 메뉴 -->
<!-- 	<nav class="navbar" style="background-color: #e3f2fd;"> -->
<!-- 		<ul class="nav"> -->
<!-- 			<li class="nav-item"><a class="nav-link" href="/">메인</a></li> -->
<!-- 			<li class="nav-item"><a class="nav-link" href="/ev/list">전기차 충전소</a></li> -->
<!-- 			<li class="nav-item"><a class="nav-link" href="/board/list">전체글보기</a></li> -->
<%-- 			<c:forEach items="${cateList}" var="cate"> --%>
<%-- 				<li class="nav-item"><a class="nav-link" href="/board/${cate.cid}/list">${cate.cname}</a></li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<!-- 	</nav> -->

<!-- </div> -->


=======
	<!-- 메뉴 -->
	<nav class="navbar" style="background-color: #e3f2fd;">
		<ul class="nav">
			<li class="nav-item"><a class="nav-link" href="/">메인</a></li>
			<li class="nav-item"><a class="nav-link" href="/ev/list">전기차 충전소</a></li>
			<li class="nav-item"><a class="nav-link" href="/ev/chart">전기차 충전소 그래프</a></li>
			<c:forEach items="${cateList}" var="cate">
				<li class="nav-item"><a class="nav-link" href="/board/${cate.cid}/list">${cate.cname}</a></li>
			</c:forEach>
		</ul>
	</nav>
	
>>>>>>> 6e4a589 (그래프 작업 완료)
</div>


<style>
/* 전체 컨테이너 스타일 */
.container-fluid {
    width: 90%;
    height: 100px;
    background: linear-gradient(rgba(0, 204, 255, 0.5), rgba(0, 153, 255, 0.5)),
                url('/images/jeju-background.jpg') center/cover;
    padding: 0;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

/* 로고 컨테이너 */
.logo-container {
    padding: 10px 10px;
}

.logo-container img {
    transition: transform 0.3s ease;
}

.logo-container img:hover {
    transform: scale(1.05);
}

/* 메인 메뉴 스타일 */
.main-menu {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}

.nav-item {
    list-style: none;
    flex-grow: 1;
    text-align: center;
    position: relative;
}

.nav-item > a {
    text-decoration: none;
    display: block;
    height: 100%;
    line-height: 100px;
    color: white;
    font-weight: bold;
    font-size: 1.1em;
    transition: all 0.3s ease;
    text-shadow: 1px 1px 2px rgba(0,0,0,0.2);
}

.nav-item > a:hover {
    color: #ffd700;
    transform: translateY(-2px);
}

/* 서브메뉴 스타일 */
.submenu {
    display: none;
    position: absolute;
    left: 0;
    top: 100%;
    width: 100%;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 0 0 10px 10px;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    padding: 15px 0;
    border-top: 3px solid #FF9F00;
    z-index: 1000;
}

.submenu a {
    text-decoration: none;
    display: block;
    padding: 10px 15px;
    color: #333;
    transition: all 0.3s ease;
    font-size: 0.95em;
}

.submenu a:hover {
    background-color: #FF9F00;
    color: white;
    transform: translateX(5px);
}

/* 로그인/회원가입 버튼 */
.auth-buttons .btn-outline-primary {
    color: white;
    border-color: white;
    border-radius: 20px;
    padding: 8px 20px;
    transition: all 0.3s ease;
    font-weight: 500;
}

.auth-buttons .btn-outline-primary:hover {
    background-color: #FF9F00;
    border-color: #FF9F00;
    color: white;
    transform: translateY(-2px);
}

/* 반응형 디자인 */
@media (max-width: 992px) {
    .nav-item > a {
        font-size: 1em;
        line-height: 80px;
    }
    
    .logo-container img {
        width: 150px;
        height: 75px;
    }
    
    .auth-buttons .btn-outline-primary {
        padding: 6px 15px;
        font-size: 0.9em;
    }
}

@media (max-width: 768px) {
    .nav-item > a {
        font-size: 0.9em;
        line-height: 60px;
    }
    
    .auth-buttons {
        flex-direction: column;
    }
    
    .auth-buttons .btn {
        margin: 5px 0;
    }
}

.container-fluid.scrolled {
    background: rgba(0, 102, 204, 0.95);
    transition: background 0.3s ease;
}
</style>


<script>
$(document).ready(function() {
    // 메뉴 항목에 마우스를 올리면 서브메뉴 보이기
    $('.nav-item').mouseenter(function() {
        $(this).find('.submenu').stop().slideDown(200);
    });

    // 메뉴 항목에서 마우스를 떼면 서브메뉴 숨기기
    $('.nav-item').mouseleave(function() {
        $(this).find('.submenu').stop().slideUp(200);
    });
    
    // 스크롤 시 헤더 스타일 변경
    $(window).scroll(function() {
        if ($(this).scrollTop() > 50) {
            $('.container-fluid').addClass('scrolled');
        } else {
            $('.container-fluid').removeClass('scrolled');
        }
    });
});
</script>

