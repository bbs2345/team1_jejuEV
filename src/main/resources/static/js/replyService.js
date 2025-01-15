console.log("replyService.js파일 불러옴.");

function makeReplyListTag(obj) {
	let tag = ``;

	for (i of obj) {
		tag += `			
		<div>
			작성자 : ${i.writer}  내용 : ${i.content}  작성일 : ${i.createDate}`;
		if (i.writer == $("input[name='username']").val()) {
			tag += `
			<button type='button' class='reply_btn_update' data-rId='${i.id}'>수정</button> 
			<button type='button' class='reply_btn_delete' data-rId='${i.id}'>삭제</button>`;
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
					if(isDelete){
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
							success: function(data) {
								alert("댓글이 삭제되었습니다.");
								getReplyList();
							}
						});
					}
					
				});
				
			});

			// 댓글 수정 버튼 클릭 이벤트
			$("#board_read_show_reply_list").find(".reply_btn_update").each(function() {
				$(this).click(function() {
					let rId = $(this).attr("data-rId");

					$.ajax({
						url: "/replies/",
						type: "put",
						data: JSON.stringify({
							rId: rId,
							content: ""
						}),
						headers: {
							"Content-Type": "application/json",
							"X-HTTP-Method-Override": "PUT"
						},
						dataType: "text",
						success: function(data) {
							alert("댓글이 삭제되었습니다.");
							getReplyList();
						}
					});
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
			}
		});
	});
});