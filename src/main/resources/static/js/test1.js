function makeReplyListTag(obj){
	let tag = `
	<div class="reply">
		<div>댓글 아이디: ${obj["id"]}</div>
		<div>${obj["content"]}</div>
	  	<div> <button data-reply-id=${obj["id"]}>수정</button> 
			  <button  data-reply-id=${obj["id"]}>삭제</button>  
		</div>
	</div>
	`;
	
	return tag;
	
}