console.log("replyService.js파일 불러옴.");






// 댓글 리스트 페이징 렌더링


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
                    <a class="page-link" href="#" data-page="${currentPage +1}">다음</a>
                </li>
                <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
                    <a class="page-link" href="#" data-page="${totalPages}">마지막으로</a>
                </li>
            </ul>
        </nav>`;

    $("#reply_pagenation").html(pagingTag);
}

// 댓글 리스트 HTML 생성
function makeReplyListTag(comments) {
    return comments.map(comment => `
        <div class='d-flex'>
            작성자: ${comment.writer} 내용: ${comment.content} 작성일: ${comment.writeDate}
            ${comment.writer === $("input[name='username']").val() ? `
            <div class="replyOriBtn" style="display: block;">
                <button type="button" class="reply_btn_update" data-rId="${comment.id}">수정</button>
                <button type="button" class="reply_btn_delete" data-rId="${comment.id}">삭제</button>
            </div>
            <div class="replyUpBtn" style="display: none;">
                <input type="text" class="reply_edit_input" value="${comment.content}" />
                <button type="button" class="reply_save_btn" data-id="${comment.id}">저장</button>
                <button type="button" class="reply_cancel_btn" data-id="${comment.id}">취소</button>
            </div>` : ''}
        </div><hr>
    `).join('');
}

// 댓글 리스트 및 페이징 가져오기
function getReplyList(page = -1) {
    const bId = $("input[name='boardId']").val();

    $.ajax({
        url: `/replies/${bId}?page=${page}&size=5`,
        type: "GET",
        dataType: "json",
        success: function(result) {
            const { content, number, totalPages } = result;

            // 댓글 리스트 렌더링
            $("#board_read_show_reply_list").html(makeReplyListTag(content));

            // 페이징 렌더링
            renderReplyPaging(number + 1, totalPages);

            // 페이징 이벤트 설정
            $("#reply_pagenation").on("click", "a.page-link", function(e) {
                e.preventDefault();
                const targetPage = $(this).data("page");
                if (targetPage) getReplyList(targetPage);
            });

            // 댓글 이벤트 리스너 설정
            setReplyEventListeners();
        }
    });
}

// 댓글 이벤트 리스너 설정
function setReplyEventListeners() {
    // 삭제 버튼
    $("#board_read_show_reply_list").on("click", ".reply_btn_delete", function() {
        if (confirm("정말 삭제하시겠습니까?")) {
            const rId = $(this).data("rId");
            $.ajax({
                url: `/replies/`,
                type: "DELETE",
                data: JSON.stringify({ rId }),
                headers: { "Content-Type": "application/json" },
                success: function() {
                    alert("댓글이 삭제되었습니다.");
                    getReplyList();
                },
                error: function(xhr) {
                    console.error("댓글 삭제 중 오류 발생:", xhr.responseText);
                    alert("댓글 삭제에 실패했습니다.");
                }
            });
        }
    });

    // 수정 버튼
    $("#board_read_show_reply_list").on("click", ".reply_btn_update", function() {
        $(this).closest("div").next().show().prev().hide();
    });

    // 수정 완료 버튼
    $("#board_read_show_reply_list").on("click", ".reply_save_btn", function() {
        const rId = $(this).data("id");
        const content = $(this).closest("div").find(".reply_edit_input").val().trim();
        const bId = $("input[name='boardId']").val();

        $.ajax({
            url: `/replies/${rId}`,
            type: "PUT",
            data: JSON.stringify({ bId, content }),
            headers: { "Content-Type": "application/json" },
            success: function() {
                getReplyList();
            },
            error: function(xhr) {
                console.error("댓글 수정 중 오류 발생:", xhr.responseText);
                alert("댓글 수정에 실패했습니다.");
            }
        });
    });

    // 수정 취소 버튼
    $("#board_read_show_reply_list").on("click", ".reply_cancel_btn", function() {
        $(this).closest("div").hide().prev().show();
    });
}

// 초기 실행
$(function() {
    getReplyList(); // 초기 댓글 리스트 및 페이징 호출

    // 댓글 작성
    $("#board_read_reply_insert").click(function() {
        const bId = $("input[name='boardId']").val();
        const writer = $("#replyWriter").val();
        const content = $("#replyContent").val().trim();

        if (!content) {
            alert("댓글 내용을 입력해 주세요.");
            return;
        }

        $.ajax({
            url: "/replies/",
            type: "POST",
            data: JSON.stringify({ bId, writer, content }),
            headers: { "Content-Type": "application/json" },
            success: function() {
                $("#replyContent").val("");
                getReplyList();
            },
            error: function(xhr) {
                console.error("댓글 작성 중 오류 발생:", xhr.responseText);
                alert("댓글 작성에 실패했습니다.");
            }
        });
    });
});
