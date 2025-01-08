<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	type="text/javascript"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<div>
			<h3>회원가입하기</h3>
		</div>

		<div>
			<form action="/member/insert" method="post">
				<div>
					아이디 : <input name="username">
					<button id="member_insertForm_checkId" type="button">중복체크</button>
					<span></span>
				</div>
				<div>
					비밀번호 : <input type="password" name="password"> <span></span>
				</div>
				<div>
					비밀번호 확인 : <input type="password" name="password2"> <span></span>
				</div>
				<div>
					이름 : <input name="name">
				</div>
				<button id="member_insertForm_submit" type="button">회원등록</button>
			</form>
		</div>
	</div>

<script type="text/javascript">
	
	function isOk() {
		if (username.val() == '') {
			alert("아이디를 입력하세요.");
			return false;
		}

		if (password.val() == '') {
			alert("비밀번호를 입력하세요.");
			return false;
		}

		if (password2.val() == '') {
			alert("비밀번호를 확인하세요.");
			return false;
		}

		if (!confirmPassword) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}

		if (name.val() == '') {
			alert("이름을 입력하세요.");
			return false;
		}

		if (!checkId) {
			alert("중복검사 하세요.");
			return false;
		}
		
		return true;
	}

	let username = $("input[name='username']");
	let password = $("input[name='password']");
	let password2 = $("input[name='password2']");
	let name = $("input[name='name']");

	let checkId = false;
	let confirmPassword = false;

	// 비밀번호 확인 input이벤트
	$("body").on("input", "input[type='password']", function() {
		confirmPassword = false;

		$("input[type='password']").next().removeAttr().text("");

		let passwordVal = $("input[name='password']").val();
		let password2Val = $("input[name='password2']").val();

		if (passwordVal == password2Val && passwordVal != '' && password2Val != '') {
			$("input[type='password']").next().attr("style", "color: blue").text("비밀번호 일치");
			confirmPassword = true;
		}
	});

	// 회원등록 버튼 클릭 이벤트
	$("#member_insertForm_submit").click(function() {
		if (isOk()) {
			$("form").submit();
		}
	});

	// input 이벤트
	$("body").on("input", "input[name='username']", function() {
		// 아이디 바꾸면 span태그 초기화 시키고 isOk false로, 중복체크버튼 다시 활성화
		$("#member_insertForm_checkId").next().removeAttr().text('');
		checkId = false;
		$("#member_insertForm_checkId").prop("disabled", false);
	});

	// 중복체크 버튼 클릭 이벤트
	$("#member_insertForm_checkId").click(function() {
		// name속성값이 username인 input태그
		let usernameValue = $("input[name='username']").val();

		// usernameValue가 비어있으면 알림창 띄우기
		if (usernameValue == '') {
			alert("아이디를 입력하세요.");
			return;
		}

		$.ajax({
			url : "/member/checkId",
			type : "post",
			data : {
				username : usernameValue
			},
			dataType : "text",
			success : function(result) {
				if (result == "ok") {
					// result가 ok일 때, span태그 style속성바꾸고, text로 '사용가능한 아이디'
					$("#member_insertForm_checkId").next().attr("style", "color: blue").text("사용가능한 아이디");
					checkId = true;
					$("#member_insertForm_checkId").prop("disabled", true); // 중복체크 버튼 비활성화 시킴
				} else {
					$("#member_insertForm_checkId").next().attr("style", "color: red").text("이미사용중인 아이디");
				}
			}
		});
	});
</script>
</body>
</html>