<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 자세히 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	<link href="/css/replyStyle.css" rel="stylesheet">
	<link href="/css/boardStyle.css" rel="stylesheet">
<style type="text/css">
	
table {
    width: 100%;
    margin-bottom: 20px;
    border-collapse: collapse;
    border: 1px solid #FFCC80;
}

table th, table td {
    padding: 12px;
    text-align: left;
    border: 1px solid #FFCC80;
    font-size: 1em;
}

table th {
    background-color: #FFF3E0;
    color: #FF9F00;
    font-weight: bold;
}

table td {
    background-color: #FFFAF0;
}

.board_read_btns, .board_read_btns_reaction {
    display: flex;
    justify-content: space-between;
    
}

.board_read_btns a, .reaction-button, .empty_user_reaction-button {
    display: inline-block;
    background: linear-gradient(90deg, #FF9F00, #FFA726);
    color: white;
    padding: 3px 10px;
    border-radius: 15px;
    text-decoration: none;
    font-weight: bold;
    transition: all 0.3s ease;
}

.board_read_btns a:hover, .reaction-button:hover {
    background: linear-gradient(90deg, #FFB74D, #FFCC80);
    transform: translateY(-3px);
}

.reaction-button i {
    margin-right: 5px;
    font-size: 1.2em;
}

.images img {
    margin: 10px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

#replies_list {
    margin-top: 40px;
    padding: 15px;
    background: rgba(255, 255, 255, 0.9);
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
    border: 1px solid #FFCC80;
}


button{
    background: linear-gradient(90deg, #00ACC1, #26C6DA);
    border: none;
    color: white;
    font-weight: bold;
    padding: 10px 20px;
    border-radius: 8px;
    transition: all 0.3s ease;
}

#replies_list button {
    display: inline-block;
    background: linear-gradient(90deg, #FF9F00, #FFA726);
    color: white;
    padding: 3px 10px;
    border-radius: 15px;
    text-decoration: none;
    font-weight: bold;
    transition: all 0.3s ease;
}

#reply_pagenation {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}

	</style>
	
	
	
	    <link href="/css/boardStyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="../part/part_header.jsp" %>
	

	<div class="container" id="board_read_css">
		<div>
			<h3>게시글 자세히 보기</h3>
		</div>
		
		<div>
			<form action="/board/delete" method="post" id="board_delete_service">
				<input type="hidden" id="board_read_csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">
				<input type="hidden" name="id" value="${boardResponse.id}" /> 
				<input type="hidden" name="username" value="${principal.username}">
				<input type="hidden" name="reactionLike" value="${reactionResponse.likes}">
				<input type="hidden" name="reactionDislike" value="${reactionResponse.dislikes}">
				<table class="table">
					<tr>
						<th>글번호</th>
						<td>${boardResponse.id}</td>
						<th>작성일</th>
						<td>${boardResponse.createDate}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${boardResponse.title}</td>
						<th>작성자</th>
						<td>${boardResponse.writer}</td>
					</tr>
					<tr>
						<td colspan="4">
							<p>${boardResponse.content}</p>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
							<div class="images">
								<c:forEach items="${boardResponse.attachList}" var="dto">
									<img src="/board/imgDisplay?fullFileName=${dto.filename}" width="100px" height="100px">
								</c:forEach>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="d-flex justify-content-between">
			<div class="board_read_btns">
			    <a href="${criteria.page}" id="toBoardList">목록</a> 
			    
			    <c:if test="${principal.role == 'ROLE_ADMIN' or principal.username == boardResponse.writer}">
				    <a href="/board/update/${boardResponse.id}">수정</a>
				    <a href="#" id="delete_board_botton">삭제</a>
			    </c:if>
		    </div>
		    
			<div class="board_read_btns_reaction">
				<c:if test="${not empty principal}">
					<button class="reaction-button" data-reaction-type="like">
					    <i class="bi bi-hand-thumbs-up"></i> <span id="like-count">0</span>
					</button>
					<button class="reaction-button" data-reaction-type="dislike">
					    <i class="bi bi-hand-thumbs-down"></i> <span id="dislike-count">0</span>
					</button>
				</c:if>
				<c:if test="${empty principal}" >
					<button type="button" class="empty_user_reaction-button">
					    <i class="bi bi-hand-thumbs-down"></i> <span id="like-count">0</span>
					</button>
					<button type="button" class="empty_user_reaction-button">
					    <i class="bi bi-hand-thumbs-up"></i> <span id="dislike-count">0</span>
					</button>
				</c:if>
		    </div>
		</div>
	
		<hr>
		<!-- 댓글 영역 -->
		<div id="replies_list">
			<div class="container">
				<h5>댓글작성</h5>
				<!-- 댓글 작성 -->
				<form action="/replies/" method="post" id="replyForm" enctype="application/json">
					<input type="hidden" id="csrf_token_value" name="${_csrf.parameterName}" value="${_csrf.token}">
					
					<input id="ttt" type="hidden" name="boardId" value="${boardResponse.id}" />
	
					<!-- 로그인해야만 댓글작성 할 수 있도록 바꾸기 -->
					<div>
						<c:if test="${empty principal}">
							<input type="text" id="replyWriter" class="empty-form-control" placeholder="작성자" required />
						</c:if>
						<c:if test="${not empty principal}">
							<input readonly="readonly" value="${principal.name}">
							<input type="hidden" id="replyWriter" class="form-control" readonly="readonly" value="${principal.username}" />
						</c:if>
					</div>
	
					<div>
						<textarea id="replyContent" class="form-control" rows="3" placeholder="댓글 내용을 입력해 주세요" required></textarea>
					</div>
					<button id="board_read_reply_insert" type="button">댓글 작성</button>
				</form>
				<hr>
				<h5>댓글 목록<span></span></h5><hr>
				<div id="board_read_show_reply_list"></div>
			</div>
		
		</div>
		<div class=" d-flex justify-content-center" id="reply_pagenation"></div>
	</div>
		

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/boardService.js"></script>
<script type="text/javascript" src="/js/replyService.js"></script>
<script type="text/javascript">
	getReaction();
</script>
</body>
</html>
