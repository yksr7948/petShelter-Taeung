<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/survey/surveyQnA_style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/survey/surveyResult_style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/survey/animation.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<!-- Q&A 영역 -->
	<section id="qna">
		<div class="statusCount mt-5"></div>
		<div class="status mx-auto">
			<div class="statusBar"></div>
		</div>
	
		<div class="qBox my-5 py-3"></div>
		
		<div class="aBox"></div>
	</section>

	<!-- 결과 영역 -->	
	<div class="container">
		<section id="result" class="mx-auto mt-5 py-5 px-3" align="center">
			<div>
				<h1>당신의 결과는?!</h1>
				<div class="resultName"></div>
			</div>
			
			<!-- 이미지 -->
			<div id="resultImg" class="my-5"></div>
			
			<!-- 설명 -->
			<div class="resultDesc my-5 py-5 px-5">
			
			</div>
			
			<!-- 버튼 -->
			<div>
				<a onclick="redo();" class="start-button"><i class="fas fa-redo"></i> 다시하기</a>
			</div>
				
		</section>
	</div>
	
	<!-- 자바스크립트파일에서 pageContext 경로를 잡지 못하므로 속성값으로 전달 -->
	<div id="contextPath" data-context-path="${pageContext.request.contextPath}"></div>
	
	<!-- 데이터 js -->
	<script src="${pageContext.request.contextPath }/js/survey/surveyData.js"></script>
	<!-- 질문 답변 생성 js -->
	<script src="${pageContext.request.contextPath }/js/survey/surveyQnA.js"></script>
	
	<script>
		function redo(){
			location.href="${pageContext.request.contextPath}/survey/index.si";
		}
	</script>
	

	
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>