console.log("boardService.js파일 불러옴.");



function makeImg(result){
	let tag =`<img src="${result}" width="100" height="100">`;
	return tag;
}

$(function() {
	
	// 이미지 삭제 버튼 클릭 이벤트 처리
	 $("#deleteImage").click(function(event){
	    event.preventDefault();
	       
	    let filename = $(this).attr("data-filename"); // 클릭한 이미지의 파일 이름
		let isDelete = confirm("정말 삭제할거니?");
	    
		
		if(isDelete){
		    $.ajax({
		        url: '/board/deleteBoardFile', // 요청 URL 수정
		        type: "POST",
		        dataType: "text",
		        data: {filename : filename},
		        success: function(response){
		           alert(response); // 서버에서 보내준 응답 처리
		           location.reload(); // 페이지 새로 고침
		       }
			})
		}   
		
	});
	
	
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