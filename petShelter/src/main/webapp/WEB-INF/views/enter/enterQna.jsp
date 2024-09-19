<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/enter/enterQna_style.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<h1>나에게 맞는 반려동물 알아보기</h1>

<!-- 강아지 냐옹이 사진 -->
<div class="image-container">
    <img src="${pageContext.request.contextPath}/img/common/dogcat.jpg" class="centered-image">
</div>

<!-- 시작하기 버튼 추가 -->
<div class="start-section">
    <button id="startButton" class="start-button">시작하기</button>
</div>


<!-- 질문 섹션 -->
<div class="question-section" style="display: none;">
    <h2 id="questionText" data-question-no="${currentQuestion.questionNo}">${currentQuestion.questionText}</h2>

    <div id="answerSection">
    <c:if test="${not empty currentQuestion.answers}">
        <c:forEach var="answer" items="${currentQuestion.answers}">
            <button class="answer-button" data-answer="${answer.answerNo}">${answer.answerText}</button>
        </c:forEach>
    </c:if>
	</div>

    <div class="progress-bar">
        <div class="progress-bar-fill" style="width: ${(questionNumber / totalQuestions) * 100}%"></div>
    </div>
    <p>질문 ${questionNumber}/${totalQuestions}</p>
</div>

<script>
$(document).ready(function() {
    // 시작하기 버튼 클릭 시 질문 시작
    $('#startButton').click(function() {
        $('.start-section').hide();  // 시작하기 숨김
        $('.image-container').hide();  // 이미지 숨김
        $('.question-section').show();  // 질문 보이기
    });

    // 질문 답변 클릭 처리
    $(document).on('click', '.answer-button', function() {
        var answerNo = $(this).data('answer');
        var currentQuestionNo = $('#questionText').data('question-no');
        
        $.post('${pageContext.request.contextPath}/enter/nextQuestion', {
            currentQuestionNo: currentQuestionNo,
            answerNo: answerNo
        }, function(response) {
        	console.log(response);
            if (response.isComplete) {
                // 질문이 끝난 경우 결과 페이지로 이동
                window.location.href = '${pageContext.request.contextPath}/enter/qnaResult';
            } else {
                // 다음 질문 표시	
                $('#questionText').text(response.nextQuestion.questionText);
	            $('#questionText').data('question-no', response.nextQuestion.questionNo);  // 다음 질문 번호로 업데이트
	            $('#answerSection').empty();
	            response.nextQuestion.answers.forEach(function(answer) {
	                $('#answerSection').append('<button class="answer-button" data-answer="' + answer.answerNo + '">' + answer.answerText + '</button>');
	            });

                // 진행 게이지 업데이트
                var progressPercent = (response.questionNumber / ${totalQuestions}) * 100;
                $('.progress-bar-fill').css('width', progressPercent + '%');
                $('p').text('질문 ' + response.questionNumber + '/' + ${totalQuestions});
            }
        });
    });
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>