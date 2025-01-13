console.log("adminService.js파일 불러옴.");

$(function() {
	
	// 회원목록 페이지로 이동
	$("#toMemberList").click(function(event){
		event.preventDefault();
		let page = $(this).attr("href");
		
		let form = $("<form>").attr("action", "/admin/memberList").append(getHiddenTag("page", page));
		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}
		form.appendTo("body").submit();
		
	});

	let page = $("input[name='page']");
	let type = getSearchParam("type");
	let keyword = getSearchParam("keyword");

	// 상세페이지로 이동
	$(".admin_memberList").find("a").click(function(event) {
		event.preventDefault();
		let username = $(this).attr("href");

		let form = $("<form>").attr("action", "/member/read/" + username).attr("method", "get").append(getHiddenTag("page", page.val()));

		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}

		form.appendTo("body").submit();

	});

	// 페이지 이동
	$("#pagination").find("a").click(function(event) {
		event.preventDefault();

		let form = $("<form>").attr("action", "/admin/memberList").attr("method", "get").append(getHiddenTag("page", $(this).attr("href")));

		if (type != null && keyword != null) {
			form.append(getHiddenTag("type", type));
			form.append(getHiddenTag("keyword", keyword));
		}

		form.appendTo("body").submit();

	});

});