console.log("boardService.js파일 불러옴.");

function makeImg(result) {
	let tag = `<img src="${result}" width="100" height="100">`;
	return tag;
}

function getReaction() {
	let bId = $("input[name='id']").val();

	$.ajax({
		url: `/boardReactions/${bId}`,
		type: "get",
		dataType: "text",
		success: function(map) {
			let obj = JSON.parse(map);
			$("#like-count").prev().attr("class", "bi-hand-thumbs-up");
			$("#dislike-count").prev().attr("class", "bi-hand-thumbs-down");
			for (el of obj.likes) {
				if (el.username == $("input[name='username']").val()) {
					$("#like-count").prev().attr("class", "bi-hand-thumbs-up-fill");
				}
			}
			for (el of obj.dislikes) {
				if (el.username == $("input[name='username']").val()) {
					$("#dislike-count").prev().attr("class", "bi-hand-thumbs-down-fill");
				}
			}
			$(".board_read_btns_reaction").find("#like-count").text(obj.likes.length);
			$(".board_read_btns_reaction").find("#dislike-count").text(obj.dislikes.length);

		}

	});


}

$(function() {

	getReaction();

	// 이미지 삭제 버튼 클릭 이벤트 처리
	$("#deleteImage").click(function(event) {
		event.preventDefault();

		let filename = $(this).attr("data-filename"); // 클릭한 이미지의 파일 이름
		let isDelete = confirm("정말 삭제할거니?");

		if (isDelete) {

			$.ajax({
				url: '/board/deleteBoardFile',
				type: "POST",
				dataType: "text",
				data: { filename: filename },
				headers: {
					"X-CSRF-TOKEN": $("#csrfToken").val()
				},
				success: function(response) {
					alert(response); // 서버에서 보내준 응답 처리
					location.reload(); // 페이지 새로 고침
				}
			});
		}

	});


	//보더 삭제 기능
	$("#delete_board_botton").click(function() {
		$("#board_delete_service").submit();
	})

	// 이미지 미리보기
	$("input[name='myfile']").change(function() {

		let reader = new FileReader();

		reader.readAsDataURL(event.target.files[0]);

		reader.onload = function(event) {
			let result = event.target.result;
			if (result.startsWith("data:image")) {
				let tag = makeImg(result);
				$(".preview").html(tag);

			} else {
				alert("이미지 파일만 올리세요.");
				$("input[name='myfile']").val('');
				$(".preview").find("img").remove();
			}

		}

	});

	//=========================================

	//로그아웃 된상태의 좋아요 버튼 붛가
	$(".empty_user_reaction-button").click(function() {
		alert("로그인 후 이용하세요");
	});

	// 좋아요/나빠요 버튼 클릭 이벤트 공통 처리
	$(".reaction-button").each(function() {
		$(this).click(function() {
			let reactionType = $(this).attr("data-reaction-type");
			let bId = $("input[name='id']").val();
			let username = $("input[name='username']").val();
			let csrfToken = $("#board_delete_service").find("input").eq(0).val();

			$.ajax({
				url: "/boardReactions/",
				type: "post",
				data: JSON.stringify({
					bId: bId,
					username: username,
					reactionType: reactionType
				}),
				contentType: "application/json",
				headers: {
					"X-CSRF-TOKEN": csrfToken,
					"X-Http-Method-Override": "POST"
				},
				dataType: "text",
				success: function(result) {
					getReaction();

				}
			});
		});

	});


	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");

	// 글 목록 페이지로 이동
	$("#toBoardList").click(function(event) {
		event.preventDefault();
		// 전체 URI 가져오기
		let uri = document.location.pathname; // /board/{id}/insert
		// '/' 기준으로 분리
		let parts = uri.split("/");
		// 배열의 두 번째 요소가 카테고리
		let cId = parts[2];
		let page = $(this).attr("href");

		let form = $("<form>");
		form.attr("action", `/board/list`).append(getHiddenTag("page", page));

		if (cId != 'read') {
			form.attr("action", `/board/${cId}/list`);
		}

		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type)).append(getHiddenTag("keyword", keyword));
		}
		form.appendTo("body").submit();

	});

	// 상세페이지로 이동
	$(".board_list_boardList").find("a").click(function(event) {
		event.preventDefault();
		let page = $("input[name='page']");

		// 전체 URI 가져오기
		let uri = document.location.pathname; // /board/{id}/insert
		// '/' 기준으로 분리
		let parts = uri.split("/");
		// 배열의 두 번째 요소가 카테고리
		let cId = parts[2];
		let bId = $(this).attr("href");

		let form = $("<form>").attr("action", `/board/read/` + bId).append(getHiddenTag("page", page.val()));;

		if (cId != 'list') {
			form.attr("action", `/board/${cId}/read/` + bId);
		}

		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type)).append(getHiddenTag("keyword", keyword));
		}

		form.appendTo("body").submit();

	});

	// 페이지 이동
	$("#pagination").find("a").click(function(event) {
		event.preventDefault();
		let form = $("<form>").append(getHiddenTag("page", $(this).attr("href")));

		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type)).append(getHiddenTag("keyword", keyword));
		}

		form.appendTo("body").submit();

	});

});