<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/survey/surveyIndex_style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/survey/animation.css">
<title>Insert title here</title>
<style>
</style>
</head>
<body>

	
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<!-- header부분 -->
		<section id="main" class="mx-auto my-5 py-5 px-3" align="center">
			<div>
				<h1>나에게 맞는 반려동물 테스트</h1>
				<span>
					당신의 성격에 가장 잘 어울리는 반려동물은 무엇일까요? <br>
					이 테스트를 통해 알아보세요. 당신의 일상, 성향, 그리고 취향을 <br>
					분석하여 당신과 가장 잘 어울리는 반려동물을 추천해 드립니다. <br>
					사랑스러운 반려동물과의 삶은 더욱 행복해질 수 있어요. 당신만의 반려동물을 찾아보세요!
				</span>
			</div>
			
			<!-- 이미지 -->
			<div class="col-lg-6 col-md-8 col-sm-10 mx-auto">
				<img src="${pageContext.request.contextPath }/img/survey/index.png">
			</div>
			
			<!-- 버튼 -->
			<div>
				<button class="start-button" onclick="begin();" style="font-size: 24px;">테스트 시작</button>
			</div>
		</section>
		
		<!-- 애니메이션 추가 -->
		<script>
			const main = document.querySelector("#main");
			
			main.style.animation = "fadeIn 1.5s";
			
			function begin(){
				
				main.style.WebkitAnimation = "fadeOut 1s";
				main.style.animation = "fadeOut 1s";
				
				setTimeout(() => {
					location.href = "${pageContext.request.contextPath }/survey/question.sq";
				}, 450);
			}
		</script>
	</div>
	
	
	
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>