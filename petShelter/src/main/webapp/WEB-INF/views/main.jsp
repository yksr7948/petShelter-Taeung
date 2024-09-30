<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/main_style.css">
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="poster-slider">
        <div class="poster-slider-container">
            <div class="poster-slide"><img src="${pageContext.request.contextPath}/img/common/banner01.png" onclick="animalTest1();"></div>
            <div class="poster-slide"><img src="${pageContext.request.contextPath}/img/common/banner02.png" onclick="animalTest2();"></div>
        </div>
        <button class="prev" onclick="moveSlide(-1)">&#10094;</button>
        <button class="next" onclick="moveSlide(1)">&#10095;</button>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/common/main.js"></script>
	<%@include file="common/footer.jsp"%>
</body>
</html>