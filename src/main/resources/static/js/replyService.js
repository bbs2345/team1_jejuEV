console.log("replyService.js파일 불러옴.");

function makeReplyListTag(obj) {
	let tag = ``;

	for (i of obj) {
		tag += `			
		<div class='d-flex'>
			작성자 : ${i.writer}  내용 : ${i.content}  작성일 : ${i.writeDate}  `;
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

function getReplyList() {
	let bId = $("input[name='boardId']").val();

	$.ajax({
		url: "/replies/" + bId,
		type: "get",
		headers: {
			"Content-Type": "application/json",
			"X-HTTP-Method-Override": "GET"
		},
		dataType: "text",
		success: function(result) {
			let obj = JSON.parse(result);

			let tag = makeReplyListTag(obj);

			$("#board_read_show_reply_list").html(tag);

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
								getReplyList();
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
			$("#board_read_show_reply_list").on("click", ".reply_save_btn", function () {
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
					success: function () {
						getReplyList();  // 댓글 리스트 새로고침
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

	getReplyList();

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
				getReplyList();
				$("#replyContent").val("");
			}
		});
	});
});