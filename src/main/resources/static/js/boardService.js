console.log("boardService.js파일 불러옴.");



function makeImg(result){
	let tag =`<img src="${result}" width="100" height="100">`;
	return tag;
}

$(function() {
	//보더 삭제 기능
	$("#delete_board_botton").click(function() {
		$("#board_delete_service").submit();
	})
	
	
	//보더 상세보기 이미지 삭제 기능

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
	
});