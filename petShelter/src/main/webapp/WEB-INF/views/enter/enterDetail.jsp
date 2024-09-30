<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/enter/enterDetail.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="enter_detail_container">
    <h1>${enter.enterTitle}</h1>
    <p>글쓴이: ${enter.memberNo}</p> <!-- 이름으로 바꿀 예정 -->
    <p>조회수: ${enter.enterCount}</p>

    <div class="content-wrapper">
        <!-- 왼쪽 이미지 섹션 -->
        <div class="image-container">
            <c:forEach var="attachment" items="${attachments}">
                <img src="${pageContext.request.contextPath}/${attachment.changeName}" alt="Image">
            </c:forEach>
        </div>

       <!-- 오른쪽 게시물 내용 및 댓글 섹션 -->
        <div class="enter_content" id="enterContent">
            <div class="page enter_text" id="pageContent">
                <p>${enter.enterContent} z</p>
            </div>
            <div class="page comment_section" id="pageComments" style="display: none;">
                <h2>댓글</h2>
                <div class="comment_list">
                    <c:forEach var="reply" items="${replyList}">
                        <div class="comment_item">
                             <span class="comment_author">${reply.memberNo}</span>
						     <span class="comment_content">${reply.reContent}</span>
						     <span class="comment_date">${reply.createDate}</span>
                        </div>
                    </c:forEach>
                </div>
                <div class="comment_form">
                    <form id="commentForm" action="${pageContext.request.contextPath}/enter/addComment" method="post">
                        <input type="hidden" name="enterNo" value="${enter.enterNo}">
                        <textarea name="reContent" placeholder="댓글을 입력하세요..."></textarea>
                        <button type="submit" id="reply-button">댓글 작성</button>
                    </form>
                </div>
            </div>
            <button id="toggleContent" class="toggle_button">→</button>
        </div>
    </div>
    
    <!-- 좋아요 버튼 -->
    <div class="like-section">
        <button type="button" id="like-button" class="${isLiked ? 'liked' : ''}">
            ${isLiked ? '좋아요 취소' : '좋아요'} (${likeCount})
        </button>
    </div>

    <!-- 신청서 보기 버튼 -->
    <div class="action-section">
        <button type="button" id="openFormModal">신청서 보기</button>
    </div>
    
    <!-- 신청서 보기 모달 -->
    <div id="formModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>신청서 정보</h2>
            <div class="form-group">
                <label>품종:</label>
                <p><strong>${enterForm.enterType}</strong></p>
            </div>
            <div class="form-group">
                <label>성별:</label>
                <p><strong>${enterForm.gender}</strong></p>
            </div>
            <div class="form-group">
                <label>나이:</label>
                <p><strong>${enterForm.age}살</strong></p>
            </div>
            <div class="form-group">
                <label>접종 여부:</label>
                <p><strong>${enterForm.inoculation}</strong></p>
            </div>
            <div class="form-group">
                <label>중성화 여부:</label>
                <p><strong>${enterForm.neutering}</strong></p>
            </div>
            <div class="form-group">
                <label>특이 사항:</label>
                <p><strong>${enterForm.significant}</strong></p>
            </div>
            <div class="form-group">
                <label>보호자 연락처:</label>
                <p><strong>${enterForm.phone}</strong></p>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<script>
$(document).ready(function(){
    // 모달 열기
    $("#openFormModal").on("click", function(){
        $("#formModal").show();
    });

    // 모달 닫기
    $(".close").on("click", function(){
        $("#formModal").hide();
    });

    // 좋아요 버튼 클릭
    $("#like-button").on("click", function(){
        $.ajax({
            url: "toggleLike",
            type: "POST",
            data: { enterNo: ${enter.enterNo} },
            success: function(likeCount) {
                $("#like-button").toggleClass("liked");
                $("#like-button").text($("#like-button").hasClass("liked") ? '좋아요 취소 (' + likeCount + ')' : '좋아요 (' + likeCount + ')');
            }
        });
    });

  //콘텐츠와 댓글 페이지 전환
    $("#toggleContent").on("click", function(){
        if ($("#pageComments").is(":visible")) {
            $("#pageComments").hide();
            $("#pageContent").show();
            $(this).text("→");
        } else {
            $("#pageComments").show();
            $("#pageContent").hide();
            $(this).text("←");
        }
    });
});
</script>

</body>