<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adopt/adoptReviewDetail_style.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<!-- 입양 리스트 헤더 -->
	<div class="adopt_header">
		<div class="adopt_nav">
			<a href="${pageContext.request.contextPath }">Home</a> >
			 <a href="#">입양</a> >
			  <a href="${pageContext.request.contextPath }/adopt/reviewList.ar">입양 후기</a>
		</div>
		<h1>입양 후기</h1>
	</div>
	
	<div class="container">
		<header class="review_detail_title">
			<h1>${review.reviewTitle }</h1>
			<p class="review_detail_info">
				<img src="${pageContext.request.contextPath}/img/adopt/user-icon.png"> 1 /
				<img src="${pageContext.request.contextPath}/img/adopt/calendar-icon.png"> ${review.createDate } /
				<img src="${pageContext.request.contextPath}/img/adopt/eye-icon.png"> ${review.count }
			</p>
		</header>
		<div class="review_detail_content">${review.reviewContent }</div>
	</div>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>