console.log("replyService.js파일 불러옴.");

//
function replyPaging(obj) {

	let tag = `
   <div>
       <ul class="pagination">`;

	if (!obj.first) {
		tag += `
      <li><a class="page-link" href="1">처음으로</a></li>
        <li><a class="page-link" href="${obj.number}"><</a></li>
      `;
	}

	// 페이지 번호들
	for (let i = 1; i <= obj.totalPages; i++) {
		let activeClass = (i == obj.number + 1) ? 'active' : '';  // 현재 페이지일 때 active 클래스 추가
		tag += `
        <li class="${activeClass}"><a class="page-link" href="${i}">${i}</a></li>
        `;
	}

	if (!obj.last) {
		tag += `
      <li><a class="page-link" href="${obj.number + 2}">></a></li>
      <li><a class="page-link" href="${obj.totalPages}">마지막으로</a></li>
      `;
	}
	tag += `
      </ul>
   </div>`;

	$("#reply_pagenation").html(tag);

	// 페이지 이동
	$("#reply_pagenation").find("a").click(function(event) {
		event.preventDefault();
		let page = $(this).attr("href");
		getReplyList(page);
	});
}

//댓글 리스트 페이징
/*
function renderReplyPaging(currentPage, totalPages) {
	let pagingTag = `
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-center">
				<li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
					<a class="page-link" href="#" data-page="1">처음으로</a>
				</li>
				<li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
					<a class="page-link" href="#" data-page="${currentPage - 1}">이전</a>
				</li>`;

	for (let i = 1; i <= totalPages; i++) {
		pagingTag += `
			<li class="page-item ${i === currentPage ? 'active' : ''}">
				<a class="page-link" href="#" data-page="${i}">${i}</a>
			</li>`;
	}

	pagingTag += `
				<li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
					<a class="page-link" href="#" data-page="${currentPage + 1}">다음</a>
				</li>
				<li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
					<a class="page-link" href="#" data-page="${totalPages}">마지막으로</a>
				</li>
			</ul>
		</nav>`;

	$("#replyPaging").html(pagingTag);
}
*/



// 댓글 리스트 생성
function makeReplyListTag(obj) {

	let tag = ``;

	for (i of obj) {
		tag += `			
		<div class='d-flex'>
			<div>
			작성자 : ${i.writer}  내용 : ${i.content}  작성일 : ${i.writeDate}  
			</div>`;

		tag += `
		<button data-rId="${i.id}" class="reply-like-button" type='button' style="margin-right: 10px;">
		👍<span id="reply-like-count">`;

		if (i.likes == null) {
			tag += `</span>`;
		}
		if (i.likes != null) {
			tag += `${i.likes}</span>`;
		}
		tag += `
		</button>
		<button data-rId="${i.id}" class="reply-dislike-button" type='button' style="margin-right: 10px;">
		👎<span id="reply-dislike-count">`;

		if (i.dislikes != null) {
			tag += `
			${i.dislikes}</span>`;
		}
		tag += `</span>
		</button>
		`;
		if (i.writer == $("input[name='username']").val()) {
			tag += `
			<div class="replyOriBtn" style="display: block;">
				<button type='button' class='reply_btn_update' data-rId='${i.id}'>수정</button> 
				<button type='button' class='reply_btn_delete' data-rId='${i.id}'>삭제</button>
			</div>
			
			<div class="replyUpBtn" style="display: none;">
			    <input type="text" class="reply_edit_input" value="${i.content}" />
			    <button type="button" class="reply_save_btn" data-id="${i.id}">저장</button>
			    <button type="button" class="reply_cancel_btn" data-id="${i.id}">취소</button>
			</div>
			`;
		}
		tag += `
		</div>
		<hr>`;

	}

	return tag;
}


// 댓글 수정

function getReplyList(page) {
	let bId = $("input[name='boardId']").val();

	// page가 undefined일 경우 기본값 1로 설정
	if (page === undefined) {
		page = 1;
	}

	$.ajax({
		//url: "/replies/" + bId+"?page="+num+"&size=5",
		url: `/replies/${bId}?page=${page}`,
		type: "get",
		headers: {
			"Content-Type": "application/json",
			"X-HTTP-Method-Override": "GET"
		},
		dataType: "text",
		success: function(result) {
			let obj = JSON.parse(result);

			$("#qq").find("span").text(obj.otalElements);

			let tag = makeReplyListTag(obj["content"]);

			console.log(obj);

			$("#board_read_show_reply_list").html(tag);

			//===========================================
			// 좋아요/나빠요 이벤트 통합
			$("#board_read_show_reply_list").off('click', ".reply-like-button, .reply-dislike-button");
			$("#board_read_show_reply_list").on('click', ".reply-like-button, .reply-dislike-button", function () {
			    let rId = $(this).attr("data-rId"); // 댓글 ID 가져오기
			    let isLike = $(this).hasClass("reply-like-button"); // 좋아요 버튼인지 확인
			    let reactionType = isLike ? "like" : "dislike"; // 반응 타입 결정
			    let countSpan = isLike ? $(this).siblings(".reply-like-count") : $(this).siblings(".reply-dislike-count"); // 카운트 요소 찾기

			    $.ajax({
			        url: "/replies/reaction/" + rId,
			        type: "post",
			        data: { reactionType: reactionType },
			        success: function (response) {
			            if (response === "success") {
			                let count = countSpan.text();
			                count = (count === "") ? 0 : Number(count); // 현재 카운트 값 가져오기
			                if (!isNaN(count)) {
			                    countSpan.text(count + 1);
			                }
			                getReplyList(1);
			            }
			        }
			    });
			});
//			//좋아요 나빠요
//			$("#board_read_show_reply_list").find(".reply-like-button").each(function() {
//				$(this).click(function() {
//					let rId = $(this).attr("data-rId");
//					$.ajax({
//						url: "/replies/reaction/" + rId,
//						type: "post",
//						data: { reactionType: "like" },
//						success: function(response) {
//							if (response === "success") {
//								let likeCount = $("#reply-like-count").text();
//								likeCount = (likeCount === "") ? 0 : Number(likeCount);
//								if (!isNaN(likeCount)) {
//									$("#reply-like-count").text(likeCount + 1);
//								}
//								getReplyList(1);
//							}
//						}
//					});
//
//				});
//			});
//
//			$("#board_read_show_reply_list").off('click', ".reply-dislike-button");
//			$("#board_read_show_reply_list").on('click', ".reply-dislike-button", function() {
//			    let rId = $(this).attr("data-rId");
//			    let dislikeSpan = $(this).siblings(".reply-dislike-count"); // 근처 dislike 카운트 요소 찾기
//
//			    $.ajax({
//			        url: "/replies/reaction/" + rId,
//			        type: "post",
//			        data: { reactionType: "dislike" },
//			        success: function(response) {
//			            if (response === "success") {
//			                let dislikeCount = dislikeSpan.text();
//			                dislikeCount = (dislikeCount === "") ? 0 : Number(dislikeCount);
//			                if (!isNaN(dislikeCount)) {
//			                    dislikeSpan.text(dislikeCount + 1); // 해당 댓글 dislike 카운트 증가
//			                }
//			                getReplyList(1); // 댓글 리스트 새로고침
//			            }
//			        }
//			    });
//			});
//

			//===========================================

			replyPaging(obj);







			// 댓글 삭제 버튼 클릭 이벤트
			$("#board_read_show_reply_list").find(".reply_btn_delete").each(function() {

				$(this).click(function() {

					let isDelete = confirm("정말 삭제하시겠습니까?");
					if (isDelete) {
						let rId = $(this).attr("data-rId");
						$.ajax({
							url: "/replies/",
							type: "delete",
							data: JSON.stringify({
								rId: rId
							}),
							headers: {
								"Content-Type": "application/json",
								"X-HTTP-Method-Override": "DELETE"
							},
							dataType: "text",
							success: function() {
								alert("댓글이 삭제되었습니다.");
								getReplyList(1);
							}
						});
					}

				});

			});

			// 댓글 수정 버튼 클릭 이벤트 --> 댓글 수정 모드로 만드는 거
			$("#board_read_show_reply_list").find(".reply_btn_update").each(function() {
				$(this).click(function(event) {
					let rId = $(this).attr("data-rId"); // 댓글 번호
					let parentDiv = $(this).closest("div");  // 수정 버튼이 있는 부모 div
					parentDiv.next().show(); // 수정 버튼 클릭 시 수정 UI 보이기
					parentDiv.hide(); // 수정/삭제 버튼 숨기기
				});
			});

			// 수정완료 버튼 
			$("#board_read_show_reply_list").on("click", ".reply_save_btn", function() {
				let rId = $(this).data("id"); // 수정할 댓글 ID
				let content = $(this).closest("div").find(".reply_edit_input").val();
				let bId = $("input[name='boardId']").val(); // 게시글 ID
				let writer = $("input[name='username']").val(); // 작성자 이름			

				$.ajax({
					url: "/replies/" + rId,
					type: "put",
					data: JSON.stringify({
						bId: bId,
						writer: writer,
						content: content.trim()
					}),
					headers: {
						"Content-Type": "application/json"
					},
					dataType: "text",
					success: function() {
						getReplyList(1);  // 댓글 리스트 새로고침
					}
				});
			});


			// 취소 버튼
			$("#board_read_show_reply_list").find(".reply_cancel_btn").each(function() {
				$(this).click(function() {
					let parentDiv = $(this).closest("div");
					parentDiv.hide(); // 입력창 숨기기
					parentDiv.prev().show(); // 수정/삭제 버튼 다시 표시
				});
			});
		}
	});

}


$(function() {

	getReplyList(1);

	let bId = $("input[name='boardId']");
	let replyWriter = $("#replyWriter");
	let replyContent = $("#replyContent");

	// 댓글 작성
	$("#board_read_reply_insert").click(function() {

		$.ajax({
			url: "/replies/",
			type: "post",
			data: JSON.stringify({
				bId: bId.val(),
				writer: replyWriter.val(),
				content: replyContent.val()
			}),
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "POST"
			},
			dataType: "text",
			success: function(result) {
				getReplyList(1);
				$("#replyContent").val("");
			}
		});
	});
});