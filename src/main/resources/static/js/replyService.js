console.log("replyService.js파일 불러옴.");
function makeReplyListTag(obj){
	let tag = `
	<div class="reply">
		<div>댓글 아이디: ${obj["id"]}</div>
		<div>${obj["content"]}</div>
	  	
	</div>
	`;
	
	return tag;
	
}


// 댓글 삭제


// 댓글 수정


// 댓글 목록 가져오기

getReplyList();

function getReplyList() {
	let bId = $("input[name='boardId']").val();
	$.ajax({
		url: "/replies/"+bId,
		type: "get",
		headers: {
			"Content-Type": "application/json",
			"X-HTTP-Method-Override": "GET"
		},
		dataType: "text",
		success: function(result) {
			let object = JSON.parse(result);
			
			let tag =``;
			for(i of object){
				console.log(i);
				tag += `			
				<div>
					작성자 : ${i.writer}  내용 : "${i.content}"  작성일 : ${i.createDate}
					<button type='button'>수정</button> 
				    <button type='button'>삭제</button>  
				</div>
				<hr>`;
			}
			
			
			$("#board_read_show_reply_list").html(tag);
		}
	});

}

// 댓글 작성

$(function() {

	let bId = $("input[name='boardId']");
	let replyWriter = $("#replyWriter").val();
	let replyContent = $("#replyContent").val();

	getReplyList();

	//let bId2 = `${boardResponse.id}`;
	//console.log(".js 보드아이디 : "+ bId2);


	// $("#board_read_btn_reply_insert") -> $("#board_read_reply_insert")
	// button type submit -> button
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
				getReplyList(bId.val());
			}
		});
	});
});

	//let count = 1;
	$("#qqq").click(function() {
		//$(".dddd").append("<input name='test"+count+"' value='"+count+"'>");
		$(".dddd").append(`<input name="test${count}" value="${count}">`);
		count = count + 1;
	});